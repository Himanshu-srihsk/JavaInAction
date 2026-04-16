package LowLevelDesign.Torrent;

public interface ChunkSelectionStrategy {
    Chunk select(ChunkManager manager);
}
