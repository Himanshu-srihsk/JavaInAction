package LowLevelDesign.Torrent;

public class Chunk {
    private final int id;
    private final String hash;
    private final int fileIndex;
    private final int offset;

    private volatile ChunkStatus status;

    public Chunk(int id, String hash, int fileIndex, int offset) {
        this.id = id;
        this.hash = hash;
        this.fileIndex = fileIndex;
        this.offset = offset;
        this.status = ChunkStatus.PENDING;
    }
    public int getId() { return id; }
    public String getHash() { return hash; }

    public synchronized boolean markDownloading() {
        if (status == ChunkStatus.PENDING) {
            status = ChunkStatus.DOWNLOADING;
            return true;
        }
        return false;
    }

    public void markCompleted() {
        status = ChunkStatus.COMPLETED;
    }

    public ChunkStatus getStatus() { return status; }
    public int getFileIndex() { return fileIndex; }
    public int getOffset() { return offset; }
}