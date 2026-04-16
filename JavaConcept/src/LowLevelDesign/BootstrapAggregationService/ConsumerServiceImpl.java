package LowLevelDesign.BootstrapAggregationService;

public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public Consumer getConsumer(String userId) {
        if ("user123".equals(userId)) {
            return new Consumer("123", "Alice");
        }
        return null;
    }
}
