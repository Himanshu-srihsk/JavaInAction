package LowLevelDesign.BootstrapAggregationService;

public class AddressServiceImpl implements AddressService {
    @Override
    public Address getAddress(String consumerId) {
        return new Address("123 Main St", "Anytown", "12345");
    }
}