package MultithreadingQuestions.CustomBlockingQueueImpl;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.StampedLock;

public class StampedLockStrategy implements LockStrategy{
    private final StampedLock lock;
    private long stamp;

    public StampedLockStrategy() {
        this.lock = new StampedLock();
    }

    @Override
    public void lock() {
        stamp = lock.writeLock();
    }

    @Override
    public void unlock() {
      lock.unlockWrite(stamp);
    }
    @Override
    public Condition newCondition() {
        // throw new UnsupportedOperationException("StampedLock does not support Condition objects");
        return new StampLockConditon();
    }

    private class StampLockConditon implements Condition{

        @Override
        public void await() throws InterruptedException {
            long stamp = lock.readLock(); // Acquire read lock for awaiting
            try {
                synchronized (this) {

                    this.wait();
                }
            } finally {
                lock.unlockRead(stamp); // Release read lock
            }
        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {
            synchronized (this) {
                this.notify();
            }
        }

        @Override
        public void signalAll() {
            synchronized (this) {
                this.notifyAll();
            }
        }
    }
}
