package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import src.DTATree23;
import src.Tree23;

public class TestDTATree23 {
    @Test
    public void testClear() {
        DTATree23<Integer> tree = new Tree23<Integer>();
        tree.insert(5);
        tree.insert(10);

        assertFalse(tree.isEmpty());

        tree.clear();

        assertTrue(tree.isEmpty());
    }

    @Test
    public void testDelete() {
        DTATree23<Integer> tree = new Tree23<Integer>();
        tree.insert(5);
        tree.insert(10);

        assertTrue(tree.search(5));
        assertTrue(tree.search(10));

        tree.delete(5);

        assertFalse(tree.search(5));
        assertTrue(tree.search(10));
    }

    @Test
    public void testInOrder() {
        DTATree23<Integer> tree = new Tree23<Integer>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(8);

        assertEquals("3 5 8", tree.inOrder());
    }

    @Test
    public void testInsert() {
        DTATree23<Integer> tree = new Tree23<Integer>();
        assertTrue(tree.isEmpty());

        tree.insert(5);

        assertFalse(tree.isEmpty());
        assertTrue(tree.search(5));
    }

    @Test
    public void testInsert10To150() {
        DTATree23<Integer> tree = new Tree23<Integer>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(60);
        tree.insert(70);
        tree.insert(80);
        tree.insert(90);
        tree.insert(100);
        tree.insert(110);
        tree.insert(120);
        tree.insert(130);
        tree.insert(140);
        tree.insert(150);

        String inOrden = "10 20 30 40 50 60 70 80 90 100 110 120 130 140 150";
        assertEquals(inOrden, tree.inOrder());
    }

    @Test
    public void testDelete10To150() {
        DTATree23<Integer> tree = new Tree23<Integer>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(60);
        tree.insert(70);
        tree.insert(80);
        tree.insert(90);
        tree.insert(100);
        tree.insert(110);
        tree.insert(120);
        tree.insert(130);
        tree.insert(140);
        tree.insert(150);

        String inOrden = "10 20 30 40 50 60 70 80 90 100 110 120 130 140 150";
        assertEquals(inOrden, tree.inOrder());

        // delete
        tree.delete(150);
        String inOrden2String = "10 20 30 40 50 60 70 80 90 100 110 120 130 140";
        assertEquals(inOrden2String, tree.inOrder());

        tree.delete(60);
        String inOrden3String = "10 20 30 40 50 70 80 90 100 110 120 130 140";
        assertEquals(inOrden3String, tree.inOrder());

        tree.delete(40);
        String inOrden4String = "10 20 30 50 70 80 90 100 110 120 130 140";
        assertEquals(inOrden4String, tree.inOrder());

        tree.delete(100);
        String inOrden5String = "10 20 30 50 70 80 90 110 120 130 140";
        assertEquals(inOrden5String, tree.inOrder());

        tree.delete(200);
        inOrden5String = "10 20 30 50 70 80 90 110 120 130 140";
        assertEquals(inOrden5String, tree.inOrder());

        tree.delete(90);
        String inOrden6String = "10 20 30 50 70 80 110 120 130 140";
        assertEquals(inOrden6String, tree.inOrder());

        tree.delete(110);
        String inOrden7String = "10 20 30 50 70 80 120 130 140";
        assertEquals(inOrden7String, tree.inOrder());

        tree.delete(50);
        String inOrden8String = "10 20 30 70 80 120 130 140";
        assertEquals(inOrden8String, tree.inOrder());

        tree.delete(10);
        assertEquals("20 30 70 80 120 130 140", tree.inOrder());

        tree.delete(70);
        assertEquals("20 30 80 120 130 140", tree.inOrder());

        tree.delete(130);
        assertEquals("20 30 80 120 140", tree.inOrder());

        tree.delete(120);
        assertEquals("20 30 80 140", tree.inOrder());

        tree.delete(80);
        assertEquals("20 30 140", tree.inOrder());

         tree.delete(20);
        assertEquals("30 140", tree.inOrder());

        tree.delete(30);
        assertEquals("140", tree.inOrder());

          tree.delete(140);
        assertEquals("", tree.inOrder());

    }

    @Test
    public void testIsEmpty() {
        DTATree23<Integer> tree = new Tree23<Integer>();
        assertTrue(tree.isEmpty());

        tree.insert(5);

        assertFalse(tree.isEmpty());
    }

    @Test
    public void testSearch() {
        DTATree23<Integer> tree = new Tree23<Integer>();
        assertFalse(tree.search(5));

        tree.insert(5);

        assertTrue(tree.search(5));
    }
}
