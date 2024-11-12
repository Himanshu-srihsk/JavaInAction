package LowLevelDesign.CacheDesignImpl;

public class DoublyLinkList<E> {
    private Node<E> head;
    private Node<E> tail;
    public DoublyLinkList() {
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        tail.setNext(null);
        head.setPrev(null);
    }
    public void removeFromHead(){
       if(isEmpty()){
           return;
       }
       head.setNext(head.getNext().getNext());
       head.getNext().setPrev(head);
    }
    public Node<E> getFirstNode(){
        return head.getNext();
    }

    public boolean isEmpty(){
        return head.getNext() == tail;
    }
    public void removeNode(Node<E> node){
        Node prev = node.getPrev();
        Node next = node.getNext();
        if(prev!=null){
            prev.setNext(next);
            next.setPrev(prev);
        }else{

            removeFromHead();
        }
    }
    public Node<E> addNodeWithKeyToTail(E key){
       Node newNode = new Node(key);
       Node tailPrev = tail.getPrev();
       tailPrev.setNext(newNode);
       newNode.setPrev(tailPrev);
       newNode.setNext(tail);
       tail.setPrev(newNode);
       return newNode;
    }

    public void ddlPrint(){
        System.out.print("Doubly Link List: ");
        Node current = head.getNext();
        while(current!=tail){
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println("--------------------");

    }


}
