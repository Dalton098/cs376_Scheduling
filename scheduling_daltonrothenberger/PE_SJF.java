import java.util.*;
import java.text.DecimalFormat;

//  Sorting via using a method reference and comparing method from Collections
// Found at: https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
import static java.util.Comparator.comparing;

/**
 * PE_SJF
 * Pre-emptive shortest job first scheduling algorithm that extends the SchedulingScheme.
 * The runProcess() method implements the pre-emptive shortest job first scheduling algorithm
 * using the member data of the object.
 * 
 * CS 376 
 * 4/7/2020
 * @author Dalton Rothenberger
 *
 */
public class PE_SJF extends SchedulingScheme {

    // Non-arg constructor made private so can't be accidentally used
    private PE_SJF() {
    }

    /**
     * Constructor of a PE_SJF scheduling scheme
     * 
     * @param name       The name of the scheduling algorithm
     * @param toSchedule The PCBs to be scheduled
     */
    public PE_SJF(ArrayList<PCB> toSchedule) {
        _name = "PE_SJF";
        _toSchedule = toSchedule;
    }

    /**
     * Runs the PE_SJF scheduling algorithm on the _toSchedule processes
     */
    public void runProcess() {

        // total time that has elapsed
        int time = 0;

        // time elapsed that the cpu has been used
        int usageTime = 0;

        // Arraylist containing the elements actively being bursted
        ArrayList<PCB> scheduling = new ArrayList<PCB>();

        while (!_toSchedule.isEmpty() || !scheduling.isEmpty()) {

            for (int i = 0; i < _toSchedule.size(); i++) {
                if (_toSchedule.get(i).getArrivalTime() <= time) {
                    scheduling.add(_toSchedule.remove(i));
                    i--;
                }
            }

            // Sorting using comparing and method reference passing so that the PCBs
            // are sorted using their burst times (See import comment)
            Collections.sort(scheduling, comparing(PCB::getBurstDurationLeft));

            // Current Process going for its burst
            if (!scheduling.isEmpty()) {
                PCB currProcess = scheduling.get(0);

                int newDuration = 0;
                newDuration = currProcess.getBurstDurationLeft() - 1;
                currProcess.setBurstDurationLeft(newDuration);

                System.out.println("<System time " + time + "> process " + currProcess.getPid() + " is running");

                usageTime++;
                time++;

                if (currProcess.getBurstDurationLeft() == 0) {
                    System.out.println("<System time " + time + "> process " + currProcess.getPid() + " is finished");

                    currProcess.setTurnAroundTime(time);

                    _completedProcesses.add(scheduling.remove(0));

                }
            }

            // For if the CPU has to wait for a process to arrive
            // this would impact cpu usage
            if (!_toSchedule.isEmpty() && scheduling.isEmpty()) {
                time++;
            }

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
