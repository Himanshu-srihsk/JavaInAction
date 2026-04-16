package LowLevelDesign.BootstrapAggregationService;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public PaymentInfo getPaymentInfo(String consumerId) {
        return new PaymentInfo("Credit Card", 50.0);
    }
}
