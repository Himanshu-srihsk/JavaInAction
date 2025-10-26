package MultithreadingQuestions.CyclicBarrierWithCustomActions.MapReduceWithCyclicBarrier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class WordReducer implements Runnable{
    List<WordCountWorker> wordCountWorkerList;
    AtomicReference<Map<String, Integer>> globalMapRef;
    public WordReducer(List<WordCountWorker> wordCountWorkerList, AtomicReference<Map<String, Integer>> globalMapRef) {
        this.globalMapRef = globalMapRef;
        this.wordCountWorkerList = wordCountWorkerList;
    }

    @Override
    public void run() {
        Map<String, Integer> combinedMap = new HashMap<>();
        wordCountWorkerList.forEach(worker -> {
            mergeIntoCombinedMap(worker.localMap,combinedMap);
        });

        List<Map.Entry<String,Integer>> sortedList = new ArrayList<>(combinedMap.entrySet());
        sortedList.sort((a,b) -> b.getValue() - a.getValue());
        globalMapRef.set(combinedMap);
        persistResult(globalMapRef,sortedList);
    }

    private void persistResult(AtomicReference<Map<String, Integer>> globalMapRef, List<Map.Entry<String, Integer>> sortedList) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\MultithreadingQuestions\\CyclicBarrierWithCustomActions\\MapReduceWithCyclicBarrier\\word_count.csv"))) {
            bufferedWriter.write("word,count\n");
            sortedList.stream().forEach((Map.Entry e) -> {
                try {
                    bufferedWriter.write(e.getKey()+","+e.getValue() + "\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("\n Reduce phase completed! CSV saved. Top words:");
        sortedList.stream().limit(10).forEach(System.out::println);
    }

    private void mergeIntoCombinedMap(Map<String, Integer> localMap, Map<String, Integer> combinedMap) {
        localMap.forEach((K,V) -> combinedMap.merge(K,V,Integer::sum));
    }
}
