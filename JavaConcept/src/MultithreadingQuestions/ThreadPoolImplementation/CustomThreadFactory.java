package MultithreadingQuestions.ThreadPoolImplementation;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(false);
        thread.setPriority(Thread.MIN_PRIORITY);
        return thread;
    }
}