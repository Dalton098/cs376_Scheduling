package scheduling_daltonrothenberger;

import java.net.Socket;

public class Scheduler {
    private SchedulingScheme _scheme;

    private Scheduler() {}

    public Scheduler(SchedulingScheme scheme) {
        _scheme = scheme;
    }

    public void runScheme() {
        System.out.println("Scheduling Algorithm: " + _scheme.getName());
        System.out.println(_scheme.getToSchedule().size() + " tasks to be scheduled");
        System.out.println("=====================================================================================");
        _scheme.runProcess();
    }


}
