package LowLevelDesign.Torrent;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChunkManager {

    private final ConcurrentMap<Integer, Chunk> chunkMap;

    public ChunkManager(List<Chunk> chunks) {
        chunkMap = new ConcurrentHashMap<>();
        for (Chunk c : chunks) {
            chunkMap.put(c.getId(), c);
        }
    }

    public Chunk getNextChunk() {
        for (Chunk chunk : chunkMap.values()) {
            if (chunk.markDownloading()) {
                return chunk;
            }
        }
        return null;
    }

    public void markCompleted(int chunkId) {
        chunkMap.get(chunkId).markCompleted();
    }

    public boolean isDownloadComplete() {
        return chunkMap.values().stream()
                .allMatch(c -> c.getStatus() == ChunkStatus.COMPLETED);
    }
}
