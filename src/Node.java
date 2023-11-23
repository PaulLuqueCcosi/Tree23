package src;

import java.util.Arrays;

public class Node<T extends Comparable<T>> {

    private static final int MAX_CHILDREN = 3;
    private static final int MAX_VALUES = 2;
    private static final int ROOT_IS_BIGGER = 1;
    private static final int ROOT_IS_SMALLER = -1;

    private Node<T>[] children;
    private T[] values;

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(children);
        result = prime * result + Arrays.hashCode(values);
        return result;
    }

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
        this.children = new Node[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
    }

    public Node(T value) {
        this.children = new Node[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
        this.values[0] = value;
    }

    public Node(T value1, T value2) {
        this.children = new Node[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
        this.values[0] = value1;
        this.values[1] = value2;

    }

    public void clear() {
        this.children = new Node[MAX_CHILDREN];
        this.values = (T[]) new Comparable[MAX_VALUES];
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
        return getLeftValue();
    }

    public T getMaxValue() {
        if (getRightValue() == null) {
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

    private boolean isEmptyValues() {
        return this.values[0] == null && this.values[1] == null;
    }

    public T[] getValues() {
        return values;
    }

    private T getAndDeleteLeftValue() {

        T leftValue = getLeftValue();

        // fix childs
        Node<T>[] newChildren = new Node[MAX_CHILDREN];
        newChildren[0] = this.getMiddleChild();
        newChildren[1] = this.getRightChild();
        newChildren[2] = null;

        this.setChilds(newChildren);

        // fix values
        this.setLeftValue(this.getRightValue());
        this.setRightValue(null);

        return leftValue;
    }

    private T getAndDeleteRightValue() {
        T rightValue = getRightValue();

        // fix childs
        // genere new Childs
        Node<T>[] newChildren = new Node[MAX_CHILDREN];
        newChildren[0] = this.getLeftChild();
        newChildren[1] = this.getMiddleChild();
        newChildren[2] = null;

        this.setChilds(newChildren);

        // fix values
        this.setRightValue(null);

        return rightValue;
    }

    // public metos is2NOde, is3Node

    private void setChilds(Node<T>[] newChildren) {
        this.children = newChildren;
    }

    public boolean is2Node() {
        return (this.getLeftValue() != null && this.getRightValue() == null);
    }

    public boolean is3Node() {
        return (this.getLeftValue() != null && this.getRightValue() != null);
    }

    public Node<T> insert(T element) {
        Node<T> newNode = this;

        // check if empty node
        if (this.isEmptyValues()) {
            this.setLeftValue(element);
        }

        // Check if the node is full
        else if (this.getLeftValue() != null && this.getRightValue() != null) {
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

    public Node<T> insertNode(Node<T> nodeToInsert) {
        Node<T> newNode = this;

        // Check if the node is full
        if (this.getLeftValue() != null && this.getRightValue() != null) {
            // Node is full, split before insertion
            newNode = split(nodeToInsert);
        }

        // Determine the correct position to insert the new element
        else if (this.getLeftValue() == null
                || nodeToInsert.getLeftValue().compareTo(this.getLeftValue()) == ROOT_IS_SMALLER) {
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

    private Node<T> split(Node<T> newElement) {
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

    public Node<T> testSplitNode(Node<T> newElement) {
        return split(newElement);
    }

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

    public boolean isSplitForInsert() {
        return this.getLeftValue() != null && this.getRightValue() != null;
    }

    public boolean contains(T data) {
        if (is2Node()) {
            return this.getLeftValue().equals(data);
        } else {
            return this.getLeftValue().equals(data) || this.getRightValue().equals(data);
        }
    }

    public int reBalance() {
        // TODO
        // devuelve 1 si necesita rebalancear este nodo
        // return 0 si no necesita rebalancear este nod
        // return -1 si no se encuentra el valor

        // left child is the problem
        if (this.getLeftChild().isEmptyValues()) {
            return reBalanceLeft(this);

        }
        // middle child is the problem
        else if (this.getMiddleChild().isEmptyValues()) {
            return reBalanceMid(this);

        }
        // right child is the problem / if exist
        else if (this.getRightChild() != null && this.getRightChild().isEmptyValues()) {
            return reBalanceRight(this);
        }

        return -1;
    }

    private int reBalanceMid(Node<T> node) {
        // si el hermano del izquierdo tiene 1 valor
        if (node.getLeftChild().is2Node()) {
            Node<T> childFault = node.getMiddleChild();

            T leftData = node.getLeftValue();
            childFault.insert(leftData);

            node.insertFoultNodeMid(childFault);
            return (node.isEmptyValues() ? 1 : 0);
        }
        // tiene 2 valores el hermano
        else {
            Node<T> childFault = node.getMiddleChild();

            T leftData = node.getLeftValue();

            childFault.insert(leftData);

            node.insertFoultNodeMid(childFault);
            return (node.isEmptyValues() ? 1 : 0);
        }
    }

    private int reBalanceLeft(Node<T> node) {
        // si el hermano del medio tiene 1 valor
        if (node.getMiddleChild().is2Node()) {
            Node<T> childFault = node.getLeftChild();
            T leftdata = node.getLeftValue();
            // solucioando
            childFault.insert(leftdata);
            // fin solucionando
            node.insertFaultNodeLeft(childFault);
            return (node.isEmptyValues() ? 1 : 0);
        }
        // tiene 2 valores el hermano
        else {

            Node<T> childFault = node.getLeftChild();
            T leftdata = node.getLeftValue();
            // solucioando
            childFault.insert(leftdata);
            // fin solucionando
            node.insertFaultNodeLeft(childFault);
            return (node.isEmptyValues() ? 1 : 0);

        }
    }

    private void insertFaultNodeLeft(Node<T> childFault) {

        // si no tieme espacop
        if (this.getMiddleChild().isSplitForInsert()) {
            // Node<T> fatherSplit = new Node<>();

            childFault.setCenterChild(this.getMiddleChild().getLeftChild());
            T newLefData = this.getMiddleChild().getAndDeleteLeftValue();
            this.setLeftValue(newLefData);

            // this.setRightChild(this.getMiddleChild());
            // this.setCenterChild(this.getLeftChild());
            // this.setLeftChild(childFault.getLeftChild());
        }
        // insert in left
        else {

            this.getMiddleChild().setRightValue(this.getMiddleChild().getLeftValue());
            this.getMiddleChild().setLeftValue(childFault.getLeftValue());

            this.getMiddleChild().setRightChild(this.getMiddleChild().getMiddleChild());
            this.getMiddleChild().setCenterChild(this.getMiddleChild().getLeftChild());
            this.getMiddleChild().setLeftChild(childFault.getLeftChild());

            this.getAndDeleteLeftValue();
        }
    }

    private void insertFaultNodeRight(Node<T> childFault) {

        // si no tieme espacop
        if (this.getMiddleChild().isSplitForInsert()) {
            // Node<T> fatherSplit = new Node<>();

            childFault.setCenterChild(childFault.getLeftChild());

            childFault.setLeftChild(this.getMiddleChild().getRightChild());

            T newRightData = this.getMiddleChild().getAndDeleteRightValue();
            this.setRightValue(newRightData);

            // this.setRightChild(this.getMiddleChild());
            // this.setCenterChild(this.getLeftChild());
            // this.setLeftChild(childFault.getLeftChild());
        }
        // insert in right
        else {

            // this.getMiddleChild().setRightValue(this.getMiddleChild().getLeftValue());
            this.getMiddleChild().setRightValue(childFault.getLeftValue());

            this.getMiddleChild().setRightChild(childFault.getLeftChild());

            this.getAndDeleteRightValue();
        }
    }

    private void insertFoultNodeMid(Node<T> childFault) {

        // si no tieme espacop
        if (this.getLeftChild().isSplitForInsert()) {
            // Node<T> fatherSplit = new Node<>();

            childFault.setCenterChild(childFault.getLeftChild());

            childFault.setLeftChild(this.getLeftChild().getRightChild());

            T newLeftData = this.getLeftChild().getAndDeleteRightValue();
            this.setLeftValue(newLeftData);

            // this.setRightChild(this.getMiddleChild());
            // this.setCenterChild(this.getLeftChild());
            // this.setLeftChild(childFault.getLeftChild());
        }
        // insert in left
        else {

            // this.getMiddleChild().setRightValue(this.getMiddleChild().getLeftValue());
            this.getLeftChild().setRightValue(childFault.getLeftValue());

            this.getLeftChild().setRightChild(childFault.getLeftChild());

            this.setCenterChild(this.getLeftChild());

            this.getAndDeleteLeftValue();
        }
    }

    private int reBalanceRight(Node<T> node) {
        // si el hermano del medio tiene 1 valor
        if (node.getMiddleChild().is2Node()) {
            Node<T> childFault = node.getRightChild();
            T rightData = node.getRightValue();
            childFault.insert(rightData);
            node.insertFaultNodeRight(childFault);
            return (node.isEmptyValues() ? 1 : 0);
        }
        // tiene 2 valores el hermano
        else {
            Node<T> childFault = node.getRightChild();
            T rightData = node.getRightValue();
            childFault.insert(rightData);
            node.insertFaultNodeRight(childFault);
            return (node.isEmptyValues() ? 1 : 0);
        }
    }

    public int removeValueInNode(T data) {
        // TODO
        // devuelve 1 si necesita rebalancear este nodo
        // return 0 si no necesita rebalancear este nod
        // return -1 si no se encuentra el valor

        if (this.getLeftValue() == data) {
            if (this.getRightValue() == null) {
                this.setLeftValue(null);
                return 1;
            } else {
                this.setLeftValue(this.getRightValue());
                this.setRightValue(null);
                return 0;
            }

        } else if (this.getRightValue() == data) {
            this.setRightValue(null);
            return 0;
        } else {
            return -1;
        }

    }

    public Node<T> getSuccesor(T valor) {
        if (valor == this.getLeftValue()) {
            return getSuccesorRecursive(this.getMiddleChild());
        } else if (valor == this.getRightValue()) {
            return getSuccesorRecursive(this.getRightChild());
        }

        return null;

    }

    private Node<T> getSuccesorRecursive(Node<T> node) {
        // condicones de parada
        if (node == null) {
            return null;
        }
        if (node.isLeaf()) {
            return node;
        }
        // parte recursiva
        else {
            return getSuccesorRecursive(node.getLeftChild());

        }
    }

    public void swapValue(T value, Node<T> nodeSuccesor) {
        if (value == this.getLeftValue()) {
            this.setLeftValue(nodeSuccesor.getLeftValue());
        } else if (value == this.getRightValue()) {
            this.setRightValue(nodeSuccesor.getLeftValue());
        }

        nodeSuccesor.setLeftValue(value);
    }

    // para testear

    public void testSetLeftChild(Node<T> leftChild) {
        this.children[0] = leftChild;
    }

    public void testSetRightChild(Node<T> rightChild) {
        this.children[2] = rightChild;
    }

    public void testSetCenterChild(Node<T> centerChild) {
        this.children[1] = centerChild;
    }

}