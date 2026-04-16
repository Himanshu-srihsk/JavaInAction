package LowLevelDesign.Torrent;

import java.util.List;
import java.util.Optional;

public class PeerManager {

    private final List<Peer> peers;

    public PeerManager(List<Peer> peers) {
        this.peers = peers;
    }

    public Optional<Peer> findPeer(int chunkId) {
        return peers.stream()
                .filter(p -> p.hasChunk(chunkId))
                .findAny();
    }
}
