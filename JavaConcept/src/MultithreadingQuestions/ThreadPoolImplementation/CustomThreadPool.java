package MultithreadingQuestions.ThreadPoolImplementation;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class CustomThreadPool {
    private final int minThread;
    private final int maxThread;
    private List<WorkerThread> threads;
    private final BlockingQueue<Runnable> workQueue;
    private final ThreadFactory threadFactory;
    private final RejectedExecutionHandler rejectedExecutionHandler;
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int minThread, int maxThread, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        this.minThread = minThread;
        this.maxThread = maxThread;
        this.workQueue = workQueue;
        this.threadFactory = threadFactory != null ? threadFactory : Executors.defaultThreadFactory();;
        this.rejectedExecutionHandler = rejectedExecutionHandler!=null ? rejectedExecutionHandler : new ThreadPoolExecutor.AbortPolicy();
        this.threads = new CopyOnWriteArrayList<>();
        for(int i = 0; i < this.minThread; i++) {
            WorkerThread  workerThread = new WorkerThread(workQueue);
           threads.add(workerThread);
           new Thread(workerThread).start();
        }
    }
    public void submit(Runnable task) {
        if (task == null) throw new NullPointerException();
        if (isShutdown) {
            rejectedExecutionHandler.rejectedExecution(task, null);
            return;
        }
       if(!workQueue.offer(task)){
           synchronized (threads) {
               if(threads.size() < this.maxThread){
                   System.out.println("All min threads are busy and queue is  full, creating a new thread to handle task: " + task);
                   WorkerThread workerThread = new WorkerThread(workQueue);
                   threads.add(workerThread);
                   new Thread(workerThread).start();
                   if (!workQueue.offer(task)) {
                       rejectedExecutionHandler.rejectedExecution(task, null);
                       System.out.println("Task rejected: " + task + " (after new thread creation)");
                   }
               }
               else{
                   rejectedExecutionHandler.rejectedExecution(task,null);
                   System.out.println("Task rejected: " + task + " (due to full queue and max threads reached)");
               }
           }
       }else{
            System.out.println("Task " + task + " added to queue.");
        }
    }

    public void shutdown() {
        isShutdown = true;
        for (WorkerThread worker : threads) {
            // new Thread(worker).interrupt();  // Interrupt each worker thread
            worker.setWorkerRunning(false);
        }
        System.out.println("Custom thread pool shut down.");
    }


    public boolean isTerminated() {
        for (WorkerThread worker : threads) {
            if (worker.isWorkerRunning()) return false;
        }
        return true;
    }
}
