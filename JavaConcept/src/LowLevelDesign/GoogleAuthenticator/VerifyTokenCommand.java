package LowLevelDesign.GoogleAuthenticator;

public class VerifyTokenCommand implements TokenCommand {
    private final TokenVerifier tokenVerifier;
    private final String enteredCode;
    private final String generatedCode;

    public VerifyTokenCommand(TokenVerifier tokenVerifier, String enteredCode, String generatedCode) {
        this.tokenVerifier = tokenVerifier;
        this.enteredCode = enteredCode;
        this.generatedCode = generatedCode;
    }

    @Override
    public void execute() {
        boolean isVerified = tokenVerifier.verifyToken(enteredCode, generatedCode);
        System.out.println("Token Verification Result: " + isVerified);
    }
}
