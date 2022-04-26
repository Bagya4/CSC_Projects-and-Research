package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {

	/** Linked tree */
    private LinkedBinaryTree<String> tree;
    /** Node one */
    private Position<String> one;
    /** Node 2 */
    private Position<String> two;
    /** Node 3 */
    private Position<String> three;
    /** Node 4 */
    private Position<String> four;
    /** Node 5 */
    private Position<String> five;
    /** Node 6 */
    private Position<String> six;
    /** Node 7 */
    private Position<String> seven;
    /** Node 8 */
    private Position<String> eight;
    /** Node 9 */
    private Position<String> nine;
    /** Node 10 */
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * @param <E> generic type
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }
    

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test validate
     */
    @Test
    public void testVal() {
    	//createTree();
    	InvalidPosition<String> inv = new InvalidPosition<>();
    	assertTrue(null == inv.getElement());
    	try {
    		tree.validate(inv);
    		fail();
    	}
    	catch(IllegalArgumentException e) {
    		assertEquals(e.getMessage(), "Position is not a valid linked binary tree node");
    	}
    	
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "un"));
        assertEquals("un", tree.root().getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertTrue(10 == tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertTrue(2 == tree.numChildren(four));
        assertTrue(0 == tree.numChildren(nine));
        
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertTrue(null == tree.parent(one));
        assertEquals(four, tree.parent(eight));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(eight, tree.sibling(nine));
        assertEquals(six, tree.sibling(ten));
        assertEquals(five, tree.sibling(seven));
        assertTrue(null == tree.sibling(four));
        assertTrue(null == tree.sibling(one));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertFalse(tree.isInternal(nine));
        assertTrue(tree.isInternal(one));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(nine));
        
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(ten));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        
        assertEquals("one", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("six", it.next().getElement());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        
        assertEquals("six", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("five", it.next().getElement());
        
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        
        assertEquals("six", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("one", it.next().getElement());
        
        
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	createTree();
        assertFalse(tree.isEmpty());
    }
    
    /**
     * Test the output of the levelOrder traversal behavior
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        
        assertEquals("one", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("three", it.next().getElement());
        assertEquals("six", it.next().getElement());
        
        try {
        	it.remove();
        	fail();
        }
        catch(UnsupportedOperationException e) {
        	assertEquals(e.getMessage(), "The remove operation is not supported yet.");
        }
        
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	createTree();
        Position<String> eightPointFive = tree.addLeft(nine, "8point5");
        assertEquals(eightPointFive, tree.left(nine));
        try {
        	tree.addLeft(nine, "8point5");
        	fail();
        }
        catch(IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "Node already has a left child.");
        }
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	createTree();
        Position<String> eleven = tree.addRight(nine, "eleven");
        assertEquals(eleven, tree.right(nine));
        try {
        	tree.addRight(nine, "eleven");
        	fail();
        }
        catch(IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "Node already has a right child.");
        }
        
        try {
        	tree.addRoot("root");
        	fail();
        }
        catch(IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "The tree already has a root.");
        }
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertEquals("three", tree.remove(three));
        assertTrue(9 == tree.size());
        assertEquals(four, tree.right(one));
        
        try {
        	tree.remove(one);
        	fail();
        }
        catch(IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "The node has two children");
        }
    }
    
    /**
     * Test toString
     */
    @Test
    public void testToString() {
    	assertEquals("LinkedBinaryTree[\n]", tree.toString());
    	one = tree.addRoot("one");
    	tree.setRoot(one);
        two = tree.addLeft(one, "two");
    	assertEquals("LinkedBinaryTree[\none\n two\n]", tree.toString());
    }
}
