package src;

public class Tree23<T extends Comparable<T>> implements DTATree23<T> {

    private Node<T> root;
    private static final int ROOT_IS_BIGGER = 1;
    private static final int ROOT_IS_SMALLER = -1;
    protected static final int ROOT_IS_EQUALS = 0;
    protected static final int LEFT = 0;
    protected static final int RIGHT = 1;

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
        // izquierda
        if (element.compareTo(currentNode.getLeftValue()) == ROOT_IS_SMALLER) {
            if (currentNode.getLeftChild().isSplitForInsert()) {
                currentNode.insertNode(insertRecursive(currentNode.getLeftChild(), element));
            } else {
                insertRecursive(currentNode.getLeftChild(), element);
            }
        } else if (element.compareTo(currentNode.getRightValue()) == ROOT_IS_BIGGER) {
            if (currentNode.getRightChild().isSplitForInsert()) {
                currentNode.insertNode(insertRecursive(currentNode.getRightChild(), element));
            } else {
                insertRecursive(currentNode.getRightChild(), element);
            }
        } else {
            if (currentNode.getMiddleChild().isSplitForInsert()) {
                currentNode.insertNode(insertRecursive(currentNode.getMiddleChild(), element));
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
        // condicion de parada
        if (currentNode == null) {
            return false;
        }

        // Compare with left value
        int compareLeft = element.compareTo(currentNode.getLeftValue());
        if (compareLeft == ROOT_IS_EQUALS) {
            return true; // Element found
        } else if (compareLeft < ROOT_IS_SMALLER && currentNode.getLeftChild() != null) {
            return search(currentNode.getLeftChild(), element);
        }

        // Compare with right value
        int compareRight = element.compareTo(currentNode.getRightValue());
        if (compareRight == ROOT_IS_EQUALS) {
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
        int resultRebalance = 0;
        // The element is in the current root
        if (root.contains(element) && root.isLeaf()) {
            if (root.removeValueInNode(element) == 1) {
                // rebalance root node
                this.root = null;
            }

        }
        // The element is not in the current root
        else {
            resultRebalance = deleteRecursive(root, element);
            if (resultRebalance == -1) {
                System.out.println("NO SE ENCONTRO EL ELEMNTO PARA ELIMINAR");
            }
            // rebalance root node
            if (resultRebalance == 1) {
                root = root.getLeftChild();
            }
        }

    }

    private int deleteRecursive(Node<T> node, T element) {
        // condicionde parada
        if (node == null) {
            return -1;
        } else if (node.contains(element)) { // Contiene al elemento
            if (node.isLeaf()) { // es una hoja
                int result = node.removeValueInNode(element);
                return result;
            } else { // no es una hoja

                // get sucesor
                Node<T> nodeSuccesor = node.getSuccesor(element);
                // recordar en que lugar esta
                // izquieda 0, derecha 1
                int whereIsValue;
                if (node.getLeftValue() == element) {
                    whereIsValue = LEFT;
                } else {
                    whereIsValue = RIGHT;
                }

                // intercambiar valores
                node.swapValue(element, nodeSuccesor);

                // eliminar
                // si el el elemnto izquierdo
                int result;
                if (whereIsValue == LEFT) {
                    result = deleteRecursive(node.getMiddleChild(), element);
                } else {
                    result = deleteRecursive(node.getRightChild(), element);
                }

                // rebalanceo

                if (result == 1) {
                    return node.reBalance();
                } else {
                    return result;
                }

            }
        }
        
        // no contienen al elemento, recursividad
        else {
            // izquierda
            if (element.compareTo(node.getLeftValue()) == -1) {
                int result = deleteRecursive(node.getLeftChild(), element);
                if (result == 1) {
                    return node.reBalance();
                } else {
                    return result;
                }
            }
            // medio
            else if (element.compareTo(node.getLeftValue()) == 1 && node.is2Node()) {
                int result = deleteRecursive(node.getMiddleChild(), element);
                if (result == 1) {
                    return node.reBalance();
                } else {
                    return result;
                }
            }
            // derecha
            else if (node.is3Node() && element.compareTo(node.getRightValue()) == 1) {
                int result = deleteRecursive(node.getRightChild(), element);
                if (result == 1) {
                    return node.reBalance();
                } else {
                    return result;
                }
            } else {
                return -1;
            }

        }
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

}
