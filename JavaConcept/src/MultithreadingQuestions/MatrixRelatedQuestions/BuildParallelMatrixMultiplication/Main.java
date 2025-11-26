package MultithreadingQuestions.MatrixRelatedQuestions.BuildParallelMatrixMultiplication;

import java.util.Random;
import java.util.Scanner;

/*
perform Matrix Multiplication using multiple threads concurrently
matrix A is M * N
matrix  B is N * P
Result matrix C is M * P
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int M = 8;  // rows of A
        int N = 5;  // cols of A, rows of B
        int P = 7;  // cols of B

        int[][] A = randomMatrix(M, N);
        int[][] B = randomMatrix(N, P);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter num of worker Threads ");
        int numThreads = sc.nextInt();
        System.out.println("Matrix A is ");
        printMatrix(A);
        System.out.println("Matrix B is ");
        printMatrix(B);
        int[][] result = DynamicWorkersMatrixMultiply.multiply(A,B,numThreads);
        System.out.println("Result Matrix is ");
        printMatrix(result);

    }

    private static int[][] randomMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] m = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = rand.nextInt(10);
            }
        }
        return m;
    }
    private static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}
/*
Enter num of worker Threads
3
Matrix A is
   1   3   4   3   0
   9   8   0   1   5
   2   5   6   5   4
   4   7   1   2   7
   0   1   7   7   8
   0   7   0   8   4
   3   5   6   5   8
   6   8   3   1   9

Matrix B is
   2   5   9   2   8   1   4
   2   1   6   3   8   9   5
   1   3   4   9   9   7   5
   3   0   3   3   1   4   9
   7   8   1   7   8   1   8

pool-1-thread-1 computed rows 0 to 2 (slept 87 ms)
pool-1-thread-2 computed rows 3 to 5 (slept 184 ms)
pool-1-thread-3 computed rows 6 to 7 (slept 823 ms)
Result Matrix is
  21  20  52  56  71  68  66
  72  93 137  80 177  90 125
  63  65  91 116 147 113 140
  78  86  95  93 155  89 130
  86  86  63 143 142  94 167
  66  39  70  73  96  99 139
  93 102 104 146 187 118 176
  97 119 126 129 212 112 160


 */
