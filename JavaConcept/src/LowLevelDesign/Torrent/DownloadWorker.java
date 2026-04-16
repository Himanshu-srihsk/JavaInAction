package LowLevelDesign.Torrent;

import java.util.Optional;

public class DownloadWorker implements Runnable {

    private final ChunkManager cm;
    private final PeerManager pm;
    private final ChunkSelectionStrategy strategy;
    private final RateLimiter limiter;
    private final FileWriterService writer;

    public DownloadWorker(ChunkManager cm,
                          PeerManager pm,
                          ChunkSelectionStrategy strategy,
                          RateLimiter limiter,
                          FileWriterService writer) {
        this.cm = cm;
        this.pm = pm;
        this.strategy = strategy;
        this.limiter = limiter;
        this.writer = writer;
    }

    @Override
    public void run() {

        while (!cm.isDownloadComplete()) {

            Chunk chunk = strategy.select(cm);
            if (chunk == null) continue;

            Optional<Peer> peerOpt = pm.findPeer(chunk.getId());
            if (peerOpt.isEmpty()) continue;

            Peer peer = peerOpt.get();

            limiter.acquire(10);

            byte[] data = peer.downloadChunk(chunk.getId());

            if (verify(data, chunk.getHash())) {
                writer.writeChunk(chunk, data);
                cm.markCompleted(chunk.getId());
                System.out.println("Chunk " + chunk.getId() + " DONE");
            }
        }
    }

    private boolean verify(byte[] data, String hash) {
        return true;
    }
}
