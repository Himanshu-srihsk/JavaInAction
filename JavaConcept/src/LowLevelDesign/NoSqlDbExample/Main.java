package LowLevelDesign.NoSqlDbExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        NoSQLDatabase db = new InMemoryNoSQLDatabase();

        // Insert documents
        db.insert("users", new HashMap<>(Map.of("name", "Alice", "age", 25)));
        db.insert("users", new HashMap<>(Map.of("name", "Bob", "age", 30)));
        db.insert("users", new HashMap<>(Map.of("name", "Charlie", "age", 35)));

        // Find users older than 28
        System.out.println("Users older than 28:");

        List<Map<String, Object>> results = db.find("users", doc -> (int) doc.get("age") > 28);
        results.forEach(System.out::println);

        // Update Bob's age
        db.update("users", doc -> "Bob".equals(doc.get("name")), Map.of("age", 32));

        // Delete user Charlie
        db.delete("users", doc -> "Charlie".equals(doc.get("name")));

        // Print all users
        System.out.println("\nAll Users after update and delete:");
        db.printAll("users");
    }
}
/*
Users older than 28:
{name=Bob, age=30, _id=2}
{name=Charlie, age=35, _id=3}

All Users after update and delete:
{name=Alice, age=25, _id=1}
{name=Bob, age=32, _id=2}
 */