package MultithreadingQuestions.CountdownLatch.CustomCountdownLatchImpl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfServices = 3;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfServices);
        System.out.println("Main thread waiting for services to start...");
        Thread[] services = new Thread[numberOfServices];
        List<String> servicesName = Arrays.asList("DatabaseService", "CacheService", "MessageQueueService");
        List<Integer> startupTimesMs = Arrays.asList(2000, 3000, 4000);
        for (int i = 0; i < numberOfServices; i++) {
            services[i] = new Thread(new Service(servicesName.get(i), startupTimesMs.get(i), countDownLatch));
            services[i].start();
        }
        countDownLatch.await();
        System.out.println("All services are up. Application is starting...");
    }
}
/*
Main thread waiting for services to start...
MessageQueueService is starting...
CacheService is starting...
DatabaseService is starting...
DatabaseService is up!
CacheService is up!
MessageQueueService is up!
All services are up. Application is starting...
 */