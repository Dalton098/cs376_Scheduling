import java.util.*;
import java.text.DecimalFormat;

/**
 * RR
 * Pre-emptive Round Robin scheduling algorithm that extends the SchedulingScheme.
 * The runProcess() method implements the pre-emptive round robin algorithm
 * using the member data of the object.
 * 
 * CS 376 
 * 4/7/2020
 * @author Dalton Rothenberger
 *
 */
public class RR extends SchedulingScheme {

    // The quantum used in the round robin algorithm
    private int _quantum;

    // Non-arg constructor made private so can't be accidentally used
    private RR() {
    }

    /**
     * Constructor of a RR scheduling scheme
     * 
     * @param name       The name of the scheduling algorithm
     * @param toSchedule The PCBs to be scheduled
     */
    public RR(ArrayList<PCB> toSchedule, int quantum) {
        _name = "RR";
        _toSchedule = toSchedule;
        _quantum = quantum;
    }

    /**
     * Runs the RR scheduling algorithm on the _toSchedule processes
     */
    public void runProcess() {

        // total time that has elapsed
        int time = 0;

        // time elapsed that the cpu has been used
        int usageTime = 0;

        // Arraylist containing the elements actively being bursted
        ArrayList<PCB> scheduling = new ArrayList<PCB>();

        while (!_toSchedule.isEmpty() || !scheduling.isEmpty()) {

            // Handles adding inital elements and if the scheduling goes empty
            // due to a gap between arrival time and last finished element
            if (scheduling.isEmpty()) {

                for (int i = 0; i < _toSchedule.size(); i++) {
                    if (_toSchedule.get(i).getArrivalTime() <= time) {
                        scheduling.add(_toSchedule.remove(i));
                        i--;
                    }
                }
            }

            for (int i = 0; i < scheduling.size(); i++) {

                // Current Process going for its burst
                PCB currProcess = scheduling.get(i);

                // the counter for tracking if the quantum burst is done
                int j = 0;

                // checks if the process completed during its burst
                boolean isRunning = true;

                // performing the specified length of burst by the quantum
                // also checks if the process is done
                while (j < _quantum && isRunning) {

                    int newDuration = 0;
                    newDuration = currProcess.getBurstDurationLeft() - 1;
                    currProcess.setBurstDurationLeft(newDuration);

                    System.out.println("<System time " + time + "> process " + currProcess.getPid() + " is running");

                    usageTime++;
                    time++;

                    if (currProcess.getBurstDurationLeft() == 0) {
                        isRunning = false;

                        System.out.println("<System time " + time + "> process " + currProcess.getPid() + " is finished");

                        currProcess.setTurnAroundTime(time);

                        _completedProcesses.add(currProcess);
                        scheduling.remove(currProcess);

                        // decrement because of the removed process
                        i--;
                    }

                    j++;
                }

                // Pre-emptive adding of elements to the scheduling arraylist
                for (int k = 0; k < _toSchedule.size(); k++) {
                    if (_toSchedule.get(k).getArrivalTime() <= time) {
                        scheduling.add(i + 1, _toSchedule.remove(k));
                        k--;
                    }
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
