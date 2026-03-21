package MultithreadingQuestions.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       // RateLimiter limiter = RateLimiterFactory.tokenBucket(10, 5);
       // RateLimiter limiter = RateLimiterFactory.leakyBucket(5, 2); // capacity = 5 requests // leakRate = 2 requests/sec
       // RateLimiter limiter = RateLimiterFactory.fixedWindow(5,3000); // 5 requests allowed // per 3 second window
        RateLimiter limiter = RateLimiterFactory.slidingWindow(5, 3000);// 5 requests allowed // per 3 second sliding window

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 50; i++) {
            executor.submit(() -> {
                boolean allowed = limiter.allowRequest("user1");
                System.out.println(Thread.currentThread().getName()
                        + " -> " + allowed);
            });
            Thread.sleep(100); // simulate incoming traffic
        }
        executor.shutdown();
    }
}

/*
pool-1-thread-1 -> true
pool-1-thread-2 -> true
pool-1-thread-3 -> true
pool-1-thread-4 -> true
pool-1-thread-5 -> true
pool-1-thread-6 -> true
pool-1-thread-7 -> true
pool-1-thread-8 -> true
pool-1-thread-9 -> true
pool-1-thread-10 -> true
pool-1-thread-1 -> true
pool-1-thread-2 -> true
pool-1-thread-3 -> true
pool-1-thread-4 -> true
pool-1-thread-5 -> true
pool-1-thread-6 -> true
pool-1-thread-7 -> true
pool-1-thread-8 -> true
pool-1-thread-9 -> true
pool-1-thread-10 -> false
pool-1-thread-1 -> true
pool-1-thread-2 -> false
pool-1-thread-3 -> true
pool-1-thread-4 -> false
pool-1-thread-5 -> true
pool-1-thread-6 -> false
pool-1-thread-7 -> true
pool-1-thread-8 -> false
pool-1-thread-9 -> true
pool-1-thread-10 -> false
pool-1-thread-1 -> true
pool-1-thread-2 -> false
pool-1-thread-3 -> true
pool-1-thread-4 -> false
pool-1-thread-5 -> true
pool-1-thread-6 -> false
pool-1-thread-7 -> true
pool-1-thread-8 -> false
pool-1-thread-9 -> true
pool-1-thread-10 -> false
pool-1-thread-1 -> true
pool-1-thread-2 -> false
pool-1-thread-3 -> true
pool-1-thread-4 -> false
pool-1-thread-5 -> true
pool-1-thread-6 -> false
pool-1-thread-7 -> true
pool-1-thread-8 -> false
pool-1-thread-9 -> true
pool-1-thread-10 -> false
 */