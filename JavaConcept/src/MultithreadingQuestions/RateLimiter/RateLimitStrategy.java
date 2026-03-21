package MultithreadingQuestions.RateLimiter;

public interface RateLimitStrategy {
    boolean allowRequest(String clientId);
}
