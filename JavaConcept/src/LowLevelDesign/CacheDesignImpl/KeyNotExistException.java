package LowLevelDesign.CacheDesignImpl;

public class KeyNotExistException extends RuntimeException{
    public KeyNotExistException(String message) {
        super(message);
    }
}
