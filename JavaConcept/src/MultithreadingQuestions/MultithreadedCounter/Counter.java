package MultithreadingQuestions.MultithreadedCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private static Counter instance;
    AtomicInteger count = new AtomicInteger(0);
    private Counter(){}
    public static Counter getInstance() {
        if(instance == null){
            synchronized (Counter.class){
                if(instance == null){
                    instance = new Counter();
                }
            }
        }
        return instance;
    }
    public void increment() {
        // System.out.println("Incrementing counter by Thread " + Thread.currentThread().getName());
        count.incrementAndGet();
    }
    public int getCount() {
        return count.get();
    }

//    int count = 0;
//    public synchronized void increment() {
//        count++;
//    }
//    public int getCount() {
//        return count;
//    }
}
