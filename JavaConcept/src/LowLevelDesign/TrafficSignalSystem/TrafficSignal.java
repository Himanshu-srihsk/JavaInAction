package LowLevelDesign.TrafficSignalSystem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficSignal {

    private final Lock lock = new ReentrantLock();

    private final Condition northSouthCondition = lock.newCondition();
    private final Condition eastWestCondition = lock.newCondition();

    private boolean isNorthSouthGreen = false;

    public void setNorthSouthGreen() {
        lock.lock();
        try {
            isNorthSouthGreen = true;
            northSouthCondition.signalAll(); // wake all NS cars
        } finally {
            lock.unlock();
        }
    }

    public void setEastWestGreen() {
        lock.lock();
        try {
            isNorthSouthGreen = false;
            eastWestCondition.signalAll(); // wake all EW cars
        } finally {
            lock.unlock();
        }
    }

    public void waitForGreen(Direction dir) throws InterruptedException {
        lock.lock();
        try {
            while (!canPass(dir)) {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    northSouthCondition.await();
                } else {
                    eastWestCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private boolean canPass(Direction dir) {
        if (dir == Direction.NORTH || dir == Direction.SOUTH) {
            return isNorthSouthGreen;
        } else {
            return !isNorthSouthGreen;
        }
    }
}
