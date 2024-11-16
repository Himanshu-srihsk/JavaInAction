package MultithreadingQuestions.ForkJoinPoolQuestions.RecursiveArraySorting;

import java.util.concurrent.RecursiveTask;

public class SortingTask extends RecursiveTask<Integer[]> {
    Integer[] arr;
    int start;
    int end;
    public SortingTask(Integer[] arr, int start,int end){
        this.arr = arr;
        this.start=start;
        this.end=end;
    }
    @Override
    protected Integer[] compute() {
        if(start>=end){
            return new Integer[]{arr[start]};
        }
        int mid = (start+(end-start)/2);
        SortingTask left = new SortingTask(arr,start,mid);
        SortingTask right = new SortingTask(arr,mid+1,end);
        left.fork();
        //right.fork();
        Integer[] rightArr = right.compute();
        Integer[] leftArr = left.join();  // more efficient

        return merge(leftArr,rightArr);
    }
    public Integer[] merge(Integer []left, Integer[] right){
        Integer[] merged = new Integer[left.length+right.length];
        Integer i=0,j=0,k=0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) {
            merged[k++] = left[i++];
        }
        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }
}

/*
Original array =[10, 7, 8, 9, 1, 5, 12, 3, 15, 6, 11, 2, 14, 4, 13, 12, 12, 12]
Sorted array =[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12, 12, 12, 13, 14, 15]
 */