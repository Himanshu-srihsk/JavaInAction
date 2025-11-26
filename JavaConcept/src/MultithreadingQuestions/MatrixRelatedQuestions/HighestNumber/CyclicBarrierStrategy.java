package MultithreadingQuestions.MatrixRelatedQuestions.HighestNumber;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierStrategy implements MatrixMaxStrategy{
    @Override
    public int findMax(int[][] matrix, int numThreads) throws Exception {
        int rows = matrix.length;
        int[] rowMaxes = new int[rows];
        final int[] globalMaxHolder = new int[1];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(rows,()->{
            int globalMax = Integer.MIN_VALUE;
            for(int rowMax: rowMaxes){
                if(rowMax > globalMax){
                    globalMax = rowMax;
                }
            }
            globalMaxHolder[0] = globalMax;
            System.out.println("Barrier action computed global max = " + globalMax);
        });

        Thread[] threads = new Thread[rows];

        for(int i=0;i<matrix.length;i++){
            int rowInd = i;
            threads[i] = new Thread(() -> {
                int rowMax = Integer.MIN_VALUE;
                for(int val: matrix[rowInd]){
                    rowMax = Math.max(rowMax,val);
                }
                rowMaxes[rowInd] = rowMax;
                System.out.println(Thread.currentThread().getName() +
                        " finished row " + rowInd + " with rowMax = " + rowMax);
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"RowThread "+rowInd);
            threads[rowInd].start();
        }
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return globalMaxHolder[0];
    }
}
