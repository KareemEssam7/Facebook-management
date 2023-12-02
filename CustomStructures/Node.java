package CustomStructures;

public class Node<T> {
    
    private T data;
    private Node<T> prev = null;
    private Node<T> next = null;
    
    public Node(T data, Node<T> prev, Node<T> next){
        this(data, prev);
        this.next = next;
    }
    public Node(T data, Node<T> prev){
        this(data);
        this.prev = prev;
    }
    public Node(T data){
        this.data = data;
    }
    public T value(){
        return data;
    }
    Node<T> back(){
        return next;
    }
    Node<T> front(){
        return prev;
    }
    public void setValue(T data){
        this.data = data;
    }
    void setBack(Node<T> next){
        this.next = next;
    }
    void setFront(Node<T> prev){
        this.prev = prev;
    }
    void deleteNode(){
        if(next != null)
            next.prev = prev;

        if(prev != null)
            prev.next = next;
    }
}
