package LowLevelDesign.CacheDesignImpl;

public class keyAlreadyExistsException extends RuntimeException{
    public keyAlreadyExistsException(String message) {
        super(message);
    }
}
