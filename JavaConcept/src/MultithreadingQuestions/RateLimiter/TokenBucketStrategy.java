package MultithreadingQuestions.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketStrategy implements RateLimitStrategy{
    private final long capacity;
    private final long refillRate;

    static class Bucket {
        long tokens;
        long lastRefillTime;
    }

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketStrategy(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    @Override
    public boolean allowRequest(String clientId) {

        Bucket bucket = buckets.computeIfAbsent(clientId, k -> {
            Bucket b = new Bucket();
            b.tokens = capacity;
            b.lastRefillTime = System.nanoTime();
            return b;
        });
   /*
   lock is per client bucket
   So different users do not block each other.

    Example:

    user1 -> bucket1
    user2 -> bucket2
    user3 -> bucket3

    Thread execution:

    Thread1 locks bucket1
    Thread2 locks bucket2
    Thread3 locks bucket3

    No contention between different users.

    This is much better than global lock.

    What Happens With Global Lock (Bad)

        Bad design:

        synchronized(this)

        or

        synchronized(rateLimiter)

        Now all requests share one lock:

        Thread1 -> waiting
        Thread2 -> waiting
        Thread3 -> waiting

        Throughput collapses.
    */
        synchronized (bucket) {
            refill(bucket);
            if (bucket.tokens > 0) {
                bucket.tokens--;
                return true;
            }
            return false;
        }
    }

    private void refill(Bucket bucket) {
        long now = System.nanoTime();
        long tokensToAdd = (now - bucket.lastRefillTime) * refillRate / 1_000_000_000;
        if (tokensToAdd > 0) {
            bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
            bucket.lastRefillTime = now;
        }
    }
}
