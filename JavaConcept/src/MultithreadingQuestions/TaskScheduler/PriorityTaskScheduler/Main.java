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
Connected to the target VM, address: '127.0.0.1:56432', transport: 'socket'
[Dispatcher] Started
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-18 | priority=3 | scheduledStart=1764155370637 | actualReady=1764155370674
[Dispatcher] Task Task-18 moved to READY QUEUE
[Dispatcher] Task READY -> Task-23 | priority=5 | scheduledStart=1764155370660 | actualReady=1764155370674
[Dispatcher] Task Task-23 moved to READY QUEUE
[Dispatcher] Task READY -> Task-24 | priority=3 | scheduledStart=1764155370639 | actualReady=1764155370674
[Dispatcher] Task Task-24 moved to READY QUEUE
[2025-11-26T11:09:30.690193900Z] Worker - 1 STARTED Task-18 (priority=3, duration=910ms)
[2025-11-26T11:09:30.690193900Z] Worker - 0 STARTED Task-23 (priority=5, duration=346ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-19 | priority=2 | scheduledStart=1764155370728 | actualReady=1764155370754
[Dispatcher] Task Task-19 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:31.056485Z] Worker - 0 FINISHED Task-23
[2025-11-26T11:09:31.056485Z] Worker - 0 STARTED Task-19 (priority=2, duration=1334ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-10 | priority=1 | scheduledStart=1764155371352 | actualReady=1764155371369
[Dispatcher] Task Task-10 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-20 | priority=5 | scheduledStart=1764155371574 | actualReady=1764155371623
[Dispatcher] Task Task-20 moved to READY QUEUE
[2025-11-26T11:09:31.629515700Z] Worker - 1 FINISHED Task-18
[2025-11-26T11:09:31.629515700Z] Worker - 1 STARTED Task-10 (priority=1, duration=1269ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-27 | priority=3 | scheduledStart=1764155371716 | actualReady=1764155371726
[Dispatcher] Task Task-27 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-4 | priority=4 | scheduledStart=1764155371754 | actualReady=1764155371777
[Dispatcher] Task Task-4 moved to READY QUEUE
[Dispatcher] Task READY -> Task-8 | priority=3 | scheduledStart=1764155371774 | actualReady=1764155371777
[Dispatcher] Task Task-8 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-7 | priority=4 | scheduledStart=1764155371802 | actualReady=1764155371828
[Dispatcher] Task Task-7 moved to READY QUEUE
[Dispatcher] Task READY -> Task-17 | priority=3 | scheduledStart=1764155371805 | actualReady=1764155371828
[Dispatcher] Task Task-17 moved to READY QUEUE
[Dispatcher] Task READY -> Task-29 | priority=1 | scheduledStart=1764155371800 | actualReady=1764155371828
[Dispatcher] Task Task-29 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-0 | priority=2 | scheduledStart=1764155371956 | actualReady=1764155371981
[Dispatcher] Task Task-0 moved to READY QUEUE
[Dispatcher] Task READY -> Task-3 | priority=4 | scheduledStart=1764155371960 | actualReady=1764155371981
[Dispatcher] Task Task-3 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-5 | priority=2 | scheduledStart=1764155372387 | actualReady=1764155372388
[Dispatcher] Task Task-5 moved to READY QUEUE
[Dispatcher] Task READY -> Task-9 | priority=4 | scheduledStart=1764155372379 | actualReady=1764155372388
[Dispatcher] Task Task-9 moved to READY QUEUE
[2025-11-26T11:09:32.395678800Z] Worker - 0 FINISHED Task-19
[2025-11-26T11:09:32.395678800Z] Worker - 0 STARTED Task-29 (priority=1, duration=1048ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-2 | priority=4 | scheduledStart=1764155372506 | actualReady=1764155372540
[Dispatcher] Task Task-2 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-13 | priority=4 | scheduledStart=1764155372606 | actualReady=1764155372644
[Dispatcher] Task Task-13 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-11 | priority=5 | scheduledStart=1764155372758 | actualReady=1764155372798
[Dispatcher] Task Task-11 moved to READY QUEUE
[Dispatcher] Task READY -> Task-22 | priority=3 | scheduledStart=1764155372770 | actualReady=1764155372798
[Dispatcher] Task Task-22 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:32.899820900Z] Worker - 1 FINISHED Task-10
[2025-11-26T11:09:32.899820900Z] Worker - 1 STARTED Task-0 (priority=2, duration=1219ms)
[Dispatcher] Task READY -> Task-1 | priority=4 | scheduledStart=1764155372868 | actualReady=1764155372900
[Dispatcher] Task Task-1 moved to READY QUEUE
[Dispatcher] Task READY -> Task-28 | priority=1 | scheduledStart=1764155372857 | actualReady=1764155372900
[Dispatcher] Task Task-28 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-12 | priority=5 | scheduledStart=1764155373099 | actualReady=1764155373104
[Dispatcher] Task Task-12 moved to READY QUEUE
[Dispatcher] Task READY -> Task-16 | priority=2 | scheduledStart=1764155373058 | actualReady=1764155373104
[Dispatcher] Task Task-16 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-25 | priority=4 | scheduledStart=1764155373148 | actualReady=1764155373156
[Dispatcher] Task Task-25 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-14 | priority=2 | scheduledStart=1764155373192 | actualReady=1764155373207
[Dispatcher] Task Task-14 moved to READY QUEUE
[Dispatcher] Task READY -> Task-15 | priority=2 | scheduledStart=1764155373175 | actualReady=1764155373207
[Dispatcher] Task Task-15 moved to READY QUEUE
[Dispatcher] Task READY -> Task-21 | priority=4 | scheduledStart=1764155373181 | actualReady=1764155373207
[Dispatcher] Task Task-21 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Task READY -> Task-6 | priority=1 | scheduledStart=1764155373285 | actualReady=1764155373310
[Dispatcher] Task Task-6 moved to READY QUEUE
[Dispatcher] Task READY -> Task-26 | priority=2 | scheduledStart=1764155373284 | actualReady=1764155373310
[Dispatcher] Task Task-26 moved to READY QUEUE
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:33.445035500Z] Worker - 0 FINISHED Task-29
[2025-11-26T11:09:33.445035500Z] Worker - 0 STARTED Task-28 (priority=1, duration=781ms)
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
[2025-11-26T11:09:34.120207700Z] Worker - 1 FINISHED Task-0
[2025-11-26T11:09:34.120207700Z] Worker - 1 STARTED Task-6 (priority=1, duration=1076ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:34.227577100Z] Worker - 0 FINISHED Task-28
[2025-11-26T11:09:34.227577100Z] Worker - 0 STARTED Task-5 (priority=2, duration=999ms)
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
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:35.197916100Z] Worker - 1 FINISHED Task-6
[2025-11-26T11:09:35.197916100Z] Worker - 1 STARTED Task-16 (priority=2, duration=573ms)
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:35.228961400Z] Worker - 0 FINISHED Task-5
[2025-11-26T11:09:35.228961400Z] Worker - 0 STARTED Task-15 (priority=2, duration=873ms)
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
[2025-11-26T11:09:35.771375100Z] Worker - 1 FINISHED Task-16
[2025-11-26T11:09:35.771375100Z] Worker - 1 STARTED Task-14 (priority=2, duration=641ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:36.103458100Z] Worker - 0 FINISHED Task-15
[2025-11-26T11:09:36.103458100Z] Worker - 0 STARTED Task-26 (priority=2, duration=768ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:36.412973100Z] Worker - 1 FINISHED Task-14
[2025-11-26T11:09:36.412973100Z] Worker - 1 STARTED Task-24 (priority=3, duration=1424ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:36.873229Z] Worker - 0 FINISHED Task-26
[2025-11-26T11:09:36.873229Z] Worker - 0 STARTED Task-27 (priority=3, duration=582ms)
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
[2025-11-26T11:09:37.456176200Z] Worker - 0 FINISHED Task-27
[2025-11-26T11:09:37.456176200Z] Worker - 0 STARTED Task-8 (priority=3, duration=590ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:37.837901300Z] Worker - 1 FINISHED Task-24
[2025-11-26T11:09:37.837901300Z] Worker - 1 STARTED Task-17 (priority=3, duration=958ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:38.047812700Z] Worker - 0 FINISHED Task-8
[2025-11-26T11:09:38.047812700Z] Worker - 0 STARTED Task-22 (priority=3, duration=707ms)
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
[2025-11-26T11:09:38.756528200Z] Worker - 0 FINISHED Task-22
[2025-11-26T11:09:38.756528200Z] Worker - 0 STARTED Task-4 (priority=4, duration=867ms)
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:38.797352400Z] Worker - 1 FINISHED Task-17
[2025-11-26T11:09:38.797352400Z] Worker - 1 STARTED Task-7 (priority=4, duration=446ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:39.244765700Z] Worker - 1 FINISHED Task-7
[2025-11-26T11:09:39.244765700Z] Worker - 1 STARTED Task-3 (priority=4, duration=414ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:39.625165900Z] Worker - 0 FINISHED Task-4
[2025-11-26T11:09:39.625165900Z] Worker - 0 STARTED Task-9 (priority=4, duration=649ms)
[Dispatcher] Exiting. stopped=false
[2025-11-26T11:09:39.659977500Z] Worker - 1 FINISHED Task-3
[2025-11-26T11:09:39.659977500Z] Worker - 1 STARTED Task-2 (priority=4, duration=1285ms)
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
[2025-11-26T11:09:40.274642100Z] Worker - 0 FINISHED Task-9
[2025-11-26T11:09:40.274642100Z] Worker - 0 STARTED Task-13 (priority=4, duration=1071ms)
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
[Dispatcher] Exiting. stopped=false
Worker - 0 interrupted while running Task-13
[2025-11-26T11:09:40.437391300Z] Worker - 0 STARTED Task-1 (priority=4, duration=689ms)
Worker - 1 interrupted while running Task-2
[2025-11-26T11:09:40.439706700Z] Worker - 1 STARTED Task-25 (priority=4, duration=1142ms)
[2025-11-26T11:09:41.128881800Z] Worker - 0 FINISHED Task-1
[2025-11-26T11:09:41.128881800Z] Worker - 0 STARTED Task-21 (priority=4, duration=1419ms)
[2025-11-26T11:09:41.583060400Z] Worker - 1 FINISHED Task-25
[2025-11-26T11:09:41.583060400Z] Worker - 1 STARTED Task-20 (priority=5, duration=950ms)
[2025-11-26T11:09:42.533523Z] Worker - 1 FINISHED Task-20
[2025-11-26T11:09:42.533523Z] Worker - 1 STARTED Task-11 (priority=5, duration=1042ms)
[2025-11-26T11:09:42.548220200Z] Worker - 0 FINISHED Task-21
[2025-11-26T11:09:42.548220200Z] Worker - 0 STARTED Task-12 (priority=5, duration=1182ms)
[2025-11-26T11:09:43.576139800Z] Worker - 1 FINISHED Task-11
[2025-11-26T11:09:43.730420400Z] Worker - 0 FINISHED Task-12
Disconnected from the target VM, address: '127.0.0.1:56432', transport: 'socket'

 */