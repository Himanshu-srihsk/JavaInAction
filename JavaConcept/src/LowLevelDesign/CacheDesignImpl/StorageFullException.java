package LowLevelDesign.CacheDesignImpl;

public class StorageFullException extends RuntimeException{
    public StorageFullException(String message) {
        super(message);
    }
}
