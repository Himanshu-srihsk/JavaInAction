package MultithreadingQuestions.MatrixRelatedQuestions.BuildParallelMatrixMultiplication;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RowBlockMultiplyTask implements Runnable{
    private final int[][] A;
    private final int[][] B;
    private final int[][] C;
    private final int startRow;
    private final int endRow; // exclusive
    private final CountDownLatch latch;
    private static final Random rand = new Random();

    public RowBlockMultiplyTask(int[][] a, int[][] b, int[][] c, int startRow, int endRow, CountDownLatch latch) {
        A = a;
        B = b;
        C = c;
        this.startRow = startRow;
        this.endRow = endRow;
        this.latch = latch;
    }

    @Override
    public void run() {
       try {
           int delay = rand.nextInt(1000);
           try {
               Thread.sleep(delay);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

           int n = A[0].length;
           int p = B[0].length;
           for(int row = startRow;row<endRow;row++){
               for(int col=0;col<p;col++){
                   int sum = 0;
                   for(int k=0;k<n;k++){
                       sum += A[row][k] * B[k][col];
                   }
                   C[row][col] = sum;
               }
           }
            System.out.println(Thread.currentThread().getName()
                    + " computed rows " + startRow + " to " + (endRow - 1)
                    + " (slept " + delay + " ms)");
       }finally {
           latch.countDown();
       }
    }
}
