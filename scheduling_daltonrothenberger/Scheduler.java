package scheduling_daltonrothenberger;


public class Scheduler {
    private SchedulingScheme _scheme;

    private Scheduler() {}

    public Scheduler(SchedulingScheme scheme) {
        _scheme = scheme;
    }

    public void runScheme() {
        _scheme.runProcess();
    }


}
