package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

/*
Design and implement a Task Scheduler System that can schedule and execute tasks with different scheduling strategies.
The scheduler should support:

Task Types ->
One-time task -> Runs once at a given time.
Fixed Delay task -> Next execution starts after previous execution finishes + delay
Fixed Rate task -> Runs at a fixed interval regardless of execution time
Recurring (Cron-like) task -> Runs periodically.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskScheduler scheduler = new TaskScheduler(3);

        scheduler.scheduleTask(
                "task1",
                () -> System.out.println("ONE TIME " + System.currentTimeMillis()),
                ScheduleType.ONE_TIME,
                2000,
                0);

        scheduler.scheduleTask(
                "task2",
                () -> System.out.println("FIXED RATE " + System.currentTimeMillis()),
                ScheduleType.FIXED_RATE,
                1000,
                3000);

        scheduler.scheduleTask(
                "task3",
                () -> {
                    System.out.println("FIXED DELAY start " + System.currentTimeMillis());
                    try { Thread.sleep(2000); } catch (Exception ignored) {}
                    System.out.println("FIXED DELAY end " + System.currentTimeMillis());
                },
                ScheduleType.FIXED_DELAY,
                1000,
                4000);

        Thread.sleep(20000);

        scheduler.shutdown();
    }
}
/*
FIXED RATE 1774049178333
FIXED DELAY start 1774049178333
ONE TIME 1774049179325
FIXED DELAY end 1774049180349
FIXED RATE 1774049181327
FIXED RATE 1774049184336
FIXED DELAY start 1774049184367
FIXED DELAY end 1774049186379
FIXED RATE 1774049187325
FIXED RATE 1774049190336
FIXED DELAY start 1774049190383
FIXED DELAY end 1774049192388
FIXED RATE 1774049193335
FIXED RATE 1774049196339
FIXED DELAY start 1774049196401
FIXED DELAY end 1774049198411
Disconnected from the target VM, address: '127.0.0.1:49942', transport: 'socket'

 */