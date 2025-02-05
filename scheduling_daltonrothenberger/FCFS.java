import java.util.*;
import java.text.DecimalFormat;

//  Sorting via using a method reference and comparing method from Collections
// Found at: https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
import static java.util.Comparator.comparing;

/**
 * FCFS
 * First come first serve scheduling algorithm that extends the SchedulingScheme.
 * The runProcess() method implements the first come first serve scheduling algorithm
 * using the member data of the object.
 * 
 * CS 376 
 * 4/7/2020
 * @author Dalton Rothenberger
 *
 */
public class FCFS extends SchedulingScheme {

    // Non-arg constructor made private so can't be accidentally used
    private FCFS() {
    }

    /**
     * Constructor of a FCFS scheduling scheme
     * 
     * @param name       The name of the scheduling algorithm
     * @param toSchedule The PCBs to be scheduled
     */
    public FCFS(ArrayList<PCB> toSchedule) {
        _name = "FCFS";
        _toSchedule = toSchedule;
    }

    /**
     * Runs the FCFS scheduling algorithm on the _toSchedule processes
     */
    public void runProcess() {

        // Sorting using comparing and method reference passing so that the PCBs
        // are sorted using their arrival times (See import comment)
        Collections.sort(_toSchedule, comparing(PCB::getArrivalTime));

        int time = 0;
        int usageTime = 0;
        PCB currProcess;

        while (!_toSchedule.isEmpty()) {

            // Always looking at the first element because the arraylist
            // is sorted by arrival time and when an element is complete it gets removed
            currProcess = _toSchedule.get(0);
            int newDuration = 0;

            // Checking if the process is finished
            if (currProcess.getBurstDurationLeft() == 0) {
                System.out.println("<System time " + time + "> process " + currProcess.getPid() + " is finished");

                currProcess.setTurnAroundTime(time);

                _completedProcesses.add(_toSchedule.remove(0));
                
                // Causes the finishing to not count as a time tick
                // so 1 process can finish at the time and the next can start at that time
                time--;

            // Checks if the process has arrived yet
            } else if (currProcess.getArrivalTime() <= time) {

                newDuration = currProcess.getBurstDurationLeft() - 1;
                currProcess.setBurstDurationLeft(newDuration);

                System.out.println("<System time " + time + "> process " + currProcess.getPid() + " is running");
                usageTime++;

            }
            
            time++;
        }

        System.out.println("<System time " + time + "> All Processes finished");

        // Formatter to format to 2 decimals
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Calculating and printing out analytics
        double cpuUsage = ((1.0 * (usageTime)) / (1.0 * time)) * 100.0;
        double waitTime = calculateAvgWaittime();
        double turnaroundTime = calculateAvgTurnaround();
        System.out.println("=====================================================================================");
        System.out.println("Average CPU usage: " + df.format(cpuUsage) + "%");
        System.out.println("Average Wait time: " + df.format(waitTime));
        System.out.println("Average turnaround time: " + df.format(turnaroundTime));

        // Assigning back the processses in case the object is used again
        // since they were removed in the process
        _toSchedule = _completedProcesses;

    }

}
