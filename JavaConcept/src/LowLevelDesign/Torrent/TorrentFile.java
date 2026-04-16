package LowLevelDesign.Torrent;

import java.util.List;

public class TorrentFile {
    private final String fileName;
    private final int size;
    private final List<Integer> chunkIds;

    public TorrentFile(String fileName, int size, List<Integer> chunkIds) {
        this.fileName = fileName;
        this.size = size;
        this.chunkIds = chunkIds;
    }

    public List<Integer> getChunkIds() {
        return chunkIds;
    }

    public String getFileName() {
        return fileName;
    }
}
