package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import src.Node;

public class NodeTest {
    @Test
    public void testClear() {
        Node<Integer> node = new Node<Integer>(5);
        node.clear();
        assertNull(node.getLeftValue());
        assertNull(node.getRightValue());
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());
    }

    @Test
    public void testGetLeftChild() {
        Node<Integer> node = new Node<Integer>(5);
        Node<Integer> leftChild = new Node<Integer>(3);
        Node<Integer> rightChild = new Node<Integer>(10);

        assertNull(node.getLeftChild());

        node.insert(leftChild);
        node.insert(rightChild);
        assertEquals(node.getLeftChild(), leftChild);
    }

    @Test
    public void testInsertOneElementMax() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        node = node.insert(20);

        assertEquals(node.getLeftValue(), Integer.valueOf(10));
        assertEquals(node.getRightValue(), Integer.valueOf(20));
        assertTrue(node.is3Node());

    }

    @Test
    public void testInsertOneElementMin() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        node = node.insert(5);

        assertEquals(node.getLeftValue(), Integer.valueOf(5));
        assertEquals(node.getRightValue(), Integer.valueOf(10));
        assertTrue(node.is3Node());

    }

    @Test
    public void testInsertFullInserLeft() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        node = node.insert(5);

        // insert new element
        assertTrue(node.isSplitForInsert());
        // childs null
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // insert other
        node = node.insert(2);

        // check node
        assertEquals(node.getLeftValue(), Integer.valueOf(5));
        assertEquals(node.getRightValue(), null);
        // assertTrue(node.is2Node());
        assertNotNull(node.getLeftChild());
        assertNotNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // check children left
        assertEquals(node.getLeftChild().getLeftValue(), Integer.valueOf(2));
        assertEquals(node.getLeftChild().getRightValue(), null);
        // assertTrue(node.getLeftChild().is2Node());

        // check mid right
        assertEquals(node.getMiddleChild().getLeftValue(), Integer.valueOf(10));
        assertEquals(node.getMiddleChild().getRightValue(), null);
        // assertTrue(node.getRightChild().is2Node());

        // check right child
        assertNull(node.getRightChild());
        // assertTrue(node.getRightChild().is2Node());
    }

    @Test
    public void testInsertFullInserMid() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        node = node.insert(5);

        // insert new element
        assertTrue(node.isSplitForInsert());
        // childs null
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // insert other
        node = node.insert(7);

        // check node
        assertEquals(node.getLeftValue(), Integer.valueOf(7));
        assertEquals(node.getRightValue(), null);
        // assertTrue(node.is2Node());
        assertNotNull(node.getLeftChild());
        assertNotNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // check children left
        assertEquals(node.getLeftChild().getLeftValue(), Integer.valueOf(5));
        assertEquals(node.getLeftChild().getRightValue(), null);
        // assertTrue(node.getLeftChild().is2Node());

        // check mid right
        assertEquals(node.getMiddleChild().getLeftValue(), Integer.valueOf(10));
        assertEquals(node.getMiddleChild().getRightValue(), null);
        // assertTrue(node.getRightChild().is2Node());

        // check right child
        assertNull(node.getRightChild());
        // assertTrue(node.getRightChild().is2Node());
    }

    @Test
    public void testInsertFullInserRight() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        node = node.insert(5);

        // insert new element
        assertTrue(node.isSplitForInsert());
        // childs null
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // insert other
        node = node.insert(20);

        // check node
        assertEquals(node.getLeftValue(), Integer.valueOf(10));
        assertEquals(node.getRightValue(), null);
        // assertTrue(node.is2Node());
        assertNotNull(node.getLeftChild());
        assertNotNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // check children left
        assertEquals(node.getLeftChild().getLeftValue(), Integer.valueOf(5));
        assertEquals(node.getLeftChild().getRightValue(), null);
        // assertTrue(node.getLeftChild().is2Node());

        // check mid right
        assertEquals(node.getMiddleChild().getLeftValue(), Integer.valueOf(20));
        assertEquals(node.getMiddleChild().getRightValue(), null);
        // assertTrue(node.getRightChild().is2Node());

        // check right child
        assertNull(node.getRightChild());
        // assertTrue(node.getRightChild().is2Node());
    }

    @Test
    public void testInsert2Node() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        Node<Integer> child1 = new Node<Integer>(50);
        Node<Integer> child2 = new Node<Integer>(5);

        node = node.insert(child1);

        // insert new element
        assertTrue(node.isSplitForInsert());
        // childs null
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // insert other
        node = node.insert(child2);

        // check node
        assertEquals(node.getLeftValue(), Integer.valueOf(10));
        assertEquals(node.getRightValue(), null);
        // assertTrue(node.is2Node());
        assertNotNull(node.getLeftChild());
        assertNotNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // check children left
        assertEquals(node.getLeftChild().getLeftValue(), Integer.valueOf(5));
        assertEquals(node.getLeftChild().getRightValue(), null);
        // assertTrue(node.getLeftChild().is2Node());

        // check mid right
        assertEquals(node.getMiddleChild().getLeftValue(), Integer.valueOf(50));
        assertEquals(node.getMiddleChild().getRightValue(), null);
        // assertTrue(node.getRightChild().is2Node());

        // check right child
        assertNull(node.getRightChild());
        // assertTrue(node.getRightChild().is2Node());
    }

    @Test
    public void testIsLeaf() {
        // Test Case 1: Leaf Node
        Node<Integer> leafNode = new Node<>(42);
        assertTrue("Leaf Node", leafNode.isLeaf());

        // Test Case 2: Non-Leaf Node
        Node<Integer> nonLeafNode = new Node<>(42);
        nonLeafNode.insert(new Node<>(30));
        nonLeafNode.insert(new Node<>(50));

        nonLeafNode.insert(new Node<>(30));
        assertFalse("Non-Leaf Node", nonLeafNode.isLeaf());

        // Add more test cases as needed...
    }

    @Test
    public void testIsSplitForInsert() {
        // Test Case 1: Node Not Full
        Node<Integer> nodeNotFull = new Node<>(10);
        assertFalse("Node Not Full", nodeNotFull.isSplitForInsert());

        // Test Case 2: Node Full
        Node<Integer> nodeFull = new Node<>(5, 10);
        assertTrue("Node Full", nodeFull.isSplitForInsert());

        // Add more test cases as needed...
    }

    @Test
    public void testTestSplitNode() {
        // Test Case 1: Split 2-Node (Left Insert)
        Node<Integer> node = new Node<>(20, 30);
        Node<Integer> newNode = node.testSplitNode(new Node<>(15));
        assertEquals("Split 2-Node (Left Insert)", new Node<>(15), node.getLeftChild());

   
    }

    // delete
    

}


