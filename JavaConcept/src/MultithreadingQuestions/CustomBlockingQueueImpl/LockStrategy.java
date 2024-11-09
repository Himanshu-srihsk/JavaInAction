package MultithreadingQuestions.CustomBlockingQueueImpl;

import java.util.concurrent.locks.Condition;

interface LockStrategy {
    void lock();
    void unlock();
    Condition newCondition();
}
