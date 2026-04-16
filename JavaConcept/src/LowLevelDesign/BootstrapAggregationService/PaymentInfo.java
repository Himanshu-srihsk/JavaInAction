package LowLevelDesign.BootstrapAggregationService;

public class PaymentInfo {
    private String defaultMethod;
    private Double giftCardBalance;

    public PaymentInfo(String defaultMethod, Double giftCardBalance) {
        this.defaultMethod = defaultMethod;
        this.giftCardBalance = giftCardBalance;
    }

    public String getDefaultMethod() { return defaultMethod; }
    public Double getGiftCardBalance() { return giftCardBalance; }
}
