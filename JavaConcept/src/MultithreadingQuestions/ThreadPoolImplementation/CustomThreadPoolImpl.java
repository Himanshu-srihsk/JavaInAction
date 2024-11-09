package MultithreadingQuestions.ThreadPoolImplementation;

import java.util.concurrent.ArrayBlockingQueue;

public class CustomThreadPoolImpl {
    public static void main(String[] args) throws InterruptedException {
      CustomThreadPool executor = new CustomThreadPool(2,4, new ArrayBlockingQueue<>(3),new CustomThreadFactory(),new CustomRejectedHandler());
        try {
            for (int i = 0; i < 10; i++) {
                executor.submit(new Task(i+1));
               Thread.sleep(100);
            }
        }
        finally {
            executor.shutdown();
            executor.submit(new Task(11));
            while (!executor.isTerminated()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Finished all threads");
        }
    }
}
