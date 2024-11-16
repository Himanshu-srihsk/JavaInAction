package MultithreadingQuestions.ParallelSortingofLargeArrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public interface SortingStrategy {
    int[]  sort(int[] nums) throws ExecutionException, InterruptedException;
}
