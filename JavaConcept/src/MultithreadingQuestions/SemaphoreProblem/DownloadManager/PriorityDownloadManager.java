package MultithreadingQuestions.SemaphoreProblem.DownloadManager;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityDownloadManager {
    private volatile Semaphore semaphore;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private ReentrantLock resizeLock = new ReentrantLock();
    private final PriorityBlockingQueue<DownloadTask> taskPriorityBlockingQueue = new PriorityBlockingQueue<>();
    int maxConcurrentDownloads;
    private final AtomicInteger activeDownloads = new AtomicInteger(0);
    private final AtomicInteger peakConcurrentDownloads = new AtomicInteger(0);

    public PriorityDownloadManager(int maxConcurrentDownloads){
        this.maxConcurrentDownloads = maxConcurrentDownloads;
        this.semaphore = new Semaphore(this.maxConcurrentDownloads,true);
        startDispatcher();
    }
    private void startDispatcher(){
        executorService.submit(()->{
            while (true){
                try {
                    DownloadTask task = taskPriorityBlockingQueue.take();
                    semaphore.acquire();

                    executorService.submit(()->{
                        try {
                          System.out.println("Downloading Task :"+task.fileName+" [Priority:"+task.priority +"]");

                          Callable<Void> downloadCallable = () -> {
                              int running = activeDownloads.incrementAndGet();
                              peakConcurrentDownloads.updateAndGet(p -> Math.max(p,running));
                              try {
                                  Thread.sleep(task.downloadTimeMillis);
                                  System.out.println("Completed Task :"+ task.fileName);
                              }finally {
                                  activeDownloads.decrementAndGet();
                              }
                              return null;
                          };
                          Future<?> future = executorService.submit(downloadCallable);
                          future.get(5, TimeUnit.SECONDS);
                        }catch (TimeoutException e) {
                            System.out.println("Timeout. Cancelling "+ task.fileName);
                        }catch (Exception e){
                            System.out.println("Failed: "+ e.getMessage());
                        }finally {
                            semaphore.release();
                        }
                    });

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void submitDownload(String fileName, Priority priority,long millisec){
        taskPriorityBlockingQueue.put(new DownloadTask(fileName,millisec, priority));
    }
    public void resizePool(int newMaxConcurrentDownloads){
        resizeLock.lock();
        try {
            int usedPermits = maxConcurrentDownloads - semaphore.availablePermits();
            int newAvailablePermits = Math.max(newMaxConcurrentDownloads-usedPermits,0);
            this.semaphore = new Semaphore(newAvailablePermits,true);
            this.maxConcurrentDownloads = newMaxConcurrentDownloads;
            System.out.println("Pool resized to "+newMaxConcurrentDownloads+" downloads");
        }finally {
            resizeLock.unlock();
        }
    }
    public void shutdown(){
        executorService.shutdownNow();
    }
    public int getPeakConcurrentDownloads(){
        return peakConcurrentDownloads.get();
    }


}
