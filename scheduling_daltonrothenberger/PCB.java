package scheduling_daltonrothenberger;

/**
 * Class that holds relevant information for the processes that will be executed
 * in the scheduling algorithms. Acts as a container of data and performs some
 * calcuations regarding the information inside the class such as wait time.
 */
public class PCB {

    private int _pid = -1;
    private int _arrivalTime = 0;
    private int _burstTime = 0;
    private int _burstDurationLeft = 0;
    private int _turnAroundTime = 0;

    // Non-arg constructor made private so can't be accidentally used
    private PCB() {
    }

    /**
     * Constructor of PCB for a process
     * 
     * @param pid         The process id
     * @param arrivalTime The arrival time of the process
     * @param burstTime    The total ticks the process will take to run
     */
    public PCB(int pid, int arrivalTime, int burstTime) {
        _pid = pid;
        _arrivalTime = arrivalTime;
        _burstTime = burstTime;
        _burstDurationLeft = burstTime;

    }

    /**
     * Getter for PID
     * 
     * @return The PID of the process for the PCB
     */
    public int getPid() {
        return this._pid;
    }

    /**
     * Getter for Arrival time
     * 
     * @return The arrival time of the process for the PCB
     */
    public int getArrivalTime() {
        return this._arrivalTime;
    }

    /**
     * Getter for burst time
     * 
     * @return The burst time of the process for the PCB
     */
    public int getBurstTime() {
        return this._burstTime;
    }

    /**
     * Getter for duration left
     * 
     * @return The duration left for the process of the PCB
     */
    public int getBurstDurationLeft() {
        return _burstDurationLeft;
    }

    /**
     * Setter for duration left Used to decrement the duration in pre-emptive
     * scheduling
     * 
     * @param duration The new duration for the duration left of the process
     */
    public void setBurstDurationLeft(int duration) {
        _burstDurationLeft = duration;
    }

    /**
     * Getter for wait time Calculates wait time using turnaround time and the total
     * tick time
     * 
     * @return The wait time for the process
     */
    public int getWaitTime() {
        return _turnAroundTime - _burstTime;
    }

    /**
     * Getter for turnaround time
     * 
     * @return The turnaround time for the process
     */
    public int getTurnAroundTime() {
        return this._turnAroundTime;
    }

    /**
     * Setter for turn around time Takes in the end time for the process and then
     * subtracts it from the arrival time to calculate the turn around time
     * 
     * @param endTime The tick time that the process finished at
     */
    public void setTurnAroundTime(int endTime) {
        _turnAroundTime = endTime - _arrivalTime;
    }

}
