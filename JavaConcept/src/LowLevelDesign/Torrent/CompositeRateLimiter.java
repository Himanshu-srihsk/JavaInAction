package LowLevelDesign.Torrent;

import java.util.List;

public class CompositeRateLimiter implements RateLimiter {

    private final List<RateLimiter> limiters;

    public CompositeRateLimiter(List<RateLimiter> limiters) {
        this.limiters = limiters;
    }

    @Override
    public void acquire(int bytes) {
        for (RateLimiter r : limiters) {
            r.acquire(bytes);
        }
    }
}
