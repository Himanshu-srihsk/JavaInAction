package MultithreadingQuestions.MultithreadedCounter;

import java.util.Arrays;
import java.util.stream.IntStream;

/* Implement a Multithreaded Counter
*
Write a program that uses multiple threads to increment a shared counter variable. Use synchronization techniques to ensure the counter’s integrity.
Extend the solution to make the counter thread-safe without using synchronized.
* */
public class MultithreadedCounter {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " started");
        Counter c =Counter.getInstance();
        int numThreads = 2;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                IntStream.rangeClosed(1, 100000).forEach(e -> c.increment());
            },"Thread->" + (i + 1));
            threads[i].start();
        }
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Counter value: " + c.getCount());
        System.out.println(Thread.currentThread().getName() + " ended");
    }
}
