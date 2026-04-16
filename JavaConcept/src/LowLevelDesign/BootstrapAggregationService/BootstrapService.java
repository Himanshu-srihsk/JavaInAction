package LowLevelDesign.BootstrapAggregationService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BootstrapService {

    private final ConsumerService consumerService;
    private final PaymentService paymentService;
    private final AddressService addressService;
    private final Executor executor;

    public BootstrapService(
            ConsumerService consumerService,
            PaymentService paymentService,
            AddressService addressService,
            Executor executor
    ) {
        this.consumerService = consumerService;
        this.paymentService = paymentService;
        this.addressService = addressService;
        this.executor = executor;
    }

    public UserProfile getUserProfile(String userId) {


        Consumer consumer = consumerService.getConsumer(userId);

        if (consumer == null) {
            throw new UserNotFoundException("User not found: " + userId);
        }

        String consumerId = consumer.getId();

        CompletableFuture<PaymentInfo> paymentFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return paymentService.getPaymentInfo(consumerId);
                    } catch (Exception e) {
                        return null;
                    }
                }, executor);

        CompletableFuture<Address> addressFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return addressService.getAddress(consumerId);
                    } catch (Exception e) {
                        return null;
                    }
                }, executor);

        // Wait for both
        CompletableFuture.allOf(paymentFuture, addressFuture).join();


        PaymentInfo payment = paymentFuture.join();
        Address address = addressFuture.join();

        UserProfile profile = new UserProfile();
        profile.setConsumerId(consumerId);
        profile.setName(consumer.getName());

        if (payment != null) {
            profile.setDefaultPaymentMethod(payment.getDefaultMethod());
            profile.setGiftCardBalance(payment.getGiftCardBalance());
        }

        profile.setAddress(address);

        return profile;
    }
}
