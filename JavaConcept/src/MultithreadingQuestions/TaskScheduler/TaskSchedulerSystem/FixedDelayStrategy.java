package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

public class FixedDelayStrategy implements SchedulingStrategy{
    @Override
    public long computeNextExecutionTime(Task task) {
        return System.currentTimeMillis() + task.getInterval();
    }

    @Override
    public boolean isRecurring() {
        return true;
    }
}
