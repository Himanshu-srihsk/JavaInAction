package LowLevelDesign.GoogleAuthenticator;

public class GenerateTokenCommand implements TokenCommand {
    private final TokenFactory tokenFactory;
    private final String secretKey;
    private final long currentTimeMillis;

    public GenerateTokenCommand(TokenFactory tokenFactory, String secretKey, long currentTimeMillis) {
        this.tokenFactory = tokenFactory;
        this.secretKey = secretKey;
        this.currentTimeMillis = currentTimeMillis;
    }

    @Override
    public void execute() {
        String generatedToken = tokenFactory.generateToken(secretKey, currentTimeMillis);
        System.out.println("Generated Token: " + generatedToken);
    }
}
