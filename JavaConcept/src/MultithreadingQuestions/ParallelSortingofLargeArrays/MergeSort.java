package MultithreadingQuestions.ParallelSortingofLargeArrays;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class MergeSort implements SortingStrategy {
    private final ExecutorService executor;

    public MergeSort(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public int[] sort(int[] nums) throws ExecutionException, InterruptedException {
        CompletableFuture<int[]> sortedArrayFuture = mergesort(0, nums.length - 1, nums);
        return  sortedArrayFuture.get();
    }

    public CompletableFuture<int[]> mergesort(int start, int end, int[] nums) {
        if (start >= end) {
            return CompletableFuture.completedFuture(new int[]{nums[start]});
        }
        int mid = (start + end) / 2;

        CompletableFuture<int[]> leftFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return sortHalf(start, mid, nums);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executor);

        CompletableFuture<int[]> rightFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return sortHalf(mid + 1, end, nums);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executor);

        return leftFuture.thenCombine(rightFuture, (left, right) -> merge(left, right));
    }
    private int[] sortHalf(int start, int end, int[] nums) throws InterruptedException {
        if (start >= end) {
            return new int[]{nums[start]};
        }

        int mid = (start + end) / 2;

        Thread.sleep(100);

        System.out.println("Thread " + Thread.currentThread().getName() + " sorting range: " + start + " to " + mid);
        int[] leftSorted = sortHalf(start, mid, nums);

        Thread.sleep(100);

        System.out.println("Thread " + Thread.currentThread().getName() + " sorting range: " + (mid + 1) + " to " + end);
        int[] rightSorted = sortHalf(mid + 1, end, nums);

        return merge(leftSorted, rightSorted);
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}
