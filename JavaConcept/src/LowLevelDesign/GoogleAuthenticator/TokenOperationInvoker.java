package LowLevelDesign.GoogleAuthenticator;

public class TokenOperationInvoker {
    public void execute(TokenCommand tokenCommand) {
        tokenCommand.execute();
    }
}
