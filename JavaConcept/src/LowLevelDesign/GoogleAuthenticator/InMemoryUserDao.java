package LowLevelDesign.GoogleAuthenticator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDao implements UserDao {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public void removeUser(String userId) {
        users.remove(userId);
    }
}
