package LowLevelDesign.Torrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.*;

public  class DownloadManager {

    private final ChunkManager cm;
    private final PeerManager pm;
    private final ChunkSelectionStrategy strategy;
    private final RateLimiter limiter;
    private final FileWriterService writer;
    private final ExecutorService executor;

    public DownloadManager(ChunkManager cm,
                           PeerManager pm,
                           ChunkSelectionStrategy strategy,
                           RateLimiter limiter,
                           FileWriterService writer,
                           int threads) {

        this.cm = cm;
        this.pm = pm;
        this.strategy = strategy;
        this.limiter = limiter;
        this.writer = writer;
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void start() {

        for (int i = 0; i < 4; i++) {
            executor.submit(new DownloadWorker(
                    cm, pm, strategy, limiter, writer
            ));
        }

        executor.shutdown();
    }
}
