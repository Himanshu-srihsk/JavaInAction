package LowLevelDesign.BootstrapAggregationService;

public class UserProfile {
    private String consumerId;
    private String name;
    private String defaultPaymentMethod;
    private Double giftCardBalance;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getDefaultPaymentMethod() {
        return defaultPaymentMethod;
    }

    public void setDefaultPaymentMethod(String defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }

    public Double getGiftCardBalance() {
        return giftCardBalance;
    }

    public void setGiftCardBalance(Double giftCardBalance) {
        this.giftCardBalance = giftCardBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "consumerId='" + consumerId + '\'' +
                ", name='" + name + '\'' +
                ", defaultPaymentMethod='" + defaultPaymentMethod + '\'' +
                ", giftCardBalance=" + giftCardBalance +
                ", address=" + address +
                '}';
    }
}
