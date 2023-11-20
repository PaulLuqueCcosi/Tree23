package Tree23.src;

import java.util.Arrays;

public class Node<T extends Comparable<T>> {

    private static final int MAX_CHILDREN = 3;
    private static final int MAX_VALUES = 2;
    private static final int ROOT_IS_BIGGER = 1;
    private static final int ROOT_IS_SMALLER = -1;

    private Node<T>[] children;
    private T[] values;
    private int size;

    public Node() {
        this.children = new Node[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
        this.size = 0;
    }

    public Node(T value) {
        this.children = new Node[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
        this.values[0] = value;
        this.size = 1;
    }

    public void clear() {
        this.children = new Node[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
        this.size = 0;
    }

    public boolean isLeaf() {
        return this.getLeftChild() == null && this.getMiddleChild() == null && this.getRightChild() == null;
    }

    public Node<T> getLeftChild() {
        return children[0];
    }

    public Node<T> getMiddleChild() {
        return children[1];
    }

    public Node<T> getRightChild() {
        return children[2];
    }

    public T getMinValue() {
        if (size > 0) {
            return values[0];
        }
        return null;
    }

    public T getMaxValue() {
        if(getRightValue() == null) {
            return getLeftValue();
        }
        return getRightValue();   
    }

    public T getLeftValue() {
        return values[0];
    }

    public T getRightValue() {
        return values[1];
    }

    public T[] getCopyValues() {
        return Arrays.copyOf(values, size);
    }

    public T[] getValues() {
        return values;
    }

    // public metos is2NOde, is3Node
    public boolean is2Node() {
        return getValues().length == 1;
    }

    public boolean is3Node() {
        return getValues().length == 2;
    }

    public Node<T> insert(T element) {
        Node<T> newNode = this;
    
        // Check if the node is full
        if (this.getLeftValue() != null && this.getRightValue() != null) {
            // Node is full, split before insertion
            newNode = split(element);
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

    public Node<T> insert(Node<T> nodeToInsert) {
        Node<T> newNode = this;

        // Check if the node is full
        if (this.getLeftValue() != null && this.getRightValue() != null) {
            // Node is full, split before insertion
            newNode = split(nodeToInsert.getLeftValue());
        }

        // Determine the correct position to insert the new element
        if (nodeToInsert.getLeftValue().compareTo(this.getLeftValue()) == ROOT_IS_SMALLER) {
            // Insert to the left
            this.setRightValue(this.getLeftValue());
            this.setLeftValue(nodeToInsert.getLeftValue());

            // acomodamos los hijos
            this.setLeftChild(nodeToInsert.getLeftChild());
            this.setCenterChild(nodeToInsert.getRightChild());

        } else {
            // Insert to the right
            this.setRightValue(nodeToInsert.getLeftValue());

            // acomodamos los hijos
            this.setCenterChild(nodeToInsert.getLeftChild());
            this.setRightChild(nodeToInsert.getRightChild());

        }

        return newNode;
    }

    private Node<T> split(T newElement) {
        // Crear un nuevo nodo hoja izquierdo, derecho
        Node<T> newLeftNode, newRightNode, newNodeCenter = this;

        // obtenemos quien debe de ir izquieda, derecha, medio
        if (newElement.compareTo(this.getMinValue()) == ROOT_IS_SMALLER) {
            newLeftNode = new Node<T>(newElement);
            newRightNode = new Node<T>(this.getMaxValue());
            T midvalue = this.getMinValue();
            this.clear();
            newNodeCenter.setLeftValue(midvalue);
            newNodeCenter.setRightChild(newRightNode);
            newNodeCenter.setLeftChild(newLeftNode);

        } else if (newElement.compareTo(this.getMaxValue()) == ROOT_IS_BIGGER) {
            newLeftNode = new Node<T>(this.getMinValue());
            newRightNode = new Node<T>(newElement);
            T midvalue = this.getMaxValue();
            this.clear();
            newNodeCenter.setLeftChild(newLeftNode);
            newNodeCenter.setRightChild(newRightNode);
            newNodeCenter.setLeftValue(midvalue);
        } else {
            newLeftNode = new Node<T>(this.getMinValue());
            newRightNode = new Node<T>(this.getMaxValue());
            T midvalue = newElement;
            this.clear();
            newNodeCenter.setLeftChild(newLeftNode);
            newNodeCenter.setRightChild(newRightNode);
            newNodeCenter.setLeftValue(midvalue);
        }

        return newNodeCenter;
    }
    // set left, right, center childen

    private void setLeftChild(Node<T> leftChild) {
        this.children[0] = leftChild;
    }

    private void setRightChild(Node<T> rightChild) {
        this.children[2] = rightChild;
    }

    private void setCenterChild(Node<T> centerChild) {
        this.children[1] = centerChild;
    }

    // set values left, right
    private void setLeftValue(T leftValue) {
        this.values[0] = leftValue;
    }

    private void setRightValue(T rightValue) {
        this.values[1] = rightValue;
    }

}