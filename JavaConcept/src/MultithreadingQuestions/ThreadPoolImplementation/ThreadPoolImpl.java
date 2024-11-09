package MultithreadingQuestions.ThreadPoolImplementation;

import java.util.concurrent.*;

public class ThreadPoolImpl {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), new CustomThreadFactory(), new CustomRejectedHandler());

        try {
            for (int i = 0; i < 10; i++) {
                executor.submit(new Task(i+1));
            }
        }
        finally {
            executor.shutdown();
            executor.submit(new Task(11)); // executor willnot accept any new Task. while already submitter task will continue to execute
              while (!executor.isTerminated()) {}
              // awaitTermination-> called after calling shutdown method, blocks calling thread for specific timeout and wait for executor shutdown. return true/false
            //shutdownNow -> Best effort to interrupt actively executing task. Halt the processing of task which are waiting. return list of awaiting task execution
//            if(executor.awaitTermination(5,TimeUnit.MILLISECONDS)){
//                executor.shutdownNow(); //
//            }

            System.out.println("Finished all threads");
        }
    }
}

/*
Task rejected: java.util.concurrent.FutureTask@6cc4c815[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@3a82f6ef[Wrapped task = Task ID: 8]]
Task rejected: java.util.concurrent.FutureTask@4520ebad[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@5419f379[Wrapped task = Task ID: 9]]
Task rejected: java.util.concurrent.FutureTask@7dc7cbad[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@d2cc05a[Wrapped task = Task ID: 10]]
Task rejected: java.util.concurrent.FutureTask@1753acfe[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@7c16905e[Wrapped task = Task ID: 11]]
Executing task ID: 6 on Thread-2
Executing task ID: 2 on Thread-1
Executing task ID: 1 on Thread-0
Executing task ID: 7 on Thread-3
Executing task ID: 3 on Thread-1
Executing task ID: 4 on Thread-2
Executing task ID: 5 on Thread-0
Finished all threads


or

Task rejected: java.util.concurrent.FutureTask@56f4468b[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@6cc4c815[Wrapped task = Task ID: 8]]
Executing task ID: 7 on Thread-3
Executing task ID: 6 on Thread-2
Executing task ID: 2 on Thread-1
Task rejected: java.util.concurrent.FutureTask@58651fd0[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@4520ebad[Wrapped task = Task ID: 9]]
Executing task ID: 1 on Thread-0
Task rejected: java.util.concurrent.FutureTask@5419f379[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@7dc7cbad[Wrapped task = Task ID: 10]]
Task rejected: java.util.concurrent.FutureTask@548a9f61[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@1753acfe[Wrapped task = Task ID: 11]]
task ID: 1 is Completed Thread-0
task ID: 7 is Completed Thread-3
task ID: 2 is Completed Thread-1
Executing task ID: 3 on Thread-3
Executing task ID: 5 on Thread-0
task ID: 6 is Completed Thread-2
Executing task ID: 4 on Thread-1
task ID: 5 is Completed Thread-0
task ID: 4 is Completed Thread-1
task ID: 3 is Completed Thread-3
Finished all threads

 */