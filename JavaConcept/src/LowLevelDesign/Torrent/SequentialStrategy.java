package LowLevelDesign.Torrent;

public class SequentialStrategy implements ChunkSelectionStrategy {
    @Override
    public Chunk select(ChunkManager manager) {
        return manager.getNextChunk();
    }
}
