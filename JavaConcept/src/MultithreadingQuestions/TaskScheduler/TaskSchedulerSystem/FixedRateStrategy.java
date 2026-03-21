package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

public class FixedRateStrategy implements SchedulingStrategy{
    @Override
    public long computeNextExecutionTime(Task task) {
        return task.getNextExecutionTime()+ task.getInterval();
    }

    @Override
    public boolean isRecurring() {
        return true;
    }
}
