package LowLevelDesign.Torrent;

public interface RateLimiter {
    void acquire(int bytes);
}
