package MultithreadingQuestions.DeadlockSimulationandResolution;

import java.util.concurrent.locks.ReentrantLock;

//To prevent deadlock, we can enforce a lock ordering. For example, always lock User before Profile in all threads.
public class DeadlockResolutionUsingSynchronized {
    public static void main(String[] args) {
        Profile profile1 = new Profile("Software Developer");
        User user1 = new User("Ram", 26, profile1);
        ReentrantLock lock = new ReentrantLock();


        Thread t1 = new Thread(()->{
            synchronized (user1){
                System.out.println("Thread 1: Locked User");
                try {
                    Thread.sleep(500);

                }
                catch (Exception e){

                }
                System.out.println("Thread 1: Waiting to Lock Profile1");
                synchronized (profile1){
                    System.out.println("Thread 1: Locked Profile1");

                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (user1){
                System.out.println("Thread 2: Locked User");
                try {
                    Thread.sleep(500);

                }
                catch (Exception e){

                }
                System.out.println("Thread 2: Waiting to Lock Profile1");
                synchronized (profile1){
                    System.out.println("Thread 2: Locked Profile1");

                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Main thread end");
    }
}
/*
Thread 1: Locked User
Thread 1: Waiting to Lock Profile1
Thread 1: Locked Profile1
Thread 2: Locked User
Thread 2: Waiting to Lock Profile1
Thread 2: Locked Profile1
Main thread end
 */