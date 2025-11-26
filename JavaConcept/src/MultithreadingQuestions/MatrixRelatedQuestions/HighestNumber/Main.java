package MultithreadingQuestions.MatrixRelatedQuestions.HighestNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Find the Maximum Element in a Matrix Using Multiple Threads
User Input
Read matrix dimensions M (rows) and N (columns).
Read M × N integer values to build the matrix.
Read the number of worker threads to use.

1. using Executor Service + callable
2. Executor Service + CountDown latch + Thread equals matrix length
3. Cyclic Barrier + Thread equals matrix length
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter num of rows (M) ");
        int M = sc.nextInt();
        System.out.println("Enter num of cols (N) ");
        int N = sc.nextInt();
        System.out.println("Enter num of worker Threads ");
        int numThreads = sc.nextInt();


        int[][] matrix = new int[M][N];
        Random random = new Random();
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                matrix[i][j]= random.nextInt(10000);
            }
        }

        System.out.println("Matrix generated is ");
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("Max Element in Matrix calculation using Executor Service + callable ");

        MatrixMaxContext context = new MatrixMaxContext(new ExecutorCallableStrategy());
        int globalMax = context.executeStrategy(matrix,numThreads);
        System.out.println("Global Max is "+ globalMax);

        System.out.println("Max Element in Matrix calculation using Executor Service + CountDown latch ");

        MatrixMaxContext context1 = new MatrixMaxContext(new CountDownLatchStrategy());
        int globalMax1 = context1.executeStrategy(matrix,matrix.length);
        System.out.println("Global Max is "+ globalMax1);

        System.out.println("Max Element in Matrix calculation using  Cyclic Barrier ");
        System.out.println("CyclicBarrier has a barrier action that runs once when all threads reach the barrier and compute global max from rowMaxes.");
        MatrixMaxContext context2 = new MatrixMaxContext(new CyclicBarrierStrategy());
        int globalMax2 = context2.executeStrategy(matrix,matrix.length);
        System.out.println("Global Max is "+ globalMax2);

    }

}

/*
Enter num of rows (M)
10
Enter num of cols (N)
8
Enter num of worker Threads
3
Matrix generated is
7642 6820 8893 5854 9302 713 5553 6208
934 4452 1445 2181 9709 6295 8638 7442
7834 2825 7988 4440 6324 5202 215 4370
8257 9043 7231 7341 9441 6992 3665 1572
1671 8907 8509 9975 1431 3887 6516 3896
9850 2517 9495 947 1119 2982 5179 9959
1709 5885 8745 3732 6611 8520 4114 7203
7504 2715 1119 2812 7662 8817 3750 9305
4264 5025 3000 853 7357 2121 200 6559
8221 41 2886 8993 3296 2990 7194 9448
Max Element in Matrix calculation using Executor Service + callable
pool-1-thread-2 processed Row 1 (slept 260 ms)
pool-1-thread-3 processed Row 2 (slept 326 ms)
pool-1-thread-2 processed Row 3 (slept 48 ms)
pool-1-thread-3 processed Row 4 (slept 430 ms)
pool-1-thread-1 processed Row 0 (slept 786 ms)
pool-1-thread-3 processed Row 6 (slept 169 ms)
pool-1-thread-2 processed Row 5 (slept 756 ms)
pool-1-thread-3 processed Row 8 (slept 355 ms)
pool-1-thread-1 processed Row 7 (slept 555 ms)
pool-1-thread-2 processed Row 9 (slept 546 ms)
Global Max is 9975
Max Element in Matrix calculation using Executor Service + CountDown latch
Global Max is 9975
Max Element in Matrix calculation using  Cyclic Barrier
CyclicBarrier has a barrier action that runs once when all threads reach the barrier and compute global max from rowMaxes.
RowThread 1 finished row 1 with rowMax = 9709
RowThread 4 finished row 4 with rowMax = 9975
RowThread 5 finished row 5 with rowMax = 9959
RowThread 0 finished row 0 with rowMax = 9302
RowThread 3 finished row 3 with rowMax = 9441
RowThread 2 finished row 2 with rowMax = 7988
RowThread 7 finished row 7 with rowMax = 9305
RowThread 9 finished row 9 with rowMax = 9448
RowThread 8 finished row 8 with rowMax = 7357
RowThread 6 finished row 6 with rowMax = 8745
Barrier action computed global max = 9975
Global Max is 9975
Disconnected from the target VM, address: '127.0.0.1:59666', transport: 'socket'
 */