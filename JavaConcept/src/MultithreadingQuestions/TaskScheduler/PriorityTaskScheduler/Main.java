package MultithreadingQuestions.TaskScheduler.PriorityTaskScheduler;

import java.util.Random;

/*

There could be multiple tasks
Each task has:
start time (when it becomes ready)
duration (how long it runs)
priority
Only tasks whose start time has arrived go into the ready queue
If more tasks are ready than free threads, workers always pick the highest-priority ones
Tasks that waited in the ready queue get a bit of fairness i.e .older ones of same priority go first
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TimedPriorityTaskScheduler scheduler = new TimedPriorityTaskScheduler(2);
        Random random = new Random();

        for(int i =0;i<30;i++){
            int priority = 1 + random.nextInt(5);
            long delay = random.nextInt(3000);
            long duration = 300 + random.nextInt(1200);

            scheduler.scheduleTask("Task-" + i, priority, delay, duration);
        }
        Thread.sleep(10000);
        scheduler.shutdown();
    }
}
/*
[Dispatcher] Started
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-16 moved to READY QUEUE
[2026-02-28T09:19:13.820849700Z] Worker - 0 STARTED Task-16 (priority=4, duration=621ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-17 moved to READY QUEUE
[2026-02-28T09:19:13.936784400Z] Worker - 1 STARTED Task-17 (priority=4, duration=1270ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-8 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-15 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-0 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:14.468226100Z] Worker - 0 FINISHED Task-16
[2026-02-28T09:19:14.473198400Z] Worker - 0 STARTED Task-0 (priority=1, duration=1330ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-9 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-5 moved to READY QUEUE
[Dispatcher] Task Task-2 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-21 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-24 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-28 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-29 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-14 moved to READY QUEUE
[2026-02-28T09:19:15.222357300Z] Worker - 1 FINISHED Task-17
[2026-02-28T09:19:15.222357300Z] Worker - 1 STARTED Task-9 (priority=1, duration=851ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-18 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-7 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:15.811415200Z] Worker - 0 FINISHED Task-0
[2026-02-28T09:19:15.811415200Z] Worker - 0 STARTED Task-21 (priority=1, duration=1071ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-23 moved to READY QUEUE
[Dispatcher] Task Task-1 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-25 moved to READY QUEUE
[2026-02-28T09:19:16.074490600Z] Worker - 1 FINISHED Task-9
[2026-02-28T09:19:16.074490600Z] Worker - 1 STARTED Task-28 (priority=1, duration=370ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-3 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-27 moved to READY QUEUE
[Dispatcher] Task Task-13 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-20 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-4 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:16.446532900Z] Worker - 1 FINISHED Task-28
[2026-02-28T09:19:16.446532900Z] Worker - 1 STARTED Task-18 (priority=1, duration=627ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-6 moved to READY QUEUE
[Dispatcher] Task Task-12 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-26 moved to READY QUEUE
[Dispatcher] Task Task-19 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-11 moved to READY QUEUE
[Dispatcher] Task Task-10 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task Task-22 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:16.883303100Z] Worker - 0 FINISHED Task-21
[2026-02-28T09:19:16.883303100Z] Worker - 0 STARTED Task-23 (priority=1, duration=905ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:17.074630900Z] Worker - 1 FINISHED Task-18
[2026-02-28T09:19:17.074630900Z] Worker - 1 STARTED Task-6 (priority=1, duration=393ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:17.470270900Z] Worker - 1 FINISHED Task-6
[2026-02-28T09:19:17.470270900Z] Worker - 1 STARTED Task-10 (priority=1, duration=1077ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:17.790447500Z] Worker - 0 FINISHED Task-23
[2026-02-28T09:19:17.790447500Z] Worker - 0 STARTED Task-25 (priority=2, duration=310ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:18.101255400Z] Worker - 0 FINISHED Task-25
[2026-02-28T09:19:18.101255400Z] Worker - 0 STARTED Task-27 (priority=2, duration=1139ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:18.548292700Z] Worker - 1 FINISHED Task-10
[2026-02-28T09:19:18.548292700Z] Worker - 1 STARTED Task-12 (priority=2, duration=594ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:19.143173600Z] Worker - 1 FINISHED Task-12
[2026-02-28T09:19:19.143173600Z] Worker - 1 STARTED Task-15 (priority=3, duration=864ms)
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:19.241332Z] Worker - 0 FINISHED Task-27
[2026-02-28T09:19:19.241332Z] Worker - 0 STARTED Task-2 (priority=3, duration=668ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:19.910723600Z] Worker - 0 FINISHED Task-2
[2026-02-28T09:19:19.910723600Z] Worker - 0 STARTED Task-14 (priority=3, duration=1070ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:20.009318300Z] Worker - 1 FINISHED Task-15
[2026-02-28T09:19:20.009318300Z] Worker - 1 STARTED Task-7 (priority=3, duration=711ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:20.721213300Z] Worker - 1 FINISHED Task-7
[2026-02-28T09:19:20.721213300Z] Worker - 1 STARTED Task-1 (priority=3, duration=850ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:20.983039600Z] Worker - 0 FINISHED Task-14
[2026-02-28T09:19:20.983039600Z] Worker - 0 STARTED Task-3 (priority=3, duration=1199ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:21.578184400Z] Worker - 1 FINISHED Task-1
[2026-02-28T09:19:21.578184400Z] Worker - 1 STARTED Task-13 (priority=3, duration=844ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:22.183180300Z] Worker - 0 FINISHED Task-3
[2026-02-28T09:19:22.183180300Z] Worker - 0 STARTED Task-11 (priority=3, duration=1100ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:22.423498900Z] Worker - 1 FINISHED Task-13
[2026-02-28T09:19:22.423498900Z] Worker - 1 STARTED Task-22 (priority=3, duration=862ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:23.284029600Z] Worker - 0 FINISHED Task-11
[2026-02-28T09:19:23.284029600Z] Worker - 0 STARTED Task-8 (priority=4, duration=400ms)
[Dispatcher] Exiting. stopped=false
[2026-02-28T09:19:23.286045700Z] Worker - 1 FINISHED Task-22
[2026-02-28T09:19:23.286045700Z] Worker - 1 STARTED Task-5 (priority=4, duration=517ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
Worker - 1 interrupted while running Task-5
[2026-02-28T09:19:23.673132900Z] Worker - 1 STARTED Task-24 (priority=4, duration=1326ms)
Worker - 0 interrupted while running Task-8
[2026-02-28T09:19:23.676183900Z] Worker - 0 STARTED Task-4 (priority=4, duration=634ms)
[2026-02-28T09:19:24.311006900Z] Worker - 0 FINISHED Task-4
[2026-02-28T09:19:24.311006900Z] Worker - 0 STARTED Task-26 (priority=4, duration=1499ms)
[2026-02-28T09:19:25.002368800Z] Worker - 1 FINISHED Task-24
[2026-02-28T09:19:25.002368800Z] Worker - 1 STARTED Task-29 (priority=5, duration=558ms)
[2026-02-28T09:19:25.561217900Z] Worker - 1 FINISHED Task-29
[2026-02-28T09:19:25.561217900Z] Worker - 1 STARTED Task-20 (priority=5, duration=814ms)
[2026-02-28T09:19:25.810528500Z] Worker - 0 FINISHED Task-26
[2026-02-28T09:19:25.810528500Z] Worker - 0 STARTED Task-19 (priority=5, duration=770ms)
[2026-02-28T09:19:26.376595300Z] Worker - 1 FINISHED Task-20
[2026-02-28T09:19:26.583193500Z] Worker - 0 FINISHED Task-19
 */