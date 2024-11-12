package LowLevelDesign.CacheDesignImpl;

public interface EvictionPolicy<K> {
    K evict();
    void accessed(K key);
    void remove(K key);
    void printCache();
}
