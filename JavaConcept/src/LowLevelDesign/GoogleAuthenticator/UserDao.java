package LowLevelDesign.GoogleAuthenticator;

public interface UserDao {
    void addUser(User user);

    User getUser(String userId);

    void updateUser(User user);

    void removeUser(String userId);
}
