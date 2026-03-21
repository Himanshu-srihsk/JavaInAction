package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

public interface SchedulingStrategy {
    long computeNextExecutionTime(Task task);
    boolean isRecurring();
}
