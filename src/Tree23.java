package Tree23.src;

public class Tree23<T extends Comparable<T>> implements DTATree23<T> {

    private Node root;

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
        if (element.compareTo(currentNode.getLeftValue()) < 0) {
            currentNode.insert(insertRecursive(currentNode.getLeftChild(), element));
        } else if (currentNode.is2Node() || element.compareTo(currentNode.getRightValue()) < 0) {
            currentNode.insert(insertRecursive(currentNode.getMiddleChild(), element));
        } else {
            currentNode.insert(insertRecursive(currentNode.getRightChild(), element));
        }

        return null;
    }


    @Override
    public boolean search(T element) {
        // Implementación de la búsqueda en el árbol 2-3
        return false; // Ajustar según sea necesario
    }

    @Override
    public void delete(T element) {
        // Implementación de la eliminación en el árbol 2-3
    }

    @Override
    public String inOrder() {
        return inOrderTraversal(root).trim(); // Adjust as needed
    }

    // In-order traversal helper method
    private String inOrderTraversal(Node<T> currentNode) {
        StringBuilder result = new StringBuilder();

        if (currentNode != null) {
            result.append(inOrderTraversal(currentNode.getLeftChild()));
            result.append(currentNode.getLeftValue()).append(" ");
            result.append(inOrderTraversal(currentNode.getMiddleChild()));
            if (currentNode.is3Node()) {
                result.append(currentNode.getRightValue()).append(" ");
                result.append(inOrderTraversal(currentNode.getRightChild()));
            }
        }

        return result.toString();
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
