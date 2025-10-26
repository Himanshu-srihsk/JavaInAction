package MultithreadingQuestions.CyclicBarrierWithCustomActions.MapReduceWithCyclicBarrier;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

public class WordCountWorker implements Runnable{
    int start;
    int end;
    String filepath;
    Map<String,Integer> localMap;
    CyclicBarrier barrier;
    public WordCountWorker(int start, int end, String filepath,CyclicBarrier barrier) {
        this.start = start;
        this.end = end;
        this.filepath = filepath;
        localMap = new HashMap<>();
        this.barrier = barrier;
    }

    @Override
    public void run() {
       try(RandomAccessFile file = new RandomAccessFile(filepath,"r")){
           file.seek(start);
           System.out.println("Started Reading " + filepath + " Reader name is "+ Thread.currentThread().getName() + " Start point is "+ start + " End point is "+ end);

           StringBuilder wordBuilder = new StringBuilder();
           long currPos = file.getFilePointer();
           while (currPos < end && currPos < file.length()){
               char ch = (char) file.read();
               if(Character.isLetter(ch)){
                   wordBuilder.append(ch);
               }else {
                   if(wordBuilder.length()>0){
                       String word = wordBuilder.toString();
                       if(StopWords.isValidWord(word)){
                           localMap.merge(StopWords.cleanWord(word),1,(a,b) -> a+b);
                       }
                       wordBuilder.setLength(0);
                   }
               }
               currPos = file.getFilePointer();
           }

           if (wordBuilder.length() > 0) {
               String word = wordBuilder.toString();
               if (StopWords.isValidWord(word)) {
                   localMap.merge(StopWords.cleanWord(word), 1, Integer::sum);
               }
           }

           System.out.println(Thread.currentThread().getName() + " finished mapping. Waiting at barrier...");
           barrier.await();
       }catch (Exception e){
          e.printStackTrace();
       }
    }
}
