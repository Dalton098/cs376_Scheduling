package scheduling_daltonrothenberger;

import java.util.ArrayList;


public class FCFS extends SchedulingScheme {

    private FCFS(){}
    
    /**
     * Constructor of a FCFS scheduling scheme
     * @param name The name of the scheduling algorithm
     * @param toSchedule The PCBs to be sorted
     */
    public FCFS(ArrayList<PCB> toSchedule) {
        _name = "FCFS";
        _toSchedule = toSchedule;
    }
    
    
     public void runProcess() {
        
    }
    
}
