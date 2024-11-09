package MultithreadingQuestions.ThreadPoolImplementation;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class WorkerThread implements Runnable{
    // private final String name;
    private volatile boolean isRunning = true;
    private final BlockingQueue<Runnable> taskQueue;

    public WorkerThread(BlockingQueue<Runnable> taskQueue) {
      //  this.name = name;
        this.taskQueue = taskQueue;
    }

    public boolean isWorkerRunning() {
        return isRunning;
    }

    public void setWorkerRunning(boolean running) {
        isRunning = running;
    }
    @Override
    public void run() {
        while (isRunning || !taskQueue.isEmpty()) {
            try {
                Runnable task = taskQueue.poll();
                if (task != null) {
                    //System.out.println(name + " executing task");
                    task.run();
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println("Worker  interrupted.");
            }
        }


    }


}
