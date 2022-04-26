package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 *
 */
public class BinarySearchTreeMapTest {

	/** tree variable */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(0, "zero");
        tree.put(3, "three");
        tree.put(3, "trois");
        assertEquals("one", tree.root().getElement().getValue());
        assertEquals(3, tree.size());
    }
    
    /**
     * Test the output of the toString
     */     
    @Test
    public void testToString() {
    	assertEquals("BalanceableBinaryTree[\nnull\n]", tree.toString());
    	testPut();
    	//assertEquals("", tree.toString());

        
    }
    
    /**
     * Test the output of entrySet
     */     
    @Test
    public void testEntrySet() {
    	testPut();
    	Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
    	assertTrue(it.hasNext());
    	 Entry<Integer, String> entry = it.next();
    	 //in in order
         assertEquals(0, (int)(entry.getKey()));
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        tree.put(0, "zero");
        tree.put(3, "three");
        tree.put(4, "quatre");
        tree.put(4, "four");
        
        assertEquals(tree.get(4), "four");
       
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(1,  "one");
        tree.put(2,  "two");
        //removing node with right child
        assertEquals("one", tree.remove(1));
        assertEquals("two", tree.root().getElement().getValue());
        tree.remove(2);
        
        //removing node with  left child
        tree.put(1,  "one");
        tree.put(0, "zero");
        assertEquals("one", tree.remove(1));
        assertEquals("zero", tree.root().getElement().getValue());
        tree.remove(0);
        
        //removing node two children
        tree.put(1,  "one");
        tree.put(0, "zero");
        tree.put(2,  "two");        
        assertEquals("one", tree.remove(1));
        assertEquals("two", tree.root().getElement().getValue());
        assertEquals(2, tree.size());
        
    }
}