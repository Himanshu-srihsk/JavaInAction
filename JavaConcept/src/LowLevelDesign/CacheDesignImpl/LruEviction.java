package LowLevelDesign.CacheDesignImpl;

import java.util.HashMap;
import java.util.Map;

public class LruEviction<E> implements EvictionPolicy<E>{
    DoublyLinkList<E> dll;
    Map<E,Node<E>> keyToNodeMap;
    LruEviction(){
        dll = new DoublyLinkList<>();
        keyToNodeMap = new HashMap<>();
    }
    @Override
    public E evict() {
        dll.ddlPrint();
        Node<E> dllNode = dll.getFirstNode();
        System.out.println("Evicted " + dllNode);
        dll.removeNode(dllNode);
        keyToNodeMap.remove(dllNode.getData());
        dll.ddlPrint();
        return dllNode.getData();
    }

    @Override
    public void accessed(E key) {
        Node<E> node;
        // System.out.println("Accessed " + key);
        if(keyToNodeMap.containsKey(key)){
          //  System.out.println("remove operation as it is get " + key);
            dll.removeNode(keyToNodeMap.get(key));
            dll.addNodeWithKeyToTail(key);
        }else{

            node = dll.addNodeWithKeyToTail(key);
            keyToNodeMap.put(key, node);
        }
    }

    @Override
    public void remove(E key) {
        dll.removeNode(keyToNodeMap.get(key));
        keyToNodeMap.remove(key);
    }
    public void printCache(){
        dll.ddlPrint();
    }
}
