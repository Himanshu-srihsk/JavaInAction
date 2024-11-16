package MultithreadingQuestions.ForkJoinPoolQuestions.RecursiveSumOfArray;

import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {
    Integer[] arr;
    int start;
    int end;
    SumTask(Integer[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if((end-start) <= 4){
            int totalsum=0;
            for(int i=start;i<end;i++){
                totalsum+=arr[i];
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " computed range [" + start + ", " + end + "] with sum = " + totalsum);
            return  totalsum;
        }
        else{
            int mid = (start+(end-start)/2);
            SumTask left = new SumTask(arr,start,mid);
            SumTask right = new SumTask(arr,mid,end);
            left.fork();
           // right.fork();
            int rightResult = right.compute();
            int leftResult = left.join();

            System.out.println("Thread " + Thread.currentThread().getName() + " merged range [" + start + ", " + end + "] with sum = " + (leftResult + rightResult));
            return  leftResult+rightResult;
        }

    }

}
