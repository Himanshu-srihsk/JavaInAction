package LowLevelDesign.CacheDesignImpl;



public class CacheFactory<K, V> {
    public static <K, V> CacheService<K, V> getCache(Integer capacity) {
        return new CacheService<>(new HashmapStorage<>(capacity), new LruEviction<>());
    }
}
