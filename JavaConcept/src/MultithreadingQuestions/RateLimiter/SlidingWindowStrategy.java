package MultithreadingQuestions.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
/*
remove timestamps older than window
if remaining requests < limit -> allow
else -> reject
 */
public class SlidingWindowStrategy implements RateLimitStrategy{
    private final int maxRequests;
    private final long windowSizeMillis;

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Long>> requests = new ConcurrentHashMap<>();

    public SlidingWindowStrategy(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        requests.putIfAbsent(clientId, new ConcurrentLinkedQueue<>());
        ConcurrentLinkedQueue<Long> queue = requests.get(clientId);
        while (!queue.isEmpty() && now - queue.peek() > windowSizeMillis) {
            queue.poll();
        }
        if (queue.size() < maxRequests) {
            queue.add(now);
            return true;
        }
        return false;
    }
}
