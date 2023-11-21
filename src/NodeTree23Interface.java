package src;

public interface NodeTree23Interface<T extends Comparable<T>> {

    int hashCode();

    boolean equals(Object obj);

    void clear();

    boolean isLeaf();

    NodeTree23Interface<T> getLeftChild();

    NodeTree23Interface<T> getMiddleChild();

    NodeTree23Interface<T> getRightChild();

    T getMinValue();

    T getMaxValue();

    T getLeftValue();

    T getRightValue();

    T[] getValues();

    boolean is2Node();

    boolean is3Node();

    NodeTree23Interface<T> insert(T element);

    NodeTree23Interface<T> insert(Node<T> nodeToInsert);

    boolean isSplitForInsert();
    
    boolean contains(T data);
    
    int reBalance();

    int removeValueInNode(T data);

    NodeTree23Interface<T> getSuccesor(T valor);
    
    void swapValue(T value, NodeTree23Interface<T> nodeSuccesor);
    
    // para testear
    NodeTree23Interface<T> testSplitNode(Node<T> newElement);
    
    void testSetLeftChild(NodeTree23Interface<T> leftChild);
    
    void testSetRightChild(NodeTree23Interface<T> rightChild);
    
    void testSetCenterChild(NodeTree23Interface<T> centerChild);

}