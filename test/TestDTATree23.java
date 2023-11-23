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
