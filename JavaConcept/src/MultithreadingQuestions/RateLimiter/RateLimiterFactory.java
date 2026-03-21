package MultithreadingQuestions.RateLimiter;

public class RateLimiterFactory {
    public static RateLimiter fixedWindow(int maxRequests, long window) {
        return new DefaultRateLimiter(new FixedWindowStrategy(maxRequests, window));
    }

    public static RateLimiter slidingWindow(int maxRequests, long window) {
        return new DefaultRateLimiter(new SlidingWindowStrategy(maxRequests, window));
    }

    public static RateLimiter tokenBucket(long capacity, long refillRate) {
        return new DefaultRateLimiter(new TokenBucketStrategy(capacity, refillRate));
    }

    public static RateLimiter leakyBucket(long capacity, long leakRate) {
        return new DefaultRateLimiter(new LeakyBucketStrategy(capacity, leakRate));
    }
}
