package MultithreadingQuestions.ProducerConsumerProblem;
/*
 Implement a producer-consumer model where producers add items to a shared bounded buffer and consumers remove items.
 Implement this with multiple producer and consumer threads and use wait() and notifyAll() for inter-thread communication.
*/
import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class ProducerConsumer {
    public static void main(String[] args) {
        int bucketSize = 10;
       SharedResource sc = new SharedResource(bucketSize);
       int numProducerThreads = 2;
       int numConsumerThreads = 2;
       Thread[] producerThreads = new Thread[numProducerThreads];
       Thread[] consumerThreads = new Thread[numConsumerThreads];
//        for (int i = 0; i < numProducerThreads; i++) {
//            int j = i+1;
//          producerThreads[i] = new Thread(new Producer(sc), "Producer "+j);
//          producerThreads[i].start();
//        }
//        for (int i = 0; i < numConsumerThreads; i++) {
//            int j = i+1;
//            consumerThreads[i] = new Thread(new Consumer(sc),"Consumer "+j);
//            consumerThreads[i].start();
//        }


        for (int i = 0; i < numProducerThreads; i++) {
           final int temp =i+1;
           producerThreads[i] = new Thread(() ->{
               for(int j= 0; j< 5;j++){
                   try {
                       sc.Produce(j+ " by " +Thread.currentThread().getName().toString());
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           },"Producer "+temp);
           producerThreads[i].start();
       }

       for (int i = 0; i < numConsumerThreads; i++) {
           int temp = i+1;
           consumerThreads[i] = new Thread(() ->{
               for(int j= 0; j< 5;j++){
                   try{
                       sc.Consume();
                   }
                   catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           },"Consumer "+temp);
           consumerThreads[i].start();
       }

        try{
            Arrays.stream(producerThreads).forEach( producerThread -> {
                try {
                    producerThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Arrays.stream(consumerThreads).forEach( consumerThread -> {
                try {
                    consumerThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }



        System.out.println("main thread Completed");
    }
}

/*
Connected to the target VM, address: '127.0.0.1:55380', transport: 'socket'
Consumer 1: waiting to Consume
Consumer 2: waiting to Consume
Producer 2: Produced 0 by Producer 2
Consumer 1: Consumed 0 by Producer 2
Consumer 1: waiting to Consume
Producer 2: Produced 1 by Producer 2
Producer 2: Produced 2 by Producer 2
Consumer 2: Consumed 1 by Producer 2
Producer 1: Produced 0 by Producer 1
Producer 1: Produced 1 by Producer 1
Consumer 2: Consumed 2 by Producer 2
Consumer 2: Consumed 0 by Producer 1
Producer 2: Produced 3 by Producer 2
Consumer 1: Consumed 1 by Producer 1
Producer 2: Produced 4 by Producer 2
Consumer 2: Consumed 3 by Producer 2
Producer 1: Produced 2 by Producer 1
Consumer 2: Consumed 4 by Producer 2
Consumer 1: Consumed 2 by Producer 1
Producer 1: Produced 3 by Producer 1
Consumer 1: Consumed 3 by Producer 1
Consumer 1: waiting to Consume
Producer 1: Produced 4 by Producer 1
Consumer 1: Consumed 4 by Producer 1
main thread Completed
 */