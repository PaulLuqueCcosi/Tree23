package Tree23.src;

public interface DTATree23<T extends Comparable<T>> {

    // Insert a new element into the tree
    boolean insert(T element);

    // Search for an element in the tree
    boolean search(T element);

    // Delete an element from the tree
    void delete(T element);

    // Print the elements of the tree in pre-order
    String inOrder();

    // Check if the tree is empty
    boolean isEmpty();

    void clear();
}
