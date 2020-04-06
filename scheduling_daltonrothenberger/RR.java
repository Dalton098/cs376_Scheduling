package scheduling_daltonrothenberger;

import java.util.ArrayList;


public class RR extends SchedulingScheme {

    private int _quantum;
    
    private RR(){}
    
    /**
     * Constructor of a RR scheduling scheme
     * @param name The name of the scheduling algorithm
     * @param toSchedule The PCBs to be sorted
     */
    public RR(ArrayList<PCB> toSchedule, int quantum) {
        _name = "RR";
        _toSchedule = toSchedule;
        _quantum = quantum;
    }
    
    
     public void runProcess() {
        
    }
    
}
