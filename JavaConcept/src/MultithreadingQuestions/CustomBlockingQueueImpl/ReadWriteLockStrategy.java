package MultithreadingQuestions.CustomBlockingQueueImpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockStrategy implements LockStrategy{
    private final ReadWriteLock lock;

    public ReadWriteLockStrategy() {
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void lock() {
       lock.writeLock().lock();
    }

    @Override
    public void unlock() {
      lock.writeLock().unlock();
    }
    @Override
    public Condition newCondition() {
        return lock.writeLock().newCondition();
    }
}
