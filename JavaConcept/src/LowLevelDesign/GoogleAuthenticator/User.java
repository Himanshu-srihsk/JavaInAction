package LowLevelDesign.GoogleAuthenticator;

public class User {
    private final String userId;
    private final String name;
    private final String secretKey;

    public User(String userId, String name, String secretKey) {
        this.userId = userId;
        this.name = name;
        this.secretKey = secretKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public String toString() {
        return "User{id='" + userId + "', name='" + name + "', secretKey='" + secretKey + "'}";
    }
}
