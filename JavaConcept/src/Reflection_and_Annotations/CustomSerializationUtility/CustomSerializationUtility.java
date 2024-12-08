package Reflection_and_Annotations.CustomSerializationUtility;

/*
Custom Serialization Utility with Annotations
Define custom annotations like @Serializable and @IgnoreField.
Write a serialization utility that:
Serializes an object to JSON format by including fields annotated with @Serializable.
Ignores fields annotated with @IgnoreField.
Test it with a sample class to serialize it into a JSON-like structure.
 */
public class CustomSerializationUtility {
    public static void main(String[] args) {
       Address address = new Address("Karnataka", "Bangalore", "560011");
       User user = new User("Himanshu", 26, "06-06-1998", address);
       String userJson = SerialzableUtil.serializeObject(user);
       System.out.println("Serialized JSON: " +userJson);
    }
}

/*
Serialized JSON: {"pincode": "560011", "city": "Bangalore", "name": "Himanshu", "age": "26"}
 */
