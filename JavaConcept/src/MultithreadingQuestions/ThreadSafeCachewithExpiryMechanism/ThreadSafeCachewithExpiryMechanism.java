package MultithreadingQuestions.ThreadSafeCachewithExpiryMechanism;

/*
 Thread-safe Cache with Expiry Mechanism
	Design a thread-safe cache with the following properties:
		Each item in the cache has a time-to-live (TTL) value.
		Expired items should be removed automatically without blocking the main operation.
	    Multiple threads should be able to read and write to the cache simultaneously.
Implement this using ConcurrentHashMap and ScheduledExecutorService for expiration handling.
 */

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

class CacheItem<V>{
    private final V value;
    private final long expiryTime;

    public CacheItem(V value, long ttl) {
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttl;
    }
    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
class LRUCacheWithTTL<K,V>{
    private final int maxSize;
    private final ConcurrentHashMap<K,CacheItem<V>> cache;
    private final ConcurrentSkipListMap<K, Long> accessOrder;
    private final AtomicLong cacheHits;
    private final AtomicLong cacheMisses;
    private final ScheduledExecutorService scheduler;

    public LRUCacheWithTTL(int maxSize, long expiryInterval, TimeUnit unit) {
        this.maxSize = maxSize;
        this.cache = new ConcurrentHashMap<>();
        this.accessOrder = new ConcurrentSkipListMap<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.cacheHits = new AtomicLong(0);
        this.cacheMisses = new AtomicLong(0);

        scheduler.scheduleAtFixedRate(this::cleanUpExpiredItems, expiryInterval, expiryInterval, unit);
    }

    public void cleanUpExpiredItems(){
        Iterator<Map.Entry<K,CacheItem<V>>> iterator = cache.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<K, CacheItem<V>> entry = iterator.next();
            CacheItem<V> item = entry.getValue();
            if (item.isExpired()) {
                iterator.remove();
                accessOrder.remove(entry.getKey());
            }
        }
    }
    public void put(K key, V value, long ttl, TimeUnit unit) {
        if(cache.size()>maxSize){
            evictLRUItem();
        }
        CacheItem<V> item = new CacheItem<>(value, unit.toMillis(ttl));
        cache.put(key, item);
        accessOrder.put(key, System.nanoTime());
    }
    public V get(K key) {
        CacheItem<V> item = cache.get(key);
        if(item==null || item.isExpired()){
            cacheMisses.incrementAndGet();
            cache.remove(key);
            accessOrder.remove(key);
            return null;
        }
        cacheHits.incrementAndGet();
        accessOrder.put(key, System.nanoTime());
        return item.getValue();
    }

    public void remove(K key) {
        cache.remove(key);
        accessOrder.remove(key);
    }

    private synchronized void evictLRUItem() {
        long oldestAccessTime = Long.MAX_VALUE;
        K oldestKey = null;
        for (Map.Entry<K, Long> entry : accessOrder.entrySet()) {
            if (entry.getValue() < oldestAccessTime) {
                oldestAccessTime = entry.getValue();
                oldestKey = entry.getKey();
            }
        }

        if (oldestKey != null) {
            cache.remove(oldestKey);
            accessOrder.remove(oldestKey);
        }
    }

    public long getCacheHits() {
        return cacheHits.get();
    }

    public long getCacheMisses() {
        return cacheMisses.get();
    }
    public void shutdown() {
        scheduler.shutdown();
    }


}
public class ThreadSafeCachewithExpiryMechanism {
    public static void main(String[] args) throws InterruptedException {
        LRUCacheWithTTL<String, String> cache = new LRUCacheWithTTL<>(3, 1, TimeUnit.SECONDS);

        cache.put("ram", "sita", 2, TimeUnit.SECONDS);
        cache.put("shyam", "santosh", 3, TimeUnit.SECONDS);
        cache.put("Manju", "Choudhary", 2, TimeUnit.SECONDS);

        System.out.println("Cache Hit for ram: " + cache.get("ram"));
        System.out.println("Cache Hit for shyam: " + cache.get("shyam"));
        System.out.println("Cache Hit for Manju: " + cache.get("Manju"));

        Thread.sleep(2000);
        cache.put("key4", "value4", 2, TimeUnit.SECONDS);

        System.out.println("Cache Hit for key1 (after TTL expired): " + cache.get("ram"));
        System.out.println("Cache Hit for key2 (after TTL expired): " + cache.get("shyam"));
        System.out.println("Cache Hit for key3 (after TTL expired): " + cache.get("Manju"));
        System.out.println("Cache Hit for key4: " + cache.get("key4"));

        System.out.println("Cache Hits: " + cache.getCacheHits());
        System.out.println("Cache Misses: " + cache.getCacheMisses());

        cache.shutdown();
    }
}

/*
Cache Hit for ram: sita
Cache Hit for shyam: santosh
Cache Hit for Manju: Choudhary
Cache Hit for key1 (after TTL expired): null
Cache Hit for key2 (after TTL expired): santosh
Cache Hit for key3 (after TTL expired): null
Cache Hit for key4: value4
Cache Hits: 5
Cache Misses: 2

 */
