/**
 * Scheduler
 * Class that is responsible for running the specified
 * scheduling scheme. Apart of the strategy pattern where this
 * class determines the algorithm that will be ran
 * 
 * CS 376 
 * 4/7/2020
 * @author Dalton Rothenberger
 *
 */
public class Scheduler {

    // The scheduling scheme that the Scheduler is responsible for running
    private SchedulingScheme _scheme;

    // Non-arg constructor made private so can't be accidentally used
    private Scheduler() {
    }

    /**
     * Constructs a Scheduler with the specified SchedulingScheme object as its
     * member data
     * 
     * @param scheme The scheme the scheduler will run
     */
    public Scheduler(SchedulingScheme scheme) {
        _scheme = scheme;
    }

    /**
     * Runs the scheme that the scheduler object contains
     */
    public void runScheme() {

        System.out.println("Scheduling Algorithm: " + _scheme.getName());
        System.out.println(_scheme.getToSchedule().size() + " tasks to be scheduled");
        System.out.println("=====================================================================================");

        _scheme.runProcess();
    }

}
