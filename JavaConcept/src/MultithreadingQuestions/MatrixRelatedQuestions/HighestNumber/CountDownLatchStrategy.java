package MultithreadingQuestions.MatrixRelatedQuestions.HighestNumber;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchStrategy implements MatrixMaxStrategy{
    @Override
    public int findMax(int[][] matrix, int numThreads) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numThreads);
        AtomicInteger globalMax = new AtomicInteger(Integer.MIN_VALUE);
        for(int i=0;i<matrix.length;i++){
            final int rowInd = i;
            executorService.submit(()->{
                try {
                    int[] row = matrix[rowInd];
                    int rowMax = Arrays.stream(row).max().orElse(Integer.MIN_VALUE);
                    globalMax.updateAndGet(curr -> Math.max(curr,rowMax));
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        return globalMax.get();
    }
}
