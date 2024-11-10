package LowLevelDesign.HashmapImpl;

public class HashmapImpl {
    public static void main(String[] args) {
        Hashmap<String,String> hashmap = new Hashmap<>(2);

        hashmap.put("key1", "value1");
        hashmap.put("key2", "value2");
        hashmap.put("key3", "value3");
        hashmap.put("key4", "value1");
        hashmap.put("key5", "value2");
        hashmap.put("key1", "value9");

        try {
            System.out.println(hashmap.get("key1")); // Output: value9
            System.out.println(hashmap.get("key2")); // Output: value2
            System.out.println(hashmap.get("key3")); // Output: value3
            System.out.println(hashmap.get("key4")); // Output: value1
            System.out.println(hashmap.get("key5")); // Output: value2
            try {
                System.out.println(hashmap.get("key17")); // Output: Key not exist
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            hashmap.remove("key1");
            try {
                System.out.println(hashmap.get("key1")); // Output: Key not exist
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
