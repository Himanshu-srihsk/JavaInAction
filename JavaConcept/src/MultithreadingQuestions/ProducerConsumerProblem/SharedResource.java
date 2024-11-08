package MultithreadingQuestions.ProducerConsumerProblem;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class SharedResource{
    private Queue<String> bucket;
    private final Integer size;
    private final Object lock = new Object();
    SharedResource(int size){
        this.size = size;
        bucket = new ArrayDeque<>();
    }
    public  void  Produce(String value) throws InterruptedException {
        synchronized (lock){
            while (bucket.size() == size){
                try {
                    System.out.println(Thread.currentThread().getName() + ": waiting to Produce");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            bucket.add(value);
            System.out.println(Thread.currentThread().getName() + ": Produced " + value);
            Thread.sleep(1000);
            lock.notifyAll();
       }
    }
    public  void Consume() throws InterruptedException {

        synchronized (lock){
            while (bucket.isEmpty()){
                try {
                    System.out.println(Thread.currentThread().getName() + ": waiting to Consume");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            String value =bucket.poll();
            System.out.println(Thread.currentThread().getName() + ": Consumed " + value);
            Thread.sleep(1000);
            lock.notifyAll();
        }
    }
}
