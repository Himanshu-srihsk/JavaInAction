package LowLevelDesign.HashmapImpl;

public class Hashmap<K,V> {
    private Node<K,V>[] nodes;
    private int capacity;
    public Hashmap(int capacity) {
        this.capacity = capacity;
        this.nodes = new Node[capacity];
    }
    public void put(K key, V value) {
         int hasVal = Math.abs(key.hashCode())%capacity;
         Node<K,V> newNode = new Node<>();
         if(nodes[hasVal] == null){
             newNode.setKey(key);
             newNode.setValue(value);
             nodes[hasVal] = newNode;
         }else{
             Node<K,V> currentNode = nodes[hasVal];
             Node<K,V> previousNode = null;
             while(currentNode!=null){
                 if(currentNode.getKey().equals(key)){
                     currentNode.setValue(value);
                     return;
                 }else{
                     previousNode =currentNode;
                     currentNode = currentNode.getNext();
                 }
             }
             if(currentNode == null){
                previousNode.setNext(new Node<>(key, value));
             }
         }
    }
    public V get(K key) throws Exception {
        int hasVal = Math.abs(key.hashCode())%capacity;
        if(nodes[hasVal] == null){
            throw new Exception("Key not found: " + key);
        }else{
            Node<K,V> currentNode = nodes[hasVal];
            while(currentNode!=null){
                if(currentNode.getKey().equals(key)){
                    return currentNode.getValue();
                }else{
                    currentNode = currentNode.getNext();
                }
            }
        }
        throw new Exception("Key not found: " + key);
    }

    public void remove(K key) throws Exception {
        int hasVal = Math.abs(key.hashCode())%capacity;
        if(nodes[hasVal] == null){
            throw new Exception("Key not found: " + key);
        } else{
            Node<K,V> currentNode = nodes[hasVal];
            Node<K,V> previousNode = null;
            while(currentNode!=null){
                if(currentNode.getKey().equals(key)){
                    if(previousNode == null){
                        nodes[hasVal] = currentNode.getNext();
                    } else{
                        previousNode.setNext(currentNode.getNext());
                    }
                    return;
                } else{
                    previousNode = currentNode;
                    currentNode = currentNode.getNext();
                }
            }
        }
        throw new Exception("Key not found: " + key);
    }
}
