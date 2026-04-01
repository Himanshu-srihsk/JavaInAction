package LowLevelDesign.GoogleAuthenticator;

public interface TokenVerifier {
    boolean verifyToken(String enteredCode, String generatedCode);
}
