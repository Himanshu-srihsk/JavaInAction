package LowLevelDesign.Torrent;

import java.util.List;

public class TorrentMetadata {

    private final List<TorrentFile> files;
    private final List<Chunk> chunks;

    public TorrentMetadata(List<TorrentFile> files, List<Chunk> chunks) {
        this.files = files;
        this.chunks = chunks;
    }

    public List<TorrentFile> getFiles() { return files; }
    public List<Chunk> getChunks() { return chunks; }
}
