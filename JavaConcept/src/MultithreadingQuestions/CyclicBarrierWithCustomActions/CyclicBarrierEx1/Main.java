package MultithreadingQuestions.CyclicBarrierWithCustomActions.CyclicBarrierEx1;


import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
    Implement a Cyclic Barrier with Custom Actions
	Add a feature where, once the barrier is reached, a specified action (ex: printing a message) is performed.
 */
public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("All threads reached the barrier! Proceeding together...\n");
        int numThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numThreads,runnable);
        Thread[] threads = new Thread[numThreads];
        for(int i=0;i<numThreads;i++){
            int threadId = i+1;
            threads[i] = new Thread(() -> {
              System.out.println("Thread "+threadId+" doing initial work...");
                try {
                    Thread.sleep(1000L * threadId);
                    System.out.println("Thread "+threadId+" has reached Barrier");
                    barrier.await();
                    System.out.println("Thread " + threadId + " continues after barrier...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }

        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

/*
Thread 3 doing initial work...
Thread 2 doing initial work...
Thread 1 doing initial work...
Thread 1 has reached Barrier
Thread 2 has reached Barrier
Thread 3 has reached Barrier
All threads reached the barrier! Proceeding together...

Thread 1 continues after barrier...
Thread 2 continues after barrier...
Thread 3 continues after barrier...

 */