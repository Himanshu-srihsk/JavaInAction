package Reflection_and_Annotations.CustomSerializationUtility;

public class Address {
    @IgnoreField
    String state;
    @Serializable
    String city;
    @Serializable
    String pincode;

    public Address(String state, String city, String pincode) {
        this.state = state;
        this.city = city;
        this.pincode = pincode;
    }
}
