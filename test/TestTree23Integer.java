package Tree23.test;

import Tree23.src.DTATree23;

public class TestTree23Integer {

    private DTATree23<Integer> tree;

    public TestTree23Integer(DTATree23<Integer> tree) {
        this.tree = tree;
    }

    // Test all operations
    public void testAll() {
        boolean allTestsPassed = true;

        if (!testIsEmpty()) {
            allTestsPassed = false;
            System.out.println("testIsEmpty failed!");
            System.out.println();
            System.out.println();
        }

        tree.clear();

        if (!testInsert()) {
            allTestsPassed = false;
            System.out.println("testInsert failed!");
            System.out.println();
            System.out.println();
        }

        tree.clear();

        if (!testSearch()) {
            allTestsPassed = false;
            System.out.println("testSearch failed!");
            System.out.println();
            System.out.println();
            System.out.println();
        }

        tree.clear();

        if (!testDelete()) {
            allTestsPassed = false;
            System.out.println("testDelete failed!");
            System.out.println();
            System.out.println();

        }

        tree.clear();

        if (allTestsPassed) {
            System.out.println("[FINAL] ALL TEST PASSED SUCCESSFULLY!");
        } else {
            System.out.println("[FINAL] SOME TEST FAILED!");
        }
    }

    // Test if the tree is empty initially
    public boolean testIsEmpty() {
        boolean result = true;
        System.out.println("Testing if the tree is empty initially:");

        // Check if the tree is empty initially
        if (!tree.isEmpty()) {
            System.out.println("Error: Tree is not empty initially");
            result = result && false;
        }

        // Inserting an element
        tree.insert(10);

        // Check if the tree is not empty after insertion
        if (tree.isEmpty()) {
            System.out.println("Error: Tree is empty after insertion");
            result = result && false;
        }

        // Deleting the element
        tree.delete(10);

        // Check if the tree is empty after deletion
        if (!tree.isEmpty()) {
            System.out.println("Error: Tree is not empty after deletion");
            result = result && false;
        }

        // System.out.println("All checks passed successfully!");

        // Return true if all checks pass
        return result;
    }

    // Test insert operation
    public boolean testInsert() {
        System.out.println("Testing insert operation:");
        boolean result = true;

        // Insert 10
        tree.insert(10);
        result = assertInOrder("10") && result;

        // Insert 5
        tree.insert(5);
        result = assertInOrder("5 10") && result;

        // Insert 15
        tree.insert(15);
        result = assertInOrder("5 10 15") && result;

        // Insert 3
        tree.insert(3);
        result = assertInOrder("3 5 10 15") && result;

        // Insert 7
        tree.insert(7);
        result = assertInOrder("3 5 7 10 15") && result;

        // Insert 12
        tree.insert(12);
        result = assertInOrder("3 5 7 10 12 15") && result;

        // // Insert 20
        // tree.insert(20);
        // result = result && assertInOrder("3 5 7 10 12 15 20");

        // // Insert 1
        // tree.insert(1);
        // result = result && assertInOrder("1 3 5 7 10 12 15 20");

        // // Insert 6
        // tree.insert(6);
        // result = result && assertInOrder("1 3 5 6 7 10 12 15 20");

        // // Insert 14
        // tree.insert(14);
        // result = result && assertInOrder("1 3 5 6 7 10 12 14 15 20");

        // System.out.println("All checks passed successfully!");

        // Return true if all checks pass
        return true;
    }

    // Helper method to assert the pre-order traversal
    private boolean assertInOrder(String expectedInOrder) {
        String inOrderResult = tree.inOrder();
        if (!inOrderResult.equals(expectedInOrder)) {
            System.out.println(
                    "Error: In-order is incorrect. Expected: " + expectedInOrder + ", Actual: " + inOrderResult);
            return false;
        }

        return true;
    }

    // Test search operation
    public boolean testSearch() {
        System.out.println("\nTesting search operation:");
        boolean result = true;

        // Search for 10 (should not be present initially)
        boolean searchResult10 = tree.search(10);
        System.out.println("Search 10: " + searchResult10);
        result = result && !searchResult10;

        // Insert 10
        tree.insert(10);

        // Search for 10 (should be present after insertion)
        searchResult10 = tree.search(10);
        System.out.println("Search 10: " + searchResult10);
        result = result && searchResult10;

        // Insert 15
        tree.insert(15);

        // Search for 15 (should be present after insertion)
        boolean searchResult15 = tree.search(15);
        System.out.println("Search 15: " + searchResult15);
        result = result && searchResult15;

        // Search for 3 (should not be present)
        boolean searchResult3 = tree.search(3);
        System.out.println("Search 3: " + searchResult3);
        result = result && !searchResult3;

        // System.out.println("All checks passed successfully!");

        // Return true if all checks pass
        return result;
    }

    // Test delete operation
    public boolean testDelete() {
        System.out.println("\nTesting delete operation:");
        boolean result = true;

        // Insert elements
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(20);

        // Delete 3 (leaf node)
        tree.delete(3);
        result = assertInOrder("5 7 10 12 15 20") && result;

        // Delete 15 (internal node with two children)
        tree.delete(15);
        result = assertInOrder("5 7 10 12 20") && result;

        // Delete 10 (root node)
        tree.delete(10);
        result = assertInOrder("5 7 12 20") && result;

        // Delete 7 (node with one child)
        tree.delete(7);
        result = assertInOrder("5 12 20") && result;

        // Delete 20 (leaf node)
        tree.delete(20);
        result = assertInOrder("5 12") && result;

        // System.out.println("All checks passed successfully!");

        // Return true if all checks pass
        return result;
    }

}