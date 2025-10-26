package MultithreadingQuestions.CyclicBarrierWithCustomActions.MapReduceWithCyclicBarrier;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/*
Suppose you are processing a large file in multiple chunks using 4 threads.
After each thread finishes processing its chunk, all threads must synchronize to combine the results or proceed to the next stage together
( simmilar to MapReduce phase). A CyclicBarrier ensures all threads reach this merge point before moving on.

You have a large text file (data.txt).
You divide it into 4 chunks.
Each thread:
Reads one chunk.
Performs Map phase ->  build a local Map<String, Integer> word To Count
Ignores stop words
When all threads finish mapping, they meet at a CyclicBarrier.
The barrier’s action combines all partial maps → performs Reduce phase.
Finally, it outputs the global frequency map sorted by count in desc.
 */
public class Main {
    public static void main(String[] args) {
        int numWorkers = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numWorkers);
        String filepath = "C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\MultithreadingQuestions\\CyclicBarrierWithCustomActions\\MapReduceWithCyclicBarrier\\Data.txt";
        File file = new File(filepath);
        int division = (int)file.length() / numWorkers;
        List<WordCountWorker> wordCountWorkerList = new ArrayList<>();
        AtomicReference<Map<String,Integer>> globalMapRef = new AtomicReference<>();
        CyclicBarrier barrier = new CyclicBarrier(numWorkers,new WordReducer(wordCountWorkerList,globalMapRef));
        try {
           for(int i=0;i<numWorkers;i++){
               int start = i* division;
               int end = (i == numWorkers - 1)? (int) file.length() - 1 : (i + 1) * division - 1;
               WordCountWorker wordCountWorker = new WordCountWorker(start,end,filepath,barrier);
               wordCountWorkerList.add(wordCountWorker);
               executorService.submit(wordCountWorker);
           }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
