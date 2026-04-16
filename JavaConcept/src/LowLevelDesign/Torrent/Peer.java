package LowLevelDesign.Torrent;

import java.util.Set;

public class Peer {
    private final String id;
    private final Set<Integer> chunks;

    public Peer(String id, Set<Integer> chunks) {
        this.id = id;
        this.chunks = chunks;
    }

    public boolean hasChunk(int chunkId) {
        return chunks.contains(chunkId);
    }

    public byte[] downloadChunk(int chunkId) {
        System.out.println("Peer " + id + " serving chunk " + chunkId);
        return ("DATA_" + chunkId).getBytes();
    }
}