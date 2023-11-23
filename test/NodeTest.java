package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import src.Node;
import src.NodeInsertReturnData;
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

    // @Test
    // public void testGetLeftChild() {
    // Node<Integer> node = new Node<Integer>(5);
    // Node<Integer> leftChild = new Node<Integer>(3);
    // Node<Integer> rightChild = new Node<Integer>(10);

    // assertNull(node.getLeftChild());

    // node.insertNode(leftChild);
    // node.insertNode(rightChild);
    // assertEquals(node.getLeftChild(), leftChild);
    // }

    @Test
    public void testInsertOneElementMax() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        NodeInsertReturnData<Integer> newNode = node.insert(20);

        assertEquals(newNode.getNode().getLeftValue(), Integer.valueOf(10));
        assertEquals(newNode.getNode().getRightValue(), Integer.valueOf(20));
        assertTrue(newNode.getNode().is3Node());
        assertEquals(newNode.getResult(), NodeInsertReturnData.NOT_SPLIT);

    }

    @Test
    public void testInsertOneElementMin() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        NodeInsertReturnData<Integer> newNode = node.insert(5);

        assertEquals(newNode.getNode().getLeftValue(), Integer.valueOf(5));
        assertEquals(newNode.getNode().getRightValue(), Integer.valueOf(10));
        assertTrue(newNode.getNode().is3Node());
        assertEquals(newNode.getResult(), NodeInsertReturnData.NOT_SPLIT);

    }

    @Test
    public void testInsertFullInserLeft() {
        // Test Case 1: Insert into an Empty Node
        Node<Integer> node = new Node<Integer>(10);
        NodeInsertReturnData<Integer> newNode = node.insert(5);
        node = newNode.getNode();

        // node = node.insert(5);

        // insert new element
        assertTrue(node.isSplitForInsert());
        // childs null
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());
        assertEquals(NodeInsertReturnData.NOT_SPLIT, newNode.getResult());

        // insert other
        NodeInsertReturnData<Integer> newNode2 = node.insert(2);
        node = newNode2.getNode();

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
        NodeInsertReturnData<Integer> newNode = node.insert(5);
        node = newNode.getNode();
        // node = node.insert(5);

        // insert new element
        assertEquals(newNode.getResult(), NodeInsertReturnData.NOT_SPLIT);

        assertTrue(node.isSplitForInsert());
        // childs null
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // insert other
        NodeInsertReturnData<Integer> newNode2 = node.insert(7);
        node = newNode2.getNode();
        // node = node.insert(7);

        // check node
        assertEquals(newNode2.getResult(), NodeInsertReturnData.IS_SPLIT);

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
        NodeInsertReturnData<Integer> newNode = node.insert(5);
        node = newNode.getNode();
        // node = node.insert(5);

        // insert new element
        assertTrue(node.isSplitForInsert());
        // childs null
        assertNull(node.getLeftChild());
        assertNull(node.getMiddleChild());
        assertNull(node.getRightChild());

        // insert other
        NodeInsertReturnData<Integer> newNode2 = node.insert(20);
        node = newNode2.getNode();
        // node = node.insert(20);

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
    public void testIsLeaf() {
        // Test Case 1: Leaf Node
        Node<Integer> leafNode = new Node<>(42);
        assertTrue("Leaf Node", leafNode.isLeaf());

        // Test Case 2: Non-Leaf Node
        Node<Integer> nonLeafNode = new Node<>(42);
        nonLeafNode = nonLeafNode.insert(30).getNode();
        nonLeafNode = nonLeafNode.insert(50).getNode();

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
        NodeInsertReturnData<Integer> newNode = node.insert(10);
        node = newNode.getNode();
        // node = node.insert(Integer.valueOf(10));
        NodeInsertReturnData<Integer> newNode2 = node.insert(30);
        node = newNode2.getNode();
        // node = node.insert(Integer.valueOf(30));

        // eliminamos a la fuerza
        node.testSetCenterChild(new Node<>());

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
        NodeInsertReturnData<Integer> newNode = node.insert(10);
        node = newNode.getNode();
        // node = node.insert(Integer.valueOf(10));
        NodeInsertReturnData<Integer> newNode2 = node.insert(30);
        node = newNode2.getNode();
        // node = node.insert(Integer.valueOf(10));
        // node = node.insert(Integer.valueOf(30));

        node.testSetLeftChild(node.getLeftChild().insert(Integer.valueOf(5)).getNode());

        // eliminamos a la fuerza
        node.testSetCenterChild(new Node<>());

        // rebalance
        assertEquals(0, node.reBalance());
        assertEquals(node.getLeftValue(), Integer.valueOf(10));
        assertEquals(node.getRightValue(), null);

        assertEquals(node.getLeftChild().getLeftValue(), Integer.valueOf(5));
        assertEquals(node.getMiddleChild().getLeftValue(), Integer.valueOf(20));

    }

    @Test
    public void testReBalance3() {
        // Caso 1: Nodo balanceado, no se requiere rebalanceo
        Node<Integer> node = new Node<>(20, 40);
        Node<Integer> left = new Node<>(10);
        Node<Integer> right = new Node<>(50, 60);

        node.testSetRightChild(right);
        node.testSetLeftChild(left);

        // eliminamos a la fuerza
        node.testSetCenterChild(new Node<>());

        // rebalance
        assertEquals(0, node.reBalance());
        assertEquals(node.getLeftValue(), Integer.valueOf(40));
        assertEquals(node.getRightValue(), null);

        assertEquals(node.getMiddleChild().getLeftValue(), Integer.valueOf(50));
        assertEquals(node.getMiddleChild().getRightValue(), Integer.valueOf(60));

        assertEquals(node.getRightValue(), null);
        assertEquals(node.getRightChild(), null);

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
        assertEquals(Integer.valueOf(1), fourNode.getLeftValue());
        assertEquals(null, fourNode.getRightValue());

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
        assertEquals(Integer.valueOf(20), otherNode4.getLeftValue());
        assertEquals(Integer.valueOf(35), otherNode4.getRightValue());
    }
}
