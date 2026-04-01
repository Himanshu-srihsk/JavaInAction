package LowLevelDesign.GoogleAuthenticator;

public class GoogleAuthenticator {
    private static volatile GoogleAuthenticator instance;
    private final TokenFactory tokenFactory;
    private final TokenVerifier tokenVerifier;
    private final UserDao userDao;
    private final TokenOperationInvoker invoker;

    private GoogleAuthenticator() {
        this.tokenFactory = new DefaultTokenFactory();
        this.tokenVerifier = new DefaultTokenVerifier();
        this.userDao = new InMemoryUserDao();
        this.invoker = new TokenOperationInvoker();
    }

    public static GoogleAuthenticator getInstance() {
        if (instance == null) {
            synchronized (GoogleAuthenticator.class) {
                if (instance == null) {
                    instance = new GoogleAuthenticator();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void executeTokenGeneration(String userId, long currentTimeMillis) {
        User user = userDao.getUser(userId);
        if (user != null) {
            TokenCommand generateTokenCommand = new GenerateTokenCommand(tokenFactory, user.getSecretKey(), currentTimeMillis);
            invoker.execute(generateTokenCommand);
        } else {
            System.out.println("User not found!");
        }
    }

    public void executeTokenVerification(String userId, String enteredCode, String generatedCode) {
        User user = userDao.getUser(userId);
        if (user != null) {
            TokenCommand verifyTokenCommand = new VerifyTokenCommand(tokenVerifier, enteredCode, generatedCode);
            invoker.execute(verifyTokenCommand);
        } else {
            System.out.println("User not found!");
        }
    }
}
