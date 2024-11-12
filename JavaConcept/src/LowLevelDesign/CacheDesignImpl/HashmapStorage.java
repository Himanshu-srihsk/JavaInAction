package LowLevelDesign.CacheDesignImpl;

import java.util.HashMap;
import java.util.Map;

public class HashmapStorage<K,V> implements Storage<K,V>{
//    private K key;
//    private V val;
    private Integer Capacity;
    private Map<K,V> store;
    HashmapStorage(Integer capacity){
        System.out.println("Capacity="+capacity);
        store = new HashMap<>(capacity);
        this.Capacity = capacity;
    }
    @Override
    public void put(K key, V value) {
        if(store.containsKey(key)){
            throw new keyAlreadyExistsException("key already exists");
        }
       if(isStorageFull()){
           throw new StorageFullException("Storage is full");
       }
       store.put(key, value);
       System.out.println("Stored: "+key+"="+value);
    }

    @Override
    public V get(K key) {
        if(store.containsKey(key)){
            System.out.println("Retrieved: "+key+"="+store.get(key));
            return store.get(key);
        }
        throw new KeyNotExistException("Key not found ");
    }

    @Override
    public void remove(K key) {
        if(store.containsKey(key)) {
            store.remove(key);
            return;
        }
        throw new KeyNotExistException("Key not found ");
    }

    public boolean isStorageFull(){
        return store.size() == this.Capacity;
    }
}
