package scheduling_daltonrothenberger;

import java.util.ArrayList;




public class PE_SJF extends SchedulingScheme {

    private PE_SJF(){}
    
    /**
     * Constructor of a PE_SJF scheduling scheme
     * @param name The name of the scheduling algorithm
     * @param toSchedule The PCBs to be sorted
     */
    public PE_SJF(ArrayList<PCB> toSchedule) {
        _name = "PE_SJF";
        _toSchedule = toSchedule;
    }
    public void runProcess() {
        
    }
    
}
