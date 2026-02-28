package MultithreadingQuestions.SemaphoreProblem.DownloadManager;

import MultithreadingQuestions.SemaphoreProblem.DownloadManager.Priority;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DownloadTask implements Runnable, Comparable<DownloadTask> {

    private final Semaphore semaphore;
    private final AtomicInteger activeDownloads;
    private final AtomicInteger peakConcurrentDownloads;

    final String fileName;
    final long downloadTimeMillis;
    final Priority priority;
    final long createdAt;

    private static final AtomicLong sequence = new AtomicLong(0);

    public DownloadTask(
            String fileName,
            long downloadTimeMillis,
            Priority priority,
            Semaphore semaphore,
            AtomicInteger activeDownloads,
            AtomicInteger peakConcurrentDownloads
    ) {
        this.fileName = fileName;
        this.downloadTimeMillis = downloadTimeMillis;
        this.priority = priority;
        this.semaphore = semaphore;
        this.activeDownloads = activeDownloads;
        this.peakConcurrentDownloads = peakConcurrentDownloads;
        this.createdAt = sequence.getAndIncrement();
    }

    @Override
    public int compareTo(DownloadTask other) {
        int priorityCompare =
                Integer.compare(other.priority.getLevel(), this.priority.getLevel());
        if (priorityCompare != 0) return priorityCompare;

        return Long.compare(this.createdAt, other.createdAt);
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            int running = activeDownloads.incrementAndGet();
            peakConcurrentDownloads.updateAndGet(p -> Math.max(p, running));

            System.out.println(
                    System.nanoTime() + " | " +
                            Thread.currentThread().getName() +
                            " | START | " +
                            fileName +
                            " | Active: " + running
            );

            Thread.sleep(downloadTimeMillis);

            System.out.println("Completed: " + fileName);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            int remaining = activeDownloads.decrementAndGet();
            semaphore.release();

            System.out.println(
                    System.nanoTime() + " | RELEASE | " +
                            fileName +
                            " | Active(after release): " + remaining
            );
        }
    }
}