package LowLevelDesign.ConsistentHashing;

public class Main {
    public static void main(String[] args) {
        // Initialize the hash function
        HashFunction hashFunction = new HashFunction();

        // Create a consistent hash ring with 3 replicas per server
        ConsistentHashRing consistentHashRing = new ConsistentHashRing(3, hashFunction);

        // Create servers with different IPs
        Server server1 = new Server("192.168.0.1");
        Server server2 = new Server("192.168.0.2");
        Server server3 = new Server("192.168.0.3");

        // Add servers to the hash ring
        consistentHashRing.addServerToHashRing(server1);
        consistentHashRing.addServerToHashRing(server2);
        consistentHashRing.addServerToHashRing(server3);

        // Put some key-value pairs into the consistent hash ring
        consistentHashRing.put("user1", "Ravi");
        consistentHashRing.put("user2", "Himanshu");
        consistentHashRing.put("user3", "Sristi");
        consistentHashRing.put("user4", "Amit");

        // Retrieve the entries from the consistent hash ring
        System.out.println("Getting data from hash ring:");
        System.out.println("Key: user1, Value: " + consistentHashRing.get("user1"));
        System.out.println("Key: user2, Value: " + consistentHashRing.get("user2"));
        System.out.println("Key: user3, Value: " + consistentHashRing.get("user3"));
        System.out.println("Key: user4, Value: " + consistentHashRing.get("user4"));

        // Remove a server and reindex
        System.out.println("\nRemoving server with IP: " + server2.getIpAddress());
        consistentHashRing.removeServerToHashRing(server2);

        // Retrieve the entries after removing the server
        System.out.println("Getting data after removing server 192.168.0.2:");
        System.out.println("Key: user1, Value: " + consistentHashRing.get("user1"));
        System.out.println("Key: user2, Value: " + consistentHashRing.get("user2"));
        System.out.println("Key: user3, Value: " + consistentHashRing.get("user3"));
        System.out.println("Key: user4, Value: " + consistentHashRing.get("user4"));

        //testing hascode

        // int x = hashFunction.createHash("192.168.0.1");
        // int y = hashFunction.createHash("192.168.0.1");
        // int a = hashFunction.createHash("192.168.0.1");
        // int b = hashFunction.createHash("192.168.0.1");
        // int c = hashFunction.createHash("192.168.0.1");

        // System.out.println("x="+x+ " y="+y+ " a="+a+" b="+b+" c="+c);
        //x=959544045 y=959544045 a=959544045 b=959544045 c=959544045
    }
}
/*
Getting data from hash ring:
Key: user1, Value: key=user1 , value=Ravi
Key: user2, Value: key=user2 , value=Himanshu
Key: user3, Value: key=user3 , value=Sristi
Key: user4, Value: key=user4 , value=Amit

Removing server with IP: 192.168.0.2
Getting data after removing server 192.168.0.2:
Key: user1, Value: key=user1 , value=Ravi
Key: user2, Value: key=user2 , value=Himanshu
Key: user3, Value: key=user3 , value=Sristi
Key: user4, Value: key=user4 , value=Amit
 */
