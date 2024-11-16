package MultithreadingQuestions.ParallelSortingofLargeArrays;

import java.util.concurrent.ThreadPoolExecutor;

public class Invoker {
    SortingStrategy sortingStrategy;
    public Invoker(SortingStrategy sortingStrategy){
        this.sortingStrategy = sortingStrategy;
    }
    public void sort(int[] nums){
        try {
            this.sortingStrategy.sort(nums);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
