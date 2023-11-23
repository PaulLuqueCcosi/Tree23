package src;

public class NodeInsertReturnData<T extends Comparable<T>> {

    public static final int IS_SPLIT = 1;
    public static final int NOT_SPLIT = 0;

    private Node<T> node;
    private int result;

    public NodeInsertReturnData() {
        this.node = null;
        this.result = 0;
    }

    public NodeInsertReturnData(Node<T> node, int result) {
        this.node = node;
        this.result = result;
    }

    public Node<T> getNode() {
        return node;
    }

    public int getResult() {
        return result;
    }

    public void setNode(Node<T> node) {
        this.node = node;
    }
    public void setResult(int result) {
        this.result = result;
    }

}
