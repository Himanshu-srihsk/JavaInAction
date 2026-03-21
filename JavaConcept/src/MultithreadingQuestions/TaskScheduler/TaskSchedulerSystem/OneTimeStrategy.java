package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

public class OneTimeStrategy implements SchedulingStrategy{
    @Override
    public long computeNextExecutionTime(Task task) {
        return -1;
    }

    @Override
    public boolean isRecurring() {
        return false;
    }
}
