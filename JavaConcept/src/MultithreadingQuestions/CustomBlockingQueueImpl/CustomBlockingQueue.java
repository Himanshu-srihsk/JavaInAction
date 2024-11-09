package MultithreadingQuestions.CustomBlockingQueueImpl;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue<T> {
    private Queue<T> queue;
    private final  int capacity;
    private final  LockStrategy lock;
    private final Condition condition;

    CustomBlockingQueue(int capacity, LockStrategy lock){
        queue = new ArrayDeque<>();
        this.capacity = capacity;
        this.lock = lock;
        condition = lock.newCondition();
    }
    public void produce(T t) throws InterruptedException {
       try {
           lock.lock();
           System.out.println("produce lock acquired by: "+ Thread.currentThread().getName());
           while(queue.size() == capacity){
               try {
                   System.out.println(Thread.currentThread().getName() + ": waiting to Produce");
                   condition.await();
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
           queue.add(t);
           System.out.println(Thread.currentThread().getName() + ": Produced " + t.toString());
           Thread.sleep(1000);
           condition.signalAll();
       }catch (Exception e){

       }
       finally {
           lock.unlock();
           System.out.println("produce lock release by: "+ Thread.currentThread().getName());
       }
    }
    public T consume() throws InterruptedException {
        T val = null;
        try {
            Thread.sleep(1000);
            System.out.println("consume lock acquired by: "+ Thread.currentThread().getName());
            lock.lock();
            while(queue.isEmpty()){
                try {
                    System.out.println(Thread.currentThread().getName() + ": waiting to Consume");
                    condition.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            val = queue.poll();
            System.out.println(Thread.currentThread().getName() + ": Consumed " + val.toString());
            Thread.sleep(1000);
            condition.signalAll();
        }catch (Exception e){

        }
        finally {
           lock.unlock();
            System.out.println("consume lock release by: "+ Thread.currentThread().getName());
        }
        return val;
    }
}
