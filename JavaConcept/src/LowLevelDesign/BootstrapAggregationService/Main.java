package LowLevelDesign.BootstrapAggregationService;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Implement a BootstrapService to Aggregate Data from Multiple Services
Design and implement a BootstrapService that aggregates data from three distinct services ConsumerService,
 PaymentService, and AddressService to construct a comprehensive response for a given userId. The BootstrapService should:

getUserProfile(userId: String) -> UserProfile: For a valid userId, this method should:

Retrieve the consumer's ID and name from the ConsumerService.

Obtain the default payment method and gift card balance from the PaymentService.

Fetch the user's address from the AddressService.

Assemble and return a UserProfile object containing the following fields:

consumerId: The unique identifier of the consumer.

name: The consumer's name.

defaultPaymentMethod: The default payment method.

giftCardBalance: Remaining gift card credits.

address: The user's address.

Error Handling:

If the provided userId is invalid or the ConsumerService cannot find the user, the method should raise an appropriate error or return a null UserProfile.

If the PaymentService or AddressService encounters an error, the corresponding fields (defaultPaymentMethod, giftCardBalance, address) in the UserProfile should be set to null or an empty value, while still returning the available information.

Example:

Input:
  userId = "user123"

Assumptions:
  - ConsumerService.getConsumer("123") returns Consumer(id="123", name="Alice")
  - PaymentService.getPaymentInfo("123") returns PaymentInfo(defaultMethod="Credit Card", giftCardBalance=50.0)
  - AddressService.getAddress("123") returns Address(line1="123 Main St", city="Anytown", zip="12345")

Output:
  UserProfile(
    consumerId="123",
    name="Alice",
    defaultPaymentMethod="Credit Card",
    giftCardBalance=50.0,
    address=Address(line1="123 Main St", city="Anytown", zip="12345")
  )
 */
public class Main {
    public static void main(String[] args) {
        ConsumerService consumerService = new ConsumerServiceImpl();
        PaymentService paymentService = new PaymentServiceImpl();
        AddressService addressService = new AddressServiceImpl();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        BootstrapService bootstrapService =
                new BootstrapService(
                        consumerService,
                        paymentService,
                        addressService,
                        executor
                );

        try {
            UserProfile profile = bootstrapService.getUserProfile("user123");
            System.out.println(profile);
        } catch (UserNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            executor.shutdown();
        }
    }
}
/*
UserProfile{consumerId='123', name='Alice', defaultPaymentMethod='Credit Card', giftCardBalance=50.0, address=123 Main St, Anytown - 12345}

 */