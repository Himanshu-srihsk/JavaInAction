package MultithreadingQuestions.ForkJoinPoolQuestions.RecursiveSumOfArray;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class RecursiveSumOfArray {
    public static Integer findsum(Integer[] array){

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(array,0,array.length);
        Integer sum = pool.invoke(task);

       return sum;
    }
    public static void main(String[] args) {
        Integer[] array = {10, 7, 8,9, 1, 5, 12, 3, 15, 6, 11, 2, 14, 4, 13,12,12,12};
        System.out.println(findsum(array));
    }
}

/*
Thread ForkJoinPool-1-worker-3 computed range [0, 4] with sum = 34
Thread ForkJoinPool-1-worker-6 computed range [4, 6] with sum = 6
Thread ForkJoinPool-1-worker-4 computed range [9, 13] with sum = 33
Thread ForkJoinPool-1-worker-5 computed range [13, 15] with sum = 17
Thread ForkJoinPool-1-worker-2 computed range [6, 9] with sum = 30
Thread ForkJoinPool-1-worker-1 computed range [15, 18] with sum = 36
Thread ForkJoinPool-1-worker-1 merged range [13, 18] with sum = 53
Thread ForkJoinPool-1-worker-2 merged range [4, 9] with sum = 36
Thread ForkJoinPool-1-worker-1 merged range [9, 18] with sum = 86
Thread ForkJoinPool-1-worker-2 merged range [0, 9] with sum = 70
Thread ForkJoinPool-1-worker-1 merged range [0, 18] with sum = 156
156
 */