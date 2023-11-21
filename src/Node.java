package src;

import java.util.Arrays;

public class Node<T extends Comparable<T>> implements NodeTree23Interface<T> {

    private static final int MAX_CHILDREN = 3;
    private static final int MAX_VALUES = 2;
    private static final int ROOT_IS_BIGGER = 1;
    private static final int ROOT_IS_SMALLER = -1;

    private NodeTree23Interface<T>[] children;
    private T[] values;

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(children);
        result = prime * result + Arrays.hashCode(values);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (!Arrays.equals(children, other.children))
            return false;
        if (!Arrays.equals(values, other.values))
            return false;
        return true;
    }

    public Node() {
        this.children = new NodeTree23Interface[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
    }

    public Node(T value) {
        this.children = new NodeTree23Interface[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
        this.values[0] = value;
    }

    public Node(T value1, T value2) {
        this.children = new NodeTree23Interface[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
        this.values[0] = value1;
        this.values[1] = value2;

    }

    @Override
    public void clear() {
        this.children = new NodeTree23Interface[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
    }

    @Override
    public boolean isLeaf() {
        return this.getLeftChild() == null && this.getMiddleChild() == null && this.getRightChild() == null;
    }

    @Override
    public NodeTree23Interface<T> getLeftChild() {
        return children[0];
    }

    @Override
    public NodeTree23Interface<T> getMiddleChild() {
        return children[1];
    }

    @Override
    public NodeTree23Interface<T> getRightChild() {
        return children[2];
    }

    @Override
    public T getMinValue() {
        return getLeftValue();
    }

    @Override
    public T getMaxValue() {
        if (getRightValue() == null) {
            return getLeftValue();
        }
        return getRightValue();
    }

    @Override
    public T getLeftValue() {
        return values[0];
    }

    @Override
    public T getRightValue() {
        return values[1];
    }

    @Override
    public T[] getValues() {
        return values;
    }

    // public metos is2NOde, is3Node
    @Override
    public boolean is2Node() {
        return (this.getLeftValue() != null && this.getRightValue() == null);
    }

    @Override
    public boolean is3Node() {
        return (this.getLeftValue() != null && this.getRightValue() != null);
    }

    @Override
    public NodeTree23Interface<T> insert(T element) {
        NodeTree23Interface<T> newNode = this;

        // Check if the node is full
        if (this.getLeftValue() != null && this.getRightValue() != null) {
            // Node is full, split before insertion
            newNode = split(new Node<T>(element));
        }

        // Determine the correct position to insert the new element
        else if (element.compareTo(this.getLeftValue()) == ROOT_IS_SMALLER) {
            // Insert to the left
            this.setRightValue(this.getLeftValue());
            this.setLeftValue(element);
        } else {
            // Insert to the right
            this.setRightValue(element);
        }

        return newNode;
    }

    @Override
    public NodeTree23Interface<T> insert(Node<T> nodeToInsert) {
        NodeTree23Interface<T> newNode = this;

        // Check if the node is full
        if (this.getLeftValue() != null && this.getRightValue() != null) {
            // Node is full, split before insertion
            newNode = split(nodeToInsert);
        }

        // Determine the correct position to insert the new element
        else if (nodeToInsert.getLeftValue().compareTo(this.getLeftValue()) == ROOT_IS_SMALLER) {
            // Insert to the left
            this.setRightValue(this.getLeftValue());
            this.setLeftValue(nodeToInsert.getLeftValue());

            // acomodamos los hijos
            this.setLeftChild(nodeToInsert.getLeftChild());
            this.setRightChild(this.getMiddleChild());
            this.setCenterChild(nodeToInsert.getMiddleChild());

        } else {
            // Insert to the right
            this.setRightValue(nodeToInsert.getLeftValue());

            // acomodamos los hijos
            this.setRightChild(nodeToInsert.getRightChild());
            this.setLeftChild(this.getMiddleChild());
            this.setCenterChild(nodeToInsert.getMiddleChild());

        }

        return newNode;
    }

    private NodeTree23Interface<T> split(Node<T> newElement) {
        // Crear un nuevo nodo hoja izquierdo, derecho
        Node<T> newLeftNode, newRightNode, newNodeCenter = this;

        // obtenemos quien debe de ir izquieda, derecha, medio
        if (newElement.getLeftValue().compareTo(this.getMinValue()) == ROOT_IS_SMALLER) {
            // izquierda
            newLeftNode = newElement;

            // derecha
            newRightNode = new Node<T>(this.getMaxValue());
            newRightNode.setLeftChild(this.getMiddleChild());
            newRightNode.setCenterChild(this.getRightChild());

            // medio
            T midvalue = this.getMinValue();
            this.clear();

            newNodeCenter.setLeftValue(midvalue);
            newNodeCenter.setCenterChild(newRightNode);
            newNodeCenter.setLeftChild(newLeftNode);

        } else if (newElement.getLeftValue().compareTo(this.getMaxValue()) == ROOT_IS_BIGGER) {
            // izquierda
            newLeftNode = new Node<T>(this.getMinValue());
            newLeftNode.setLeftChild(this.getLeftChild());
            newLeftNode.setCenterChild(this.getMiddleChild());

            // derecha
            newRightNode = newElement;

            // medio
            T midvalue = this.getMaxValue();
            this.clear();

            newNodeCenter.setLeftValue(midvalue);
            newNodeCenter.setCenterChild(newRightNode);
            newNodeCenter.setLeftChild(newLeftNode);
        } else {
            // newLeftNode = new Node<T>(this.getMinValue());
            // newRightNode = new Node<T>(this.getMaxValue());
            // T midvalue = newElement;
            // this.clear();

            // izquierda
            newLeftNode = new Node<T>(this.getMinValue());
            newLeftNode.setLeftChild(this.getLeftChild());
            newLeftNode.setCenterChild(this.getMiddleChild());

            // derecha
            newRightNode = new Node<T>(this.getMaxValue());
            newRightNode.setLeftChild(this.getMiddleChild());
            newRightNode.setCenterChild(this.getRightChild());

            // medio
            T midvalue = newElement.getLeftValue();
            this.clear();

            newNodeCenter.setLeftChild(newLeftNode);
            newNodeCenter.setCenterChild(newRightNode);
            newNodeCenter.setLeftValue(midvalue);
        }

        return newNodeCenter;
    }
    // set left, right, center childen

    @Override
    public NodeTree23Interface<T> testSplitNode(Node<T> newElement) {
        return split(newElement);
    }

    private void setLeftChild(NodeTree23Interface<T> leftChild) {
        this.children[0] = leftChild;
    }

    private void setRightChild(NodeTree23Interface<T> rightChild) {
        this.children[2] = rightChild;
    }

    private void setCenterChild(NodeTree23Interface<T> centerChild) {
        this.children[1] = centerChild;
    }

    // set values left, right
    private void setLeftValue(T leftValue) {
        this.values[0] = leftValue;
    }

    private void setRightValue(T rightValue) {
        this.values[1] = rightValue;
    }

    @Override
    public boolean isSplitForInsert() {
        return this.getLeftValue() != null && this.getRightValue() != null;
    }

    @Override
    public boolean contains(T data){
        if(is2Node()){
            return this.getLeftValue().equals(data);
        }else{
            return this.getLeftValue().equals(data) || this.getRightValue().equals(data);
        }
    }

    @Override
    public int reBalance() {
        // TODO
        // devuelve 1 si necesita rebalancear este nodo
        return 0;
    }

    @Override
    public int removeValueInNode(T data) {
        // TODO
        // devuelve 1 si necesita rebalancear este nodo

        return 0;
    }

    @Override
    public NodeTree23Interface<T> getSuccesor(T valor){
        // TODO
        return null;

    }

    @Override
    public void swapValue(T value, NodeTree23Interface<T> nodeSuccesor) {
        // TODO
    }

    // para testear
    @Override
    public void testSetLeftChild(NodeTree23Interface<T> leftChild){
        this.children[0] = leftChild;
    }
    @Override
    public void testSetRightChild(NodeTree23Interface<T> rightChild){
        this.children[2] = rightChild;
    }
    @Override
    public void testSetCenterChild(NodeTree23Interface<T> centerChild){
        this.children[1] = centerChild;
    }

}