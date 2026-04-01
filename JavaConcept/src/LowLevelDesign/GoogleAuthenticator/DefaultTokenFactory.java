package LowLevelDesign.GoogleAuthenticator;

public class DefaultTokenFactory implements TokenFactory {
    @Override
    public String generateToken(String secretKey, long currentTimeMillis) {
        return "generated_token";
    }
}
