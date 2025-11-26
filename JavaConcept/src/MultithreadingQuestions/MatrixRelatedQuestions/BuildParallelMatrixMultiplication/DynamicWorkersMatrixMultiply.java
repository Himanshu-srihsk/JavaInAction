package MultithreadingQuestions.MatrixRelatedQuestions.BuildParallelMatrixMultiplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DynamicWorkersMatrixMultiply {
    public static int[][] multiply(int[][] A,int[][] B,int numThreads) throws InterruptedException {
        int m = A.length;
        int n = A[0].length;
        int nB = B.length;
        int p = B[0].length;
        if (n != nB) {
            throw new IllegalArgumentException("A columns must match B rows");
        }
        int[][] C = new int[m][p];
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        int numTasks = Math.min(numThreads, m); // at most one task per row
        int chunkSize = (m + numTasks - 1) / numTasks;

        CountDownLatch countDownLatch = new CountDownLatch(numTasks); // so numTasks task will be submitted and for whom we should wait latch count to become 0
        for(int taskInd=0;taskInd<numTasks;taskInd++){
            int startRow = taskInd *  chunkSize;
            int endRow = Math.min(startRow + chunkSize,m);
            if(startRow>=endRow){
                countDownLatch.countDown(); // no work for this task
            }
            RowBlockMultiplyTask rowBlockMultiplyTask = new RowBlockMultiplyTask(A,B,C,startRow,endRow,countDownLatch);
            executorService.submit(rowBlockMultiplyTask);
        }
        countDownLatch.await();
        executorService.shutdown();
        return C;
    }
}
