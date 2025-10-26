package MultithreadingQuestions.CyclicBarrierWithCustomActions.CustomCyclicBarrierEx2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class CyclicBarrier {
    int numParties;
    Runnable barrierAction;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    AtomicInteger waitingParties;
    CyclicBarrier(int  numParties, Runnable runnable){
        this.numParties = numParties;
        this.barrierAction = runnable;
        waitingParties = new AtomicInteger(0);
    }

    public void await() throws InterruptedException {
        lock.lock();
        try {
            waitingParties.incrementAndGet();
            if(waitingParties.get() == numParties){
                if(barrierAction!=null){
                    barrierAction.run();
                }
                waitingParties.set(0);
                condition.signalAll();
            }else{
                condition.await();
            }
        }finally {
            lock.unlock();
        }

    }
}
