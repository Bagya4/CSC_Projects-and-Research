package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {

	/** variable to hold the tree */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(8, "huit");
        tree.put(3, "trois");
        tree.put(12, "douze");
        tree.put(9, "neuf");
        tree.put(19, "dix-neuf");
        
        assertEquals(8, (int)tree.root().getElement().getKey());
        
        tree.put(11, "onze");
        
        assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(11, (int)(tree.left(tree.right(tree.root()))).getElement().getKey());
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        testPut();
        assertEquals("neuf", tree.get((int)tree.root().getElement().getKey()));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        tree.put(8, "huit");
        tree.put(3, "trois");
        tree.put(12, "douze");
        tree.put(9, "neuf");
        tree.put(19, "dix-neuf");
        assertEquals("dix-neuf", tree.remove(19));
        
        assertEquals(8, (int)tree.root().getElement().getKey());
        assertTrue(4 == tree.size());
        
        tree.put(19, "dix-neuf");
        tree.put(11, "onze");
        
        assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals("neuf", tree.remove(9));
        assertEquals(11, (int)tree.root().getElement().getKey());
        
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
    
    /**
     * testing comparator
     */
    @Test 
    public void testComparator() {
    	
    	BinarySearchTreeMap<Integer, String> tree2 = new AVLTreeMap<>(null);
    	tree2.put(8, "huit");
        tree2.put(3, "trois");
        tree2.put(12, "douze");
        tree2.put(9, "neuf");
        tree2.put(19, "dix-neuf");
        
        assertEquals(8, (int)tree2.root().getElement().getKey());
    	
    	
    }
    

}