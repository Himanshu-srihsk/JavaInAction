package MultithreadingQuestions.MultithreadedFileReader;

import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class PartReader implements Callable<Content> {
    String filepath;
    int start;
    int end;
    int order;

    public PartReader(String filepath, int start, int end, int order) {
        this.filepath = filepath;
        this.start = start;
        this.end = end;
        this.order = order;
    }

    @Override
    public Content call() throws Exception {
        StringBuilder partData = new StringBuilder();
        try(RandomAccessFile file = new RandomAccessFile(filepath, "r"))
        {
            file.seek(start);
            System.out.println("Started Reading " + filepath + " Reader name is "+ Thread.currentThread().getName() + " Start point is "+ start + " End point is "+ end);
            for (int i = start; i < end; i++) {
                char ch = (char) file.read();
                partData.append(ch);
                Thread.sleep(5);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Completed Reading " + filepath + " Reader name is "+ Thread.currentThread().getName() + " Start point is "+ start + " End point is "+ end);
        return new Content(partData.toString(),order);
    }
}
