package MultithreadingQuestions.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowStrategy implements RateLimitStrategy{
    private final int maxRequests;
    private final long windowSizeMillis;

    private static class Window {
        AtomicInteger counter = new AtomicInteger(0);
        volatile long startTime;
    }

    private final ConcurrentHashMap<String, Window> clientWindows = new ConcurrentHashMap<>();

    public FixedWindowStrategy(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public boolean allowRequest(String clientId) {

        long now = System.currentTimeMillis();

        Window window = clientWindows.computeIfAbsent(clientId, k -> {
            Window w = new Window();
            w.startTime = now;
            return w;
        });

        synchronized (window) {
            if (now - window.startTime >= windowSizeMillis) {
                window.startTime = now;
                window.counter.set(0);
            }
            if (window.counter.incrementAndGet() <= maxRequests) {
                return true;
            }
            return false;
        }
    }
}
