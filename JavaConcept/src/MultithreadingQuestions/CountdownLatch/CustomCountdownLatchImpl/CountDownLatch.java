package MultithreadingQuestions.CountdownLatch.CustomCountdownLatchImpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class CountDownLatch {
    int count;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition countZero = lock.newCondition();
    public CountDownLatch(int numberOfServices) {
        this.count = numberOfServices;
    }

    public void await() throws InterruptedException {
        lock.lock();
        try {
            while (count>0){
                countZero.await();
            }
        }finally {
            lock.unlock();
        }
    }

    public void countDown() {
        lock.lock();
        try {
            if (count > 0){
               count--;
                System.out.println(Thread.currentThread().getName() + " decremented latch count, remaining: " + count);
                if (count == 0){
                    countZero.signalAll();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
