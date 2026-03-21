package MultithreadingQuestions.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketStrategy implements RateLimitStrategy{
    private final long capacity;
    private final long leakRate;

    static class Bucket {
        long water;
        long lastLeakTime;
    }

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();
    public LeakyBucketStrategy(long capacity, long leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
    }

    @Override
    public boolean allowRequest(String clientId) {

        Bucket bucket = buckets.computeIfAbsent(clientId, k -> {
            Bucket b = new Bucket();
            b.water = 0;
            b.lastLeakTime = System.nanoTime();
            return b;
        });

        synchronized (bucket) {
            leak(bucket);
            if (bucket.water < capacity) {
                bucket.water++;
                return true;
            }
            return false;
        }
    }

    private void leak(Bucket bucket) {

        long now = System.nanoTime();
        long leaked = (now - bucket.lastLeakTime) * leakRate / 1_000_000_000;
        if (leaked > 0) {
            bucket.water = Math.max(0, bucket.water - leaked);
            bucket.lastLeakTime = now;
        }
    }
}
