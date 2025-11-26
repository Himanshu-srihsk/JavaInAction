package MultithreadingQuestions.TaskScheduler.PriorityTaskScheduler;

import java.time.Instant;

public class ScheduledTask implements Runnable,Comparable<ScheduledTask>{
    private final String name;
    private final int priority;
    private final long startTimeMillisec;
    private final long durationMillisec;
    private long readyTimeMillisec;

    public ScheduledTask(long durationMillisec, String name, int priority, long startTimeMillisec) {
        this.durationMillisec = durationMillisec;
        this.name = name;
        this.priority = priority;
        this.startTimeMillisec = startTimeMillisec;
    }

    public long getStartTimeMillisec() {
        return startTimeMillisec;
    }

    public long getDurationMillisec() {
        return durationMillisec;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public long getReadyTimeMillisec() {
        return readyTimeMillisec;
    }

    public void markReady(long startTimeMillisec){
        this.readyTimeMillisec = startTimeMillisec;
    }

    @Override
    public int compareTo(ScheduledTask o) {
        int cmp = Integer.compare(this.priority,o.priority);
        if(cmp!=0){
            return cmp;
        }
        return Long.compare(this.readyTimeMillisec,o.readyTimeMillisec);
    }

    @Override
    public void run() {
       try {
           System.out.println(
                   "[" + Instant.now() + "] " +
                           Thread.currentThread().getName() +
                           " STARTED " + name +
                           " (priority=" + priority +
                           ", duration=" + durationMillisec + "ms)"
           );
           Thread.sleep(durationMillisec);
           System.out.println(
                   "[" + Instant.now() + "] " +
                           Thread.currentThread().getName() +
                           " FINISHED " + name
           );
       } catch (InterruptedException e) {
           System.out.println(Thread.currentThread().getName() +
                   " interrupted while running " + name);
           Thread.currentThread().interrupt();
       }
    }
}
