package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 *
 */
public class RedBlackTreeMapTest {

	/** variable to hold the tree */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());

        tree.put(30, "thirty");
        assertTrue(tree.root().getElement().getKey() == 30);
        tree.put(20, "twenty");
        assertTrue(tree.root().getElement().getKey() == 30);
        assertTrue(tree.left(tree.root()).getElement().getKey() == 20);
        tree.put(10, "ten");
        assertTrue(tree.root().getElement().getKey() == 20);
        
        setUp();
        
        tree.put(30, "thirty");
        assertTrue(tree.root().getElement().getKey() == 30);
        tree.put(20, "twenty");
        assertTrue(tree.root().getElement().getKey() == 30);
        assertTrue(tree.left(tree.root()).getElement().getKey() == 20);
        tree.put(40, "forty");
        tree.put(10, "ten");
        assertTrue(tree.root().getElement().getKey() == 30);
        assertTrue(tree.left(tree.root()).getElement().getKey() == 20);
        assertTrue(tree.right(tree.root()).getElement().getKey() == 40);
        assertTrue(tree.left(tree.left(tree.root())).getElement().getKey() == 10);
        
        setUp();
        
        tree.put(14, "4teen");
        tree.put(5, "five");        
        tree.put(16, "sixteen");
        tree.put(7, "seven");
        tree.put(4, "four");
        tree.remove(16);
        assertTrue(tree.root().getElement().getKey() == 5);
        
        setUp();
        tree.put(14, "4teen");
        tree.put(7, "seven");
        tree.put(16, "sixteen");
        tree.put(4, "four");
        tree.put(15, "fifteen");      
        tree.put(12, "twelve");
        tree.put(18, "8teen");
        tree.put(3, "five");
        tree.put(5, "five");
        tree.put(17, "seventeen");
        
        tree.remove(3);
        tree.remove(12);
        tree.remove(17);
        tree.remove(18);
        tree.remove(15);
        
        tree.remove(16);
        assertTrue(tree.root().getElement().getKey() == 5);
      
        
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
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
    	tree.put(30, "thirty");
        assertTrue(tree.root().getElement().getKey() == 30);
        tree.put(20, "twenty");
        assertTrue(tree.root().getElement().getKey() == 30);
        assertTrue(tree.left(tree.root()).getElement().getKey() == 20);
        tree.put(40, "forty");
        tree.put(10, "dix");
        tree.put(10, "ten");
        assertEquals(tree.get(10), "ten");
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        
        tree.put(14, "4teen");
        tree.put(7, "seven");
        tree.put(16, "sixteen");
        tree.put(15, "fifteen");
        tree.put(4, "four");
        tree.put(18, "eighteen");
        tree.put(8, "eight");
        tree.put(17, "7teen");
        tree.put(5, "five");
        
        tree.remove(8);
        assertTrue(tree.root().getElement().getKey() == 14);
        assertTrue(tree.left(tree.root()).getElement().getKey() == 5);
        assertTrue(tree.left(tree.left(tree.root())).getElement().getKey() == 4);
        assertTrue(tree.right(tree.left(tree.root())).getElement().getKey() == 7);
        
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    /**
     * test comparator
     */
    @Test
    public void testComparator() {
    	BinarySearchTreeMap<Integer, String> tree2 = new RedBlackTreeMap<>(null);
    	tree2.put(30, "thirty");
        assertTrue(tree2.root().getElement().getKey() == 30);
        tree2.put(20, "twenty");
        assertTrue(tree2.root().getElement().getKey() == 30);
        assertTrue(tree2.left(tree2.root()).getElement().getKey() == 20);
        tree2.put(10, "ten");
        assertTrue(tree2.root().getElement().getKey() == 20);
    }
}