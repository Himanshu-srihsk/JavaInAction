package MultithreadingQuestions.CustomBlockingQueueImpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockStrategy implements LockStrategy{
    private final ReentrantLock lock;
    public ReentrantLockStrategy() {
        this.lock = new ReentrantLock();
    }
    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void unlock() {
    lock.unlock();
    }

    @Override
    public Condition newCondition() {
        return lock.newCondition();
    }

}
