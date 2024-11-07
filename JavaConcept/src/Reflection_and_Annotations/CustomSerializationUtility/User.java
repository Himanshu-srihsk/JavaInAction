package Reflection_and_Annotations.CustomSerializationUtility;

import java.util.Date;

public class User {
    @Serializable
    String name;
    @Serializable
    int age;
    @IgnoreField
    String dob;
    @Serializable
    Address address;

    public User(String name, int age, String dob, Address address) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.address = address;
    }
}
