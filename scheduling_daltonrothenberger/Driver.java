package scheduling_daltonrothenberger;

import java.util.ArrayList;
import java.io.*;

public class Driver {

    /**
     * Comes in the form input_file [FCFS|RR|SJF] [time_quantum] Where input_file is
     * the file with the data for the PCBs FCFS|RR|SJF is the scheduling algorithm
     * selected time_quantum is only used when RR is selected which will be the time
     * quantum used for RR
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        // The minimum/maximum expected arguments to be taked in
        final int MIN_ARGUMENTS = 2;
        final int MAX_ARGUMENTS = 3;
        boolean isInputValid = true;

        // Checking minimum agruments
        if (args.length < MIN_ARGUMENTS) {
            System.out.println("Error: Not enough inputs.");
            System.out.println("Expected form: java driver input_file [FCFS|RR|SJF] [time_quantum]");
            System.exit(0);
        }

        // Name of input file
        String fileName = args[0];

        // Checking if a file exist for inputted genre
        File inputFile = new File(fileName);
        if (!inputFile.exists()) {
            System.out.println("Error: Invalid input file.");
            System.out.println("Expected form: java driver input_file [FCFS|RR|SJF] [time_quantum]");
            System.exit(0);
        }

        // Name of algorithm selected
        String algo = args[1].toUpperCase();

        // Checking an algorithm was selected
        if (!algo.equals("FCFS") && !algo.equals("RR") && !algo.equals("SJF")) {
            System.out.println("Error: Invalid algorithm selected.");
            System.out.println("Expected form: java driver input_file [FCFS|RR|SJF] [time_quantum]");
            System.exit(0);
        }

        // Setting the quantum for RR
        int quantum = 0;
        if (algo.equals("RR")) {
            if (args.length == MAX_ARGUMENTS) {
                quantum = Integer.parseInt(args[2]);
            } else {
                System.out.println("Error: No quantum given for RR.");
                System.out.println("Expected form: java driver input_file [FCFS|RR|SJF] [time_quantum]");
                System.exit(0);
            }
        }

        // The scheme for the scheduling
        SchedulingScheme scheme = null;

        // PCBs to schedule
        try {
            ArrayList<PCB> toSchedule = parseFile(inputFile);

            // Creating the correct scheme that was inputted
            switch (algo) {
                case "SJF":
                    scheme = new PE_SJF(toSchedule);
                    break;
                case "FCFS":
                    scheme = new FCFS(toSchedule);
                case "RR":
                    scheme = new RR(toSchedule, quantum);
                    break;
            }

            Scheduler scheduler = new Scheduler(scheme);

            scheduler.runScheme();

        } catch (Exception e) {
            System.out.println("Error: Unable to parse input file");
            System.out.println("Expected form of input file: pid arrivalTime burstTime");
        }

    }

    /**
     * Parses the input file and creates PCBs from it
     * 
     * @param file The file that will be parsed
     * @return An array list containing all the PCBs
     */
    public static ArrayList<PCB> parseFile(File file) throws Exception {

        ArrayList<PCB> toReturn = new ArrayList<PCB>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        int pid;
        int arrivalTime;
        int burstTime;
        PCB temp;

        while ((line = br.readLine()) != null) {
            // Splitting the string on spaces
            String[] data = line.trim().split("\\s+");

            pid = Integer.parseInt(data[0]);
            arrivalTime = Integer.parseInt(data[1]);
            burstTime = Integer.parseInt(data[2]);

            temp = new PCB(pid, arrivalTime, burstTime);

            toReturn.add(temp);
        }

        br.close();
        return toReturn;

    }

}
