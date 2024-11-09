package MultithreadingQuestions.CustomBlockingQueueImpl;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/*
Custom Blocking Queue with Condition Variables
Create a BlockingQueue implementation using Condition objects from ReentrantLock, ReadWriteLock, StampLock instead of synchronized and wait()/notify().
use Strategy Design pattern to implement run time algorithm fo locking and synchronization
Demonstrate producers adding items and consumers taking items.
 */
public class CustomBlockingQueueImpl {
    public static void main(String[] args) {
       // ReentrantLock lock = new ReentrantLock();
        LockStrategy lock = new ReentrantLockStrategy();
         CustomBlockingQueue<Integer> customBlockingQueue = new CustomBlockingQueue<>(5, lock);
         int numThreads = 4;
         Thread[] prducers = new Thread[numThreads];
         Thread[] consumers = new Thread[numThreads];


         for(int i=0;i<numThreads;i++){
             prducers[i] = new Thread(() ->{
                 for(int j=0;j<10;j++){
                     Integer product = j+1;
                     try {
                         customBlockingQueue.produce(product);
                     } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                     }
                 }
             });
             prducers[i].start();
         }
        for(int i=0;i<numThreads;i++){
            consumers[i] = new Thread(() ->{
                for(int j=0;j<10;j++){
                    try {
                        customBlockingQueue.consume();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            consumers[i].start();
        }

        Arrays.stream(prducers).forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        Arrays.stream(consumers).forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println("Main thread end.");
    }
}

/*
produce lock acquired by: Thread-0
Thread-0: Produced 1
consume lock acquired by: Thread-5
consume lock acquired by: Thread-6
consume lock acquired by: Thread-7
Thread-5: Consumed 1
produce lock release by: Thread-0
consume lock acquired by: Thread-4
produce lock acquired by: Thread-2
Thread-2: Produced 1
consume lock release by: Thread-5
consume lock acquired by: Thread-5
produce lock acquired by: Thread-3
produce lock release by: Thread-2
Thread-3: Produced 1
produce lock acquired by: Thread-1
Thread-1: Produced 1
produce lock release by: Thread-3
produce lock release by: Thread-1
Thread-6: Consumed 1
consume lock release by: Thread-6
Thread-7: Consumed 1
produce lock acquired by: Thread-0
consume lock acquired by: Thread-6
Thread-0: Produced 2
consume lock release by: Thread-7
consume lock acquired by: Thread-7
Thread-4: Consumed 1
produce lock release by: Thread-0
consume lock release by: Thread-4
Thread-5: Consumed 2
consume lock acquired by: Thread-4
produce lock acquired by: Thread-2
consume lock release by: Thread-5
Thread-2: Produced 2
produce lock release by: Thread-2
produce lock acquired by: Thread-3
Thread-3: Produced 2
consume lock acquired by: Thread-5
produce lock release by: Thread-3
produce lock acquired by: Thread-1
Thread-1: Produced 2
produce lock release by: Thread-1
Thread-6: Consumed 2
consume lock release by: Thread-6
Thread-7: Consumed 2
consume lock acquired by: Thread-6
produce lock acquired by: Thread-0
Thread-0: Produced 3
consume lock release by: Thread-7
consume lock acquired by: Thread-7
Thread-4: Consumed 2
produce lock release by: Thread-0
consume lock release by: Thread-4
produce lock acquired by: Thread-2
Thread-2: Produced 3
consume lock acquired by: Thread-4
produce lock release by: Thread-2
Thread-4: Consumed 3
consume lock release by: Thread-4
Thread-5: Consumed 3
produce lock acquired by: Thread-3
Thread-3: Produced 3
consume lock release by: Thread-5
consume lock acquired by: Thread-4
consume lock acquired by: Thread-5
Thread-5: Consumed 3
produce lock release by: Thread-3
consume lock release by: Thread-5
produce lock acquired by: Thread-1
Thread-1: Produced 3
consume lock acquired by: Thread-5
Thread-6: Consumed 3
produce lock release by: Thread-1
consume lock release by: Thread-6
Thread-7: waiting to Consume
produce lock acquired by: Thread-0
Thread-0: Produced 4
consume lock acquired by: Thread-6
produce lock acquired by: Thread-2
Thread-2: Produced 4
produce lock release by: Thread-0
Thread-4: Consumed 4
produce lock release by: Thread-2
consume lock release by: Thread-4
produce lock acquired by: Thread-3
Thread-3: Produced 4
consume lock acquired by: Thread-4
produce lock acquired by: Thread-1
produce lock release by: Thread-3
Thread-1: Produced 4
produce lock release by: Thread-1
produce lock acquired by: Thread-1
Thread-1: Produced 5
produce lock release by: Thread-1
produce lock acquired by: Thread-1
Thread-1: Produced 6
produce lock release by: Thread-1
Thread-5: Consumed 4
Thread-6: Consumed 4
consume lock release by: Thread-5
consume lock acquired by: Thread-5
Thread-7: Consumed 4
consume lock release by: Thread-6
consume lock release by: Thread-7
produce lock acquired by: Thread-0
Thread-0: Produced 5
consume lock acquired by: Thread-6
consume lock acquired by: Thread-7
produce lock release by: Thread-0
produce lock acquired by: Thread-2
Thread-2: Produced 5
produce lock release by: Thread-2
Thread-4: Consumed 5
produce lock acquired by: Thread-3
Thread-3: Produced 5
consume lock release by: Thread-4
consume lock acquired by: Thread-4
produce lock acquired by: Thread-1
Thread-1: Produced 7
produce lock release by: Thread-3
Thread-5: Consumed 6
produce lock release by: Thread-1
Thread-6: Consumed 5
consume lock release by: Thread-5
consume lock acquired by: Thread-5
Thread-7: Consumed 5
consume lock release by: Thread-6
consume lock acquired by: Thread-6
produce lock acquired by: Thread-0
Thread-0: Produced 6
consume lock release by: Thread-7
consume lock acquired by: Thread-7
produce lock acquired by: Thread-2
Thread-2: Produced 6
produce lock release by: Thread-0
produce lock release by: Thread-2
Thread-4: Consumed 5
consume lock release by: Thread-4
produce lock acquired by: Thread-3
Thread-3: Produced 6
consume lock acquired by: Thread-4
produce lock acquired by: Thread-1
Thread-1: Produced 8
produce lock release by: Thread-3
produce lock release by: Thread-1
Thread-5: Consumed 7
consume lock release by: Thread-5
Thread-6: Consumed 6
consume lock release by: Thread-6
consume lock acquired by: Thread-5
Thread-7: Consumed 6
consume lock acquired by: Thread-6
consume lock release by: Thread-7
produce lock acquired by: Thread-0
Thread-0: Produced 7
consume lock acquired by: Thread-7
produce lock acquired by: Thread-2
Thread-2: Produced 7
produce lock release by: Thread-0
Thread-4: Consumed 6
produce lock release by: Thread-2
consume lock release by: Thread-4
produce lock acquired by: Thread-3
Thread-3: Produced 7
consume lock acquired by: Thread-4
Thread-4: Consumed 8
produce lock release by: Thread-3
produce lock acquired by: Thread-1
Thread-1: Produced 9
consume lock release by: Thread-4
Thread-5: Consumed 7
produce lock release by: Thread-1
consume lock acquired by: Thread-4
consume lock release by: Thread-5
Thread-6: Consumed 7
consume lock acquired by: Thread-5
consume lock release by: Thread-6
Thread-7: Consumed 7
consume lock acquired by: Thread-6
produce lock acquired by: Thread-0
Thread-0: Produced 8
consume lock release by: Thread-7
consume lock acquired by: Thread-7
produce lock acquired by: Thread-2
Thread-2: Produced 8
produce lock release by: Thread-0
produce lock acquired by: Thread-3
Thread-3: Produced 8
produce lock release by: Thread-2
produce lock acquired by: Thread-1
produce lock release by: Thread-3
Thread-1: Produced 10
produce lock release by: Thread-1
Thread-4: Consumed 9
consume lock release by: Thread-4
Thread-5: Consumed 8
consume lock acquired by: Thread-4
consume lock release by: Thread-5
Thread-6: Consumed 8
consume lock acquired by: Thread-5
consume lock release by: Thread-6
Thread-7: Consumed 8
consume lock acquired by: Thread-6
produce lock acquired by: Thread-0
Thread-0: Produced 9
consume lock release by: Thread-7
produce lock acquired by: Thread-2
Thread-2: Produced 9
consume lock acquired by: Thread-7
produce lock release by: Thread-0
produce lock acquired by: Thread-3
Thread-3: Produced 9
produce lock release by: Thread-2
produce lock release by: Thread-3
Thread-4: Consumed 10
consume lock release by: Thread-4
Thread-5: Consumed 9
consume lock release by: Thread-5
Thread-6: Consumed 9
consume lock release by: Thread-6
Thread-7: Consumed 9
consume lock release by: Thread-7
produce lock acquired by: Thread-0
Thread-0: Produced 10
consume lock acquired by: Thread-6
consume lock acquired by: Thread-7
produce lock acquired by: Thread-2
Thread-2: Produced 10
produce lock release by: Thread-0
produce lock release by: Thread-2
produce lock acquired by: Thread-3
Thread-3: Produced 10
produce lock release by: Thread-3
Thread-6: Consumed 10
consume lock release by: Thread-6
Thread-7: Consumed 10
consume lock release by: Thread-7
consume lock acquired by: Thread-7
Thread-7: Consumed 10
consume lock release by: Thread-7
Main thread end.
 */