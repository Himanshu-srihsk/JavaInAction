package MultithreadingQuestions.TaskScheduler.PriorityTaskScheduler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimedPriorityTaskScheduler {
    private final PriorityBlockingQueue<ScheduledTask> readyQueue = new PriorityBlockingQueue<>();
    private final List<ScheduledTask> pendingTasks = new ArrayList<>();
    private final Thread[] workers;
    private final Thread dispatcher;
    private final Lock pendingLock = new ReentrantLock();


    private volatile boolean stopped = false;
  //stopped tells all internal threads i.e. dispatcher and workers : We are shutting down — stop taking new tasks and exit safely when possible.

    public TimedPriorityTaskScheduler(int numWorkers){
        this.workers = new Thread[numWorkers];
        for(int i=0;i<numWorkers;i++){
            workers[i] = new Thread(new Worker(), "Worker - "+i);
            workers[i].start();
        }

        this.dispatcher = new Thread(new Dispatcher(), "Dispatcher");
        this.dispatcher.start();
    }

    public void scheduleTask(String name,int priority, long delayMillis, long durationMillis){
        long startTime = System.currentTimeMillis() + delayMillis;
        ScheduledTask scheduledTask = new ScheduledTask(durationMillis,name,priority,startTime);
        pendingLock.lock();
        try {
            pendingTasks.add(scheduledTask);
        }finally {
            pendingLock.unlock();
        }
    }

    public void shutdown(){
        stopped = true;
        dispatcher.interrupt();
        for(Thread w: workers){
            w.interrupt();
        }
    }

    private class Dispatcher implements Runnable{
        private static final long CHECK_INTERVAL_MS = 50;
        //How frequently the scheduler checks for tasks whose time has come to start.

        @Override
        public void run() {
            System.out.println("[Dispatcher] Started");
            while (!stopped){
                long now = System.currentTimeMillis();
                pendingLock.lock();
                try {
                    Iterator<ScheduledTask> iterator = pendingTasks.iterator();
                    while (iterator.hasNext()){
                        ScheduledTask task = iterator.next();
                        if(task.getStartTimeMillisec() <= now){
                            System.out.println(
                                    "[Dispatcher] Task READY -> " + task.getName() +
                                            " | priority=" + task.getPriority() +
                                            " | scheduledStart=" + task.getStartTimeMillisec() +
                                            " | actualReady=" + now
                            );

                            task.markReady(now);
                            readyQueue.offer(task);
                            iterator.remove();

                            System.out.println(
                                    "[Dispatcher] Task " + task.getName() +
                                            " moved to READY QUEUE"
                            );
                        }
                    }
                }finally {
                    pendingLock.unlock();
                }

                try {
                    Thread.sleep(CHECK_INTERVAL_MS);
                } catch (InterruptedException e) {
                    if (stopped) break;
                }

                System.out.println("[Dispatcher] Exiting. stopped=" + stopped);
            }
        }
    }

    private class Worker implements Runnable{

        @Override
        public void run() {
            while (!stopped || !readyQueue.isEmpty()){
                try {
                    ScheduledTask task = readyQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    if(stopped && readyQueue.isEmpty()){
                        break;
                    }
                }
            }
        }
    }
}
