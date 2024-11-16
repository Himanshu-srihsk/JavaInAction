package MultithreadingQuestions.ParallelSortingofLargeArrays;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelSortingofLargeArrays {
    public static void main(String[] args) {
        // Custom ThreadPoolExecutor with 4-6 threads
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4, 6, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        try {
            int[] array = {10, 7, 8, 9, 1, 5, 12, 3, 15, 6, 11, 2, 14, 4, 13};
            System.out.println("Original Array: " + Arrays.toString(array));
            MergeSort mergeSort = new MergeSort(executor);

            System.out.println("Sorted using MergeSort: " + Arrays.toString(mergeSort.sort(array)));

            QuickSort quickSort = new QuickSort(executor);
            System.out.println("Sorted using QuickSort: " + Arrays.toString(quickSort.sort(array)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor
            executor.shutdown();
        }
    }
}
