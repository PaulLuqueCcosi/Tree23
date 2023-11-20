package Tree23.src;

public class Tree23<T extends Comparable<T>> implements DTATree23<T> {

    private Node root;
    private static final int ROOT_IS_BIGGER = 1;
    private static final int ROOT_IS_SMALLER = -1;
    private static final int ROOT_IS_EQUALS = 0;

    @Override
    public boolean insert(T element) {
        boolean result = true;

        // Si la raíz es null, crea un nuevo nodo con el elemento como único valor
        if (root == null) {
            root = new Node<>(element);
        } else {
            // Llama al método auxiliar insertRecursive desde la raíz
            root = insertRecursive(root, element);
        }

        return result;
    }

    private Node<T> insertRecursive(Node<T> currentNode, T element) {
        // condicion de parada
        if (currentNode.isLeaf()) {
            return currentNode.insert(element);
        }

        // recursividad
        // Determine which child to traverse based on the element's value
        if (element.compareTo(currentNode.getLeftValue()) == ROOT_IS_SMALLER) {
            if (currentNode.getLeftChild().isSplitForInsert()) {
                currentNode.insert(insertRecursive(currentNode.getLeftChild(), element));
            } else {
                insertRecursive(currentNode.getLeftChild(), element);
            }
        } else if (element.compareTo(currentNode.getLeftValue()) == ROOT_IS_BIGGER) {
            if (currentNode.getRightChild().isSplitForInsert()) {
                currentNode.insert(insertRecursive(currentNode.getRightChild(), element));
            } else {
                insertRecursive(currentNode.getRightChild(), element);
            }
        } else {
            if (currentNode.getMiddleChild().isSplitForInsert()) {
                currentNode.insert(insertRecursive(currentNode.getMiddleChild(), element));
            } else {
                insertRecursive(currentNode.getMiddleChild(), element);
            }
        }

        return currentNode;
    }

    @Override
    public boolean search(T element) {
        return search(root, element);
    }

    private boolean search(Node<T> currentNode, T element) {
        if (currentNode == null) {
            return false;
        }

        // Compare with left value
        int compareLeft = element.compareTo(currentNode.getLeftValue());
        if (compareLeft == 0) {
            return true; // Element found
        } else if (compareLeft < 0 && currentNode.getLeftChild() != null) {
            return search(currentNode.getLeftChild(), element);
        }

        // Compare with right value
        int compareRight = element.compareTo(currentNode.getRightValue());
        if (compareRight == 0) {
            return true; // Element found
        } else if (compareRight > 0 && currentNode.getRightChild() != null) {
            return search(currentNode.getRightChild(), element);
        }

        // If it's a 3-node, compare with middle value
        if (currentNode.is3Node()) {
            return search(currentNode.getMiddleChild(), element);

        }

        // If none of the conditions match, the element is not in the current node
        return false;
    }

    @Override
    public void delete(T element) {
        // Implementación de la eliminación en el árbol 2-3
    }

    @Override
    public String inOrder() {
        StringBuilder str = new StringBuilder();

        if (!isEmpty()) {
            return inOrder(root, str).substring(0, str.length() - 1);
        } else {
            // System.out.print("The tree is empty...");
            return "The tree is empty...";
        }
    }

    /**
     * Method for displaying tree elements in the order of the method - "in-order"
     */
    private String inOrder(Node<T> current, StringBuilder str) {

        if (current != null) {

            if (current.isLeaf()) {
                if (current.getLeftValue() != null) {
                    str.append(current.getLeftValue().toString() + " ");
                }
                if (current.getRightValue() != null) {
                    str.append(current.getRightValue().toString() + " ");
                }
                return str.toString();
            } else {
                inOrder(current.getLeftChild(), str);
                if (current.getLeftValue() != null) {
                    str.append(current.getLeftValue().toString() + " ");
                }
                inOrder(current.getMiddleChild(), str);

                if (current.getRightValue() != null) {
                    str.append(current.getRightValue().toString() + " ");
                }

                inOrder(current.getRightChild(), str);

                return str.toString();
            }

        } else {
            // System.out.print("The tree is empty...");
            return "";
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    // Otros métodos y funciones auxiliares según sea necesario...
}
