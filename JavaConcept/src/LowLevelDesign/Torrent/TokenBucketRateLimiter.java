package LowLevelDesign.Torrent;

public class TokenBucketRateLimiter implements RateLimiter {

    private final int capacity;
    private int tokens;
    private long lastRefill;

    public TokenBucketRateLimiter(int capacity) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.lastRefill = System.currentTimeMillis();
    }

    @Override
    public synchronized void acquire(int bytes) {
        refill();
        while (tokens < bytes) {
            refill();
        }
        tokens -= bytes;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        if (now - lastRefill > 1000) {
            tokens = capacity;
            lastRefill = now;
        }
    }
}
