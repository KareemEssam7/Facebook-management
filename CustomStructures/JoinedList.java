package CustomStructures;
public class JoinedList<T> {
    private Node<T> startNode = null;
    private Node<T> endNode = null;
    private Node<T> iteratorNode = null;
    boolean reversedIteration = false;

    public void pushBack(T data) {
        if(startNode == null) {
            startNode = new Node<T>(data);
            endNode = startNode;
            return;
        }

        Node<T> temp = new Node<T>(data, endNode);
        endNode.setBack(temp);
        endNode = temp;
    }
    public void pushFront(T data) {
        if(startNode == null) {
            startNode = new Node<T>(data);
            endNode = startNode;
            return;
        }

        Node<T> temp = new Node<T>(data, null, startNode);
        startNode.setFront(temp);
        startNode = temp;
    }

    public Node<T> deleteNode(Node<T> node) {
        if(startNode == node)
            startNode = startNode.back();
        if(endNode == node)
            endNode = endNode.front();
        if(node == iteratorNode) {
            if(reversedIteration)
                iteratorNode = iteratorNode.front();
            else
                iteratorNode = iteratorNode.back();
        }
        node.deleteNode();
        return iteratorNode;
    }

    public Node<T> getEndNode() {
        return endNode;
    }

    public Node<T> getStartNode() {
        return startNode;
    }

    public Node<T> iteratorToStart() {
        return iteratorNode = (reversedIteration) ? endNode : startNode;
    }

    public void setIterator(Node<T> node) {
        iteratorNode = node;
    }

    public Node<T> nextValue() {
        if(reversedIteration) {
            if(iteratorNode == null)
                return iteratorNode = endNode;
        
            return iteratorNode = iteratorNode.front();
        }
        else {
            if(iteratorNode == null)
                return iteratorNode = startNode;
            
            return iteratorNode = iteratorNode.back();
        }
    }

    public void setReversedIteration(boolean reversedIteration) {
        this.reversedIteration = reversedIteration;
    }

}