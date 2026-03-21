package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ScheduledTask implements Delayed {
    private final Task task;

    public ScheduledTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long delay = task.getNextExecutionTime() - System.currentTimeMillis();
        return unit.convert(delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {

        ScheduledTask other = (ScheduledTask) o;

        return Long.compare(
                this.task.getNextExecutionTime(),
                other.task.getNextExecutionTime());
    }
}
