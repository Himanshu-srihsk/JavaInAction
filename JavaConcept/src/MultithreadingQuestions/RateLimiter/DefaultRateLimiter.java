package MultithreadingQuestions.RateLimiter;

public class DefaultRateLimiter implements RateLimiter{
    private final RateLimitStrategy strategy;

    public DefaultRateLimiter(RateLimitStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean allowRequest(String clientId) {
        return strategy.allowRequest(clientId);
    }
}
