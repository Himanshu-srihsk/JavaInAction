package MultithreadingQuestions.DeadlockSimulationandResolution;

import MultithreadingQuestions.DeadlockSimulationandResolution.Profile;
import MultithreadingQuestions.DeadlockSimulationandResolution.User;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockResolutionUserProfile {
    public static void main(String[] args) {
        Profile profile1 = new Profile("Software Developer");
        User user1 = new User("Ram", 26, profile1);
        ReentrantLock userLock = new ReentrantLock();
        ReentrantLock profileLock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            while (true) {
                if (userLock.tryLock()) {
                    System.out.println("Thread 1: Locked User");
                    try {
                        Thread.sleep(50);  // Simulate some work
                        System.out.println("Thread 1: Waiting to Lock Profile");
                        if (profileLock.tryLock()) {
                            try {
                                System.out.println("Thread 1: Locked Profile");
                                break;
                            } finally {
                                profileLock.unlock();
                                System.out.println("Thread 1: Unlocked Profile");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        userLock.unlock();
                        System.out.println("Thread 1: Unlocked User");
                    }
                }
                System.out.println("Thread 1: Retrying...");
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if (profileLock.tryLock()) {
                    System.out.println("Thread 2: Locked Profile");
                    try {
                        Thread.sleep(50);  // Simulate some work
                        System.out.println("Thread 2: Waiting to Lock User");
                        if (userLock.tryLock()) {
                            try {
                                System.out.println("Thread 2: Locked User");
                                break;
                            } finally {
                                userLock.unlock();
                                System.out.println("Thread 2: Unlocked User");
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        profileLock.unlock();
                        System.out.println("Thread 2: Unlocked Profile");
                    }
                }
                System.out.println("Thread 2: Retrying...");
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Main thread end");
    }
}

/*
Thread 1: Locked User
Thread 2: Locked Profile
Thread 1: Waiting to Lock Profile
Thread 2: Waiting to Lock User
Thread 2: Locked User
Thread 2: Unlocked User
Thread 2: Unlocked Profile
Thread 1: Unlocked User
Thread 1: Retrying...
Thread 1: Locked User
Thread 1: Waiting to Lock Profile
Thread 1: Locked Profile
Thread 1: Unlocked Profile
Thread 1: Unlocked User
Main thread end

 */
