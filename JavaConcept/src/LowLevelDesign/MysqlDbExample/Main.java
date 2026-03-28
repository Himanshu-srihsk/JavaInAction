package LowLevelDesign.MysqlDbExample;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Database db = new InMemoryMySQL();

        db.createTable("users", List.of(
                new Column("id", DataType.INT, true, true),
                new Column("name", DataType.STRING, false, false),
                new Column("email", DataType.STRING, false, true)
        ));

        db.insert("users", Map.of("id", 1, "name", "Alice", "email", "alice@example.com"));
        db.insert("users", Map.of("id", 2, "name", "Bob", "email", "bob@example.com"));
        db.insert("users", Map.of("id", 3, "name", "Charlie", "email", "charlie@example.com"));

        System.out.println("\nAll users:");
        db.printAll("users");

        System.out.println("\nSelect where name is 'Bob':");
        //r -> "Bob".equals(r.get("name"))
        Predicate<Row> predicate = (Row r) -> "Bob".equals(r.get("name"));
        List<Row> result = db.select("users", predicate);
        result.forEach(System.out::println);

        System.out.println("\nUpdate Bob's name to Bobby:");
        db.update("users", r -> "Bob".equals(r.get("name")), Map.of("name", "Bobby"));
        db.printAll("users");


        System.out.println("\nUpdate users with id > 1 and email ending with '@example.com' and name not 'Charlie':");
        Predicate<Row> condition =  (Row r) -> (int) r.get("id") > 1
                && ((String) r.get("email")).endsWith("@example.com")
                && !"Charlie".equals(r.get("name"));

        db.update("users", condition, Map.of("email", "Bobbyupdated@example.com"));
        db.printAll("users");



        System.out.println("\nDelete user with id 1:");
        db.delete("users", r -> (int) r.get("id") == 1);
        db.printAll("users");
    }
}
/*

All users:
Table: users
{name=Alice, id=1, email=alice@example.com}
{name=Bob, id=2, email=bob@example.com}
{name=Charlie, id=3, email=charlie@example.com}

Select where name is 'Bob':
{name=Bob, id=2, email=bob@example.com}

Update Bob's name to Bobby:
Table: users
{name=Alice, id=1, email=alice@example.com}
{name=Bobby, id=2, email=bob@example.com}
{name=Charlie, id=3, email=charlie@example.com}

Update users with id > 1 and email ending with '@example.com' and name not 'Charlie':
Table: users
{name=Alice, id=1, email=alice@example.com}
{name=Bobby, id=2, email=Bobbyupdated@example.com}
{name=Charlie, id=3, email=charlie@example.com}

Delete user with id 1:
Table: users
{name=Bobby, id=2, email=Bobbyupdated@example.com}
{name=Charlie, id=3, email=charlie@example.com}
 */