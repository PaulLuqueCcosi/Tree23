package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import src.Node;
import src.Node;

public class NodeTest {
    @Test
    public void testClear() {
        Node<Integer> node = new Node<Integer>(5);
        node.clear();
        assertNull(node.getLeftValue());
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
    @Test
    public void testReBalance1() {
        // Caso 1: Nodo balanceado, no se requiere rebalanceo
        Node<Integer> node = new Node<>(20);
        node = node.insert(Integer.valueOf(10));
        node = node.insert(Integer.valueOf(30));

        // eliminamos a la fuerza
        node.testSetCenterChild(null);

        // rebalance
        assertEquals(1, node.reBalance());
        assertEquals(node.getLeftValue(), null);
        assertEquals(node.getRightValue(), null);

        assertEquals(node.getLeftChild(), new Node<>(10, 20));
        assertEquals(node.getRightChild(), null);

    }

    @Test
    public void testReBalance2() {
        // Caso 1: Nodo balanceado, no se requiere rebalanceo
        Node<Integer> node = new Node<>(20);
        node = node.insert(Integer.valueOf(10));
        node = node.insert(Integer.valueOf(30));
        node = node.insert(Integer.valueOf(5));

        // eliminamos a la fuerza
        node.testSetCenterChild(null);

        // rebalance
        assertEquals(0, node.reBalance());
        assertEquals(node.getLeftValue(), Integer.valueOf(10));
        assertEquals(node.getRightValue(), null);

        assertEquals(node.getLeftChild().getLeftValue(), Integer.valueOf(5));
        assertEquals(node.getRightChild().getLeftValue(), Integer.valueOf(20));

    }

    @Test
    public void testRemoveValueInNode() {
        // Caso 1: Eliminar un valor presente en un nodo 2-nodo
        Node<Integer> twoNode = new Node<>(1);
        assertEquals(1, twoNode.removeValueInNode(1));
        // verificamos que no tiene elmentos
        assertNull(twoNode.getLeftChild());
        assertNull(twoNode.getRightChild());

        // Caso 2: Eliminar un valor presente en un nodo 3-nodo
        Node<Integer> threeNode = new Node<>(1, 2);
        assertEquals(0, threeNode.removeValueInNode(1));
        // verifcamos los valores
        assertEquals(Integer.valueOf(2), threeNode.getLeftValue());
        assertEquals(null, threeNode.getRightValue());

        // Caso 2: Eliminar un valor presente en un nodo 3-nodo
        Node<Integer> fourNode = new Node<>(1, 2);
        assertEquals(0, fourNode.removeValueInNode(2));

        // verifcamos los valores
        assertEquals(Integer.valueOf(1), threeNode.getLeftValue());
        assertEquals(null, threeNode.getRightValue());

    }

    @Test
    public void testGetSuccessor() {
        // Caso 1: Nodo hoja, no tiene sucesor
        Node<Integer> leafNode = new Node<>(1);
        assertNull(leafNode.getSuccesor(leafNode.getLeftValue()));

        // Caso 2: Nodo 2-nodo, sucesor es el valor en el nodo
        Node<Integer> twoNode = new Node<>(1);
        twoNode.insert(2);
        assertEquals(null, twoNode.getSuccesor(twoNode.getLeftValue()));

        // Caso 3: Nodo 3-nodo, sucesor es el valor más pequeño del subárbol derecho
        Node<Integer> threeNode = new Node<>(1, 2);
        threeNode.insert(3);
        assertEquals(new Node<>(3), threeNode.getSuccesor(threeNode.getLeftValue()));

    }

    @Test
    public void testSwapValue() {
        // Caso 1: Intercambiar valores en un nodo 2-nodo
        Node<Integer> initNode = new Node<>(10, 20);
        Node<Integer> otherNode = new Node<>(40);

        initNode.swapValue(20, otherNode);

        assertEquals(Integer.valueOf(10), initNode.getLeftValue());
        assertEquals(Integer.valueOf(40), initNode.getRightValue());
        // other node
        assertEquals(Integer.valueOf(20), otherNode.getLeftValue());
        assertEquals(null, otherNode.getRightValue());

        // Caso 2:
        Node<Integer> initNode2 = new Node<>(10, 20);
        Node<Integer> otherNode2 = new Node<>(30);

        initNode2.swapValue(10, otherNode2);

        assertEquals(Integer.valueOf(30), initNode2.getLeftValue());
        assertEquals(Integer.valueOf(20), initNode2.getRightValue());
        // other node
        assertEquals(Integer.valueOf(10), otherNode2.getLeftValue());
        assertEquals(null, otherNode2.getRightValue());

        // Caso 3:
        Node<Integer> initNode3 = new Node<>(10, 20);
        Node<Integer> otherNode3 = new Node<>(15, 17);

        initNode3.swapValue(10, otherNode3);

        assertEquals(Integer.valueOf(15), initNode3.getLeftValue());
        assertEquals(Integer.valueOf(20), initNode3.getRightValue());
        // other node
        assertEquals(Integer.valueOf(10), otherNode3.getLeftValue());
        assertEquals(Integer.valueOf(17), otherNode3.getRightValue());

        // Caso 4:
        Node<Integer> initNode4 = new Node<>(10, 20);
        Node<Integer> otherNode4 = new Node<>(30, 35);

        initNode4.swapValue(20, otherNode4);

        assertEquals(Integer.valueOf(10), initNode4.getLeftValue());
        assertEquals(Integer.valueOf(30), initNode4.getRightValue());
        // other node
        assertEquals(Integer.valueOf(30), otherNode3.getLeftValue());
        assertEquals(Integer.valueOf(35), otherNode3.getRightValue());
    }
}
