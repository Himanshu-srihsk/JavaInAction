package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskScheduler {
    private final DelayQueue<ScheduledTask> queue = new DelayQueue<>();
    /*
    we need a queue ordered by next execution time.
    The scheduler thread should block until the next task is ready.
    So we need:
    PriorityQueue + blocking behavior + delay awareness
    Java already provides DelayQueue which internally handles this.

    Note:

    DelayQueue internally uses a PriorityQueue (min-heap).
    The heap is ordered by the element's remaining delay.
    So the queue always keeps:
    head -> task with smallest delay (earliest execution time)
    Which means:
    earliest task always at the top

    Note2:

    Any element inserted into a DelayQueue must implement:
    Delayed

    That interface requires:
    long getDelay(TimeUnit unit)
    int compareTo(Delayed other)
    These two methods allow the queue to:
    getDelay()	-> When the task becomes executable
    compareTo()	-> Ordering of tasks
     */
    private final ExecutorService workerPool;
    private final Map<String, Task> taskRegistry = new ConcurrentHashMap<>();
    private final Thread schedulerThread;
    private volatile boolean running = true;

    public TaskScheduler(int workerThreads) {
        workerPool = Executors.newFixedThreadPool(workerThreads);
        schedulerThread = new Thread(this::runScheduler);
        schedulerThread.start();
    }

    private void runScheduler() {

        while (running) {
            try {
                ScheduledTask scheduledTask = queue.take();

                /*

                Retrieves and removes the expired head of this queue, waiting if necessary until an expired element is available on this queue.
                Returns:
                the expired head of this queue
                Throws:
                InterruptedException – if interrupted while waiting

                Note:

                DelayQueue uses the Leader–Follower pattern.
                Only one thread becomes the leader and performs the timed wait for the head element’s delay.
                Other threads wait indefinitely on a condition variable.
                When the leader finishes or when a new earlier element is inserted, another thread is signaled to become the new leader.


                 Algo:

                loop forever
                    firstTask = queue.peek()
                    delay = firstTask.getDelay()

                    if delay <= 0
                        return task

                    wait(delay)
                 */
                Task task = scheduledTask.getTask();
                if (task.isCancelled())
                    continue;
                workerPool.submit(() -> executeTask(task));
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void executeTask(Task task) {
        try {
            task.getJob().run();
        } finally {
            reschedule(task);
        }
    }

    private void reschedule(Task task) {
        SchedulingStrategy strategy = StrategyFactory.getStrategy(task.getScheduleType());
        if (!strategy.isRecurring() || task.isCancelled())
            return;
        long nextTime = strategy.computeNextExecutionTime(task);
        task.setNextExecutionTime(nextTime);
        queue.put(new ScheduledTask(task));
    }

    public void scheduleTask(
            String taskId,
            Runnable job,
            ScheduleType type,
            long initialDelay,
            long interval) {

        long startTime = System.currentTimeMillis() + initialDelay;
        Task task = new Task(taskId, job, type, startTime, interval);
        taskRegistry.put(taskId, task);
        queue.put(new ScheduledTask(task));
    }

    public void cancelTask(String taskId) {

        Task task = taskRegistry.get(taskId);
        if (task != null)
            task.cancel();
    }

    public void shutdown() {
        running = false;
        schedulerThread.interrupt();
        workerPool.shutdown();
    }
}
