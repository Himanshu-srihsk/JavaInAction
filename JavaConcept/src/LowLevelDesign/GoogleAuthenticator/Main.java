package LowLevelDesign.GoogleAuthenticator;

public class Main {
    public static void main(String[] args) {
        GoogleAuthenticator authenticator = GoogleAuthenticator.getInstance();

        User user = new User("123", "John Doe", "secretKey123");
        authenticator.addUser(user);

        authenticator.executeTokenGeneration(user.getUserId(), System.currentTimeMillis());

        // Simulating user entering the code
        String enteredCode = "generated_token";
        authenticator.executeTokenVerification(user.getUserId(), enteredCode, "generated_token");
    }
}
/*
Generated Token: generated_token
Token Verification Result: true
 */