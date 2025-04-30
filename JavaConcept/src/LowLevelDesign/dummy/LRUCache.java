package LowLevelDesign.dummy;
import java.util.*;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        Node<K, V> node = cache.get(key);
        dll.moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.value = value;
            dll.moveToHead(node);
        } else {
            if (cache.size() == capacity) {
                Node<K, V> tail = dll.removeTail();
                cache.remove(tail.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            dll.addToHead(newNode);
            cache.put(key, newNode);
        }
    }


    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    static class DoublyLinkedList<K, V> {
        private final Node<K, V> head, tail;

        DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addToHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void moveToHead(Node<K, V> node) {
            remove(node);
            addToHead(node);
        }

        Node<K, V> removeTail() {
            Node<K, V> tailPrev = tail.prev;
            remove(tailPrev);
            return tailPrev;
        }

        void remove(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache.get(1)); // Output: A
        cache.put(4, "D"); // Evicts key 2
        System.out.println(cache.get(2)); // Output: null
        cache.put(5, "E"); // Evicts key 3
        System.out.println(cache.get(3)); // Output: null
        System.out.println(cache.get(4)); // Output: D
        System.out.println(cache.get(5)); // Output: E
    }
}

