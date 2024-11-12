package LowLevelDesign.CacheDesignImpl;

public class Node<E> {
    private E data;
    private Node next;
    private Node prev;
    Node(){}
    Node(E data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public E getData() {
        return data;
    }

    public Node(E data, Node next, Node prev) {
        this.next = next;
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +"}";

    }
}
