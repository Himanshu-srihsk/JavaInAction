package LowLevelDesign.CacheDesignImpl;

public class CacheService<K,V> {
    private Storage<K,V> storage;
    private EvictionPolicy<K> evictionPolicy;
    CacheService(){}
    public CacheService(Storage<K,V> storage, EvictionPolicy<K> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }
    public void put(K key, V value) {
        try {
            System.out.println("To Store Key: " + key + " Value: " + value);
            storage.put(key,value);
            evictionPolicy.accessed(key);
            System.out.println("printing cache after put operation");
            evictionPolicy.printCache();
            System.out.println("-------------------------------");
        }catch (keyAlreadyExistsException e) {
            System.out.println(e.getMessage());
            evictionPolicy.remove(key);
            storage.remove(key);
            put(key, value);
        }
        catch (StorageFullException e){
            System.out.println(e.getMessage());
            K keyToRemove = evictionPolicy.evict();
            System.out.println("Eviction policy saying to remove this key from storage ="+keyToRemove);
            storage.remove(keyToRemove);
            put(key, value);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public V get(K key) {
        V val = null;
        try{
            val = storage.get(key);
            evictionPolicy.accessed(key);
          //   System.out.println("printing cache after get operation");
            evictionPolicy.printCache();
            System.out.println("-------------------------------");

        }catch (KeyNotExistException e){
           System.out.println(e.getMessage());
        }
        return val;
    }
}
