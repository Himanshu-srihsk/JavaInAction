package LowLevelDesign.Torrent;

import java.io.RandomAccessFile;

public class FileWriterService {

    public synchronized void writeChunk(Chunk chunk, byte[] data) {
        try {
            String fileName = "file_" + chunk.getFileIndex();

            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            file.seek(chunk.getOffset());

            file.write(data);
            file.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
