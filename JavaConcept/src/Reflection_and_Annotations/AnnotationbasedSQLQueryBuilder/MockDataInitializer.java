package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

import java.util.Arrays;
import java.util.List;

public class MockDataInitializer {
    static List<User> initializeMockData() {
        // Profiles
        Profile profile1 = new Profile("1", "Himanshu", "himanshu@gmail.com","1");
        Profile profile2 = new Profile("2", "Sristi", "srisi@gmail.com","2");

        // Posts
        List<Post> postsUser1 = List.of(new Post("101", "First post by Himanshu", "1"), new Post("102", "Second post by Himanshu", "1"));
        List<Post> postsUser2 = List.of(new Post("201", "Post by Sristi", "2"));

        // Roles
        Role adminRole = new Role(1, "ADMIN");
        Role userRole = new Role(2, "USER");

        // Users
        User user1 = new User("1", profile1,postsUser1, Arrays.asList(adminRole,userRole));
        User user2 = new User("2", profile2,postsUser2,Arrays.asList(userRole));

        return List.of(user1, user2);
    }
}
