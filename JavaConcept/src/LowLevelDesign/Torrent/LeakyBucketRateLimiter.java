package LowLevelDesign.Torrent;

public class LeakyBucketRateLimiter implements RateLimiter {

    private final int capacity;
    private int used;

    public LeakyBucketRateLimiter(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void acquire(int bytes) {
        if (used + bytes > capacity) {
            throw new RuntimeException("Rate exceeded");
        }
        used += bytes;
    }
}
