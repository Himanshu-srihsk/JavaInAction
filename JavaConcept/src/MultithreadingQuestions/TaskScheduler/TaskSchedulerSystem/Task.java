package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

import java.util.concurrent.atomic.AtomicBoolean;

public class Task {

    private final String taskId;
    private final Runnable job;
    private final ScheduleType scheduleType;
    private final long interval;

    private volatile long nextExecutionTime;

    private final AtomicBoolean cancelled = new AtomicBoolean(false);

    public Task(
            String taskId,
            Runnable job,
            ScheduleType scheduleType,
            long startTime,
            long interval) {

        this.taskId = taskId;
        this.job = job;
        this.scheduleType = scheduleType;
        this.nextExecutionTime = startTime;
        this.interval = interval;
    }

    public String getTaskId() {
        return taskId;
    }

    public Runnable getJob() {
        return job;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public long getInterval() {
        return interval;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public void setNextExecutionTime(long time) {
        this.nextExecutionTime = time;
    }

    public void cancel() {
        cancelled.set(true);
    }

    public boolean isCancelled() {
        return cancelled.get();
    }
}
