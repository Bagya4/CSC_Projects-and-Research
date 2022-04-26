package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SinglyLinkedList
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an SinglyLinkedList-based list data structure
 *
 * @author Dr. King
 * @author bmahara
 *
 */
public class SinglyLinkedListTest {

	/** Stores a List of elements */
    private SinglyLinkedList<String> list;

    /**
     * Create a new instance of an linked list-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());

        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.

        try{
            list.add(15,  "fifteen");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }

    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	
    	testAddIndex();
    	
    	list.addLast("last");
    	assertEquals(2, list.size());
        assertEquals("last", list.get(1));
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	testAddLast();
    	list.last();
    	assertEquals("last", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	testAddIndex();
    	list.addFirst("first");
        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
    }
    
    /**
     * test for idx check
     */
    @Test
    public void testIdxCheck() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        
        try {
        	list.add(10, "xyx");
        }
        catch(Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * adding to middle of list
     */
    @Test
    public void testAddMid() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        list.add(1, "two");
        list.add(2, "three");
        //list.add(3, "four");
        list.add(2, "twopointsom");
        
        assertEquals("twopointsom", list.get(2));
        assertEquals(4, list.size());
    	
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	testAddFirst();
    	assertEquals("first", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();

        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");

        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));

        // Create an iterator for the list that has 1 element
        it = list.iterator();

        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        
        assertFalse(it.hasNext());
        try{
            it.next();
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        list.addLast("two");
        list.addLast("three");
        it = list.iterator();
        it.next();
        it.remove();
        assertEquals("two", list.get(0));
        
        assertEquals("two", it.next());
        assertEquals("three", it.next());
        it.remove();
        assertEquals(1, list.size());
        assertEquals("two", list.last());
        assertFalse(it.hasNext());
    }
    
    /**
     * test remove method of iterator
     */
    public void testRemoveIT() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();

        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");

        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));

        // Create an iterator for the list that has 1 element
        it = list.iterator();
    	it.remove();
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        
        assertEquals("three", list.remove(2));
        
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("four", list.get(2));
        
        assertTrue(3 == list.size());
        
        assertFalse(list.isEmpty());
        
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	testRemoveIndex();
    	assertEquals("one", list.removeFirst());
    	
    	assertEquals("two", list.get(0));
        assertEquals("four", list.get(1));
    	
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	testRemoveFirst();
    	
    	assertEquals("four", list.removeLast());
    	assertEquals("two", list.get(0));
        
    }
    
    /**
     * test remove in between
     */
    @Test
    public void testRemoveMany() {
    	list.add(0, "one");
        assertEquals(1, list.size());
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        
        
        assertEquals("two", list.remove(1));
        assertEquals("three", list.get(1));
    }
    
   

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	
    	testRemoveIndex();
    	assertEquals("two", list.set(1, "two point five"));
    	assertEquals("one", list.get(0));
        assertEquals("two point five", list.get(1));
        assertEquals("four", list.get(2));
        
    }
}

