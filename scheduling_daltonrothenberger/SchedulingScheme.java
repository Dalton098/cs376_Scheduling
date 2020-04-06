package scheduling_daltonrothenberger;

import java.util.ArrayList;


public abstract class SchedulingScheme {

    // Name of the Scheduling Algorithm
    protected String _name;
    
    // The PCBs to schedule 
    protected ArrayList<PCB> _toSchedule;

    // Stores the PCBs after the process has been completed
    // Used for analytics at the end
    protected ArrayList<PCB> _completedProcesses;
    
    public abstract void runProcess();
    
    
}
