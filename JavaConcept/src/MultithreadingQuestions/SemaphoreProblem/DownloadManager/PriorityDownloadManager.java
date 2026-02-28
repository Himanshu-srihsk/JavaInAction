package MultithreadingQuestions.SemaphoreProblem.DownloadManager;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityDownloadManager {
    private volatile Semaphore semaphore;
    private ThreadPoolExecutor executor;
    private ReentrantLock resizeLock = new ReentrantLock();
    private final AtomicInteger maxConcurrentDownloads;
    private final AtomicInteger activeDownloads = new AtomicInteger(0);
    private final AtomicInteger peakConcurrentDownloads = new AtomicInteger(0);

    public PriorityDownloadManager(int maxConcurrentDownloads){
        this.maxConcurrentDownloads = new AtomicInteger(maxConcurrentDownloads);
        this.semaphore = new Semaphore(maxConcurrentDownloads,true);
        this.executor = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>()
        );
    }

    public void submitDownload(String fileName, Priority priority, long durationMillis) {
        DownloadTask task = new DownloadTask(
                fileName,
                durationMillis,
                priority,
                semaphore,
                activeDownloads,
                peakConcurrentDownloads
        );

        executor.execute(task);
    }
    public void resizePool(int newMaxConcurrentDownloads){


        resizeLock.lock();
        try {
            int oldMax = this.maxConcurrentDownloads.get();
            if(newMaxConcurrentDownloads <= oldMax){
                System.out.println("only increase allowed");
                return;
            }
            int diff = newMaxConcurrentDownloads - oldMax;
            semaphore.release(diff);
            maxConcurrentDownloads.set(newMaxConcurrentDownloads);
            System.out.println("Pool resized to "+newMaxConcurrentDownloads+" downloads");


        }finally {
            resizeLock.unlock();
        }
    }
    public void shutdown(){
        executor.shutdownNow();
    }
    public int getPeakConcurrentDownloads(){
        return peakConcurrentDownloads.get();
    }


}

/*
Ex:
Total tasks submitted: 100
ThreadPool size: 10
Semaphore permits: 3

->
User submit (100 tasks)
ThreadPoolExecutor
     ->  10 worker threads
     -> PriorityBlockingQueue (remaining tasks)
Worker thread picks task
Each of the 10 worker threads does: semaphore.acquire()
Since permits = 3:
First 3 threads succeed
Remaining 7 threads BLOCK at acquire()
Download runs
semaphore.release()

Suppose 1 download completes:
Now:
One of the 7 blocked threads wakes up
That thread continues running its task
Active downloads still = 3
So concurrency never exceeds 3.



After Some Tasks Finish
Eventually:
Some of the original 10 threads finish
They go back to thread pool
They pick next tasks from PriorityBlockingQueue
They always pick the highest priority task available


so

when VIP Task Arrives Late?
Lets say:
90 LOW tasks waiting
VIP task comes
It gets inserted into queue at top position.
When any worker thread becomes free:
It picks VIP first.

 */
