package MultithreadingQuestions.ThreadSafeSingletonPattern;

import java.util.concurrent.*;

/*
Thread-safe Singleton with Lazy Initialization
Implement a singleton class that is lazily initialized and thread-safe.
Write two versions: one using the double-checked locking pattern and another using the Bill Pugh Singleton design with static inner helper class.
Test the singleton with multiple threads to ensure only one instance is created.
 */
public class ThreadSafeSingleton {
    public static void main(String[] args) {
        BillplughSingleton instance1 = BillplughSingleton.getInstance();
        BillplughSingleton instance2 = BillplughSingleton.getInstance();
        System.out.println("Instance1 hashcode " + instance1.hashCode());
        System.out.println("Instance2 hashcode " + instance2.hashCode());
        User user = new User("Himanshu", 26);
        //using Threadpool executor
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(2,4,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());;System.out.println("Instance1 == Instance2: " + (instance1 == instance2));
        try {
          for(int i=0;i<10;i++){
              executor1.submit(() -> {
                  BillplughSingleton instance = BillplughSingleton.getInstance();
                  System.out.println("Thread " + Thread.currentThread().getId() + " created instance with hashcode: " + instance.hashCode());
                  instance.setUser(user);
                  User resUser = instance.getUser();
                  System.out.println("Thread " + Thread.currentThread().getId() + " received user: " + resUser);

              });
              Thread.sleep(1000);
          }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            executor1.shutdown();
        }

        //using Executor service
        ExecutorService executor = Executors.newFixedThreadPool(10);
        System.out.println("with DoubleCheckLocking ");
        try {

            for (int i = 0; i < 10; i++) {
                executor.execute(() -> {
                    DoubleCheckLocking instance = DoubleCheckLocking.getInstance();
                    System.out.println("Thread " + Thread.currentThread().getId() + " created instance with hashcode: " + instance.hashCode());
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            executor.shutdown();
        }

    }
}
