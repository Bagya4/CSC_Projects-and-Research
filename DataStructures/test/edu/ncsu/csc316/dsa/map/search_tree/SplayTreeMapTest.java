package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {

	/** variable to hold tree */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(10, "ten");
        tree.put(20, "twenty");
        tree.put(30, "trente");
        tree.put(30, "thirty");
       
        assertEquals(30, (int)tree.root().getElement().getKey());
        assertTrue(10 == (int) tree.left(tree.left(tree.root())).getElement().getKey()); 
        
        setUp();
        
        tree.put(10, "ten");
        tree.put(30, "thirty");
        tree.put(20, "twenty");
        assertEquals(20, (int)tree.root().getElement().getKey());
        assertTrue(10 == (int) tree.left(tree.root()).getElement().getKey()); 
        assertTrue(30 == (int) tree.right(tree.root()).getElement().getKey()); 
        
        BinarySearchTreeMap<Integer, String> tree2 = new SplayTreeMap<Integer, String>();
        tree2.put(30, "trente");
        tree2.put(20, "twenty");
        tree2.put(10, "ten");
        assertEquals(10, (int)tree2.root().getElement().getKey());

        BinarySearchTreeMap<Integer, String> tree3 = new SplayTreeMap<Integer, String>();
        tree3.put(16, "sixteen");
        tree3.put(13, "thirteen");
        tree3.put(14, "4teen");
        assertEquals(14, (int)tree3.root().getElement().getKey());
        assertEquals(13, (int)tree3.sibling(tree3.right(tree3.root())).getElement().getKey());
        
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
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
    	tree.put(10, "ten");
        tree.put(20, "twenty");
        tree.put(30, "thirty");
        
        assertEquals("twenty", tree.left(tree.root()).getElement().getValue());
        assertEquals("thirty", tree.get(30));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {

    	tree.put(5, "dog");
    	tree.put(10, "cat");
    	tree.put(15, "frog");
        tree.put(20, "wolf");
        tree.put(8, "lion");
        
        tree.remove(8);
        assertEquals(15, (int)tree.root().getElement().getKey() );   
        
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
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
    	
    	BinarySearchTreeMap<Integer, String> tree2 = new SplayTreeMap<>(null);
    	tree2.put(10, "ten");
        tree2.put(20, "twenty");
        tree2.put(30, "trente");
        tree2.put(30, "thirty");
       
        assertEquals(30, (int)tree2.root().getElement().getKey());
        assertTrue(10 == (int) tree2.left(tree2.left(tree2.root())).getElement().getKey()); 
    	
    	
    }
}