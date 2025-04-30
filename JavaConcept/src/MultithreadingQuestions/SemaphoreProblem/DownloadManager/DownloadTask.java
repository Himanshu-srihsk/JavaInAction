package MultithreadingQuestions.SemaphoreProblem.DownloadManager;

import java.util.concurrent.atomic.AtomicLong;

public class DownloadTask implements Comparable<DownloadTask>{
    final String fileName;
    final long downloadTimeMillis;
    final Priority priority;
    final long createdAt;
    private static final AtomicLong counter = new AtomicLong(0);

    public DownloadTask(String fileName, long downloadTimeMillis, Priority priority) {
        this.createdAt = counter.getAndIncrement();
        this.fileName = fileName;
        this.downloadTimeMillis = downloadTimeMillis;
        this.priority = priority;
    }

    @Override
    public int compareTo(DownloadTask o) {
        if(this.priority.ordinal()!=o.priority.ordinal() ){
             return this.priority.ordinal() - o.priority.ordinal();
        }
        return Long.compare(this.createdAt, o.createdAt);
    }
}
