package LowLevelDesign.GoogleAuthenticator;

public class DefaultTokenVerifier implements TokenVerifier {
    @Override
    public boolean verifyToken(String enteredCode, String generatedCode) {
        return enteredCode.equals(generatedCode);
    }
}
