import java.util.ArrayList;

/**
 * Scheduling Scheme
 * Abstract Class that outlines some of the necessary member data and functions
 * for the scheduling algorithms to have. Also, comes with generalized functions
 * for calculating average wait time and average turnaround time
 * 
 * CS 376 
 * 4/7/2020
 * @author Dalton Rothenberger
 *
 */
public abstract class SchedulingScheme {

    // Name of the Scheduling Algorithm
    protected String _name;

    // The PCBs to schedule
    protected ArrayList<PCB> _toSchedule = new ArrayList<PCB>();

    // Stores the PCBs after the process has been completed
    // Used for analytics at the end
    protected ArrayList<PCB> _completedProcesses = new ArrayList<PCB>();

    /**
     * Runs the processes according to the scheduling algorithm
     */
    public abstract void runProcess();

    /**
     * Calculates the average turnaround time from the completed processes
     * 
     * @return The average turn around time for the processes
     */
    public double calculateAvgTurnaround() {
        double toReturn = 0;

        for (int i = 0; i < _completedProcesses.size(); i++) {
            toReturn += _completedProcesses.get(i).getTurnAroundTime();
        }

        toReturn = toReturn / (1.0 * _completedProcesses.size());

        return toReturn;
    }

    /**
     * Calculates the average wait time from the completed processes
     * 
     * @return The average wait time for the processes
     */
    public double calculateAvgWaittime() {
        double toReturn = 0;

        for (int i = 0; i < _completedProcesses.size(); i++) {
            toReturn += _completedProcesses.get(i).getWaitTime();
        }

        toReturn = toReturn /(1.0 * _completedProcesses.size());

        return toReturn;
    }

    /**
     * Gets the name of the scheduling scheme
     * 
     * @return The name of the scheduling scheme
     */
    public String getName() {
        return this._name;
    }

    /**
     * Gets the proccess to be scheduled
     * 
     * @return The processes to be scheduled
     */
    public ArrayList<PCB> getToSchedule() {
        return this._toSchedule;
    }

}
