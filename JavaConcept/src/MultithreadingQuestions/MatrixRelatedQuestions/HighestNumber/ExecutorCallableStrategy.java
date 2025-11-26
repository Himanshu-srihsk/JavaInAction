package MultithreadingQuestions.MatrixRelatedQuestions.HighestNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCallableStrategy implements MatrixMaxStrategy{
    @Override
    public int findMax(int[][] matrix, int numThreads) throws Exception {
        List<Future<Integer>> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for(int i=0;i<matrix.length;i++){
            futureList.add(executorService.submit(new RowMaxTask(matrix[i],i)));
        }
        int globalMax = Integer.MIN_VALUE;
        for(Future<Integer> future: futureList){
            globalMax = Math.max(globalMax,future.get());
        }
        executorService.shutdown();
        return globalMax;
    }
}
