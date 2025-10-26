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
            left.fork();  // run left task asynchronously
           // right.fork();
            int rightResult = right.compute();  // use current thread for right
            int leftResult = left.join();  // wait for left to finish

            System.out.println("Thread " + Thread.currentThread().getName() + " merged range [" + start + ", " + end + "] with sum = " + (leftResult + rightResult));
            return  leftResult+rightResult;
        }

    }

}


/*
--------------------------------------------------------------------------------------------------------------------
if both the tasks are forked
left.fork();
right.fork();
int leftResult = left.join();
int rightResult = right.join();
- Less Efficient
->

Then both tasks are submitted asynchronously to the ForkJoinPool.
This increases scheduling overhead as:
Both tasks are queued and may be stolen by other threads.
The current thread does nothing useful while waiting for both joins.
->  acting like a manager waiting for two workers ( left Thread and right thread) instead of contributing yourself (Current Thread).
--------------------------------------------------------------------------------------------------------------------
instead use:
left.fork();          // run left task asynchronously
int rightResult = right.compute();  // use current thread for right
int leftResult = left.join();       // wait for left to finish
- More Efficient
This is more efficient because:

The current thread does useful work by computing right directly.
The left task runs in parallel on another thread.
There is less task submission overhead (only one fork instead of two).
-> both(current thread and another thread) work at the same time without wasting time waiting
--------------------------------------------------------------------------------------------------------------------
 */