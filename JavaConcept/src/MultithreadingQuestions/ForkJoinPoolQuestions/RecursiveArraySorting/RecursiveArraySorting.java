package MultithreadingQuestions.ForkJoinPoolQuestions.RecursiveArraySorting;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class RecursiveArraySorting {
    public static void main(String[] args) {
        Integer[] array = {10, 7, 8,9, 1, 5, 12, 3, 15, 6, 11, 2, 14, 4, 13,12,12,12};
        System.out.println("Original array ="+Arrays.toString(array));
        ForkJoinPool pool = new ForkJoinPool();
        SortingTask sortingTask = new SortingTask(array,0,array.length-1);
        Integer[] sortedArr = pool.invoke(sortingTask);
       System.out.println("Sorted array ="+Arrays.toString(sortedArr));
    }
}
