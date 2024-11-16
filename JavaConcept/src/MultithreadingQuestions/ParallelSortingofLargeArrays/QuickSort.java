package MultithreadingQuestions.ParallelSortingofLargeArrays;

import java.util.concurrent.ThreadPoolExecutor;

public class QuickSort implements SortingStrategy{
    ThreadPoolExecutor executor;
   public QuickSort(ThreadPoolExecutor executor){
       this.executor = executor;
   }
    @Override
    public  int[]  sort(int[] nums){
         this.executor = executor;
         return new int[2];
    }
}
