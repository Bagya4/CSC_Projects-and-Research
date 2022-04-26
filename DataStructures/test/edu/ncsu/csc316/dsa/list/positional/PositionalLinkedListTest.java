package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest {

	/** variable to maintain a list */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        
        assertEquals(first, list.last());
        
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	testFirst();
    	Position<String> last = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals("two", list.first().getElement());
        assertEquals(last, list.last());
    	
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());        
        assertEquals(first, list.first());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        assertEquals(first, list.last());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(second, list.last());

    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> three = list.addLast("three");
        assertEquals(second, list.before(three));
        assertEquals(3, list.size());
        assertNull(list.before(first));
        
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> three = list.addLast("three");
        assertEquals(second, list.after(first));
        assertNull(list.after(three));
        assertEquals(3, list.size());
        
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> three = list.addLast("three");
        Position<String> beforeOne = list.addBefore(first, "zero");
        assertEquals(beforeOne, list.first());
        assertEquals("zero", beforeOne.getElement());
        
        assertEquals(second, list.before(three));
        
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> three = list.addLast("three");
        Position<String> afterTwo = list.addAfter(second, "twohalf");
        Position<String> afterThree = list.addAfter(three, "four");
        
        assertEquals(afterTwo, list.after(second));
        assertEquals("twohalf", afterTwo.getElement());
        assertEquals(afterThree, list.after(three));
        assertEquals("four", afterThree.getElement());
        assertNull(list.before(first));
        assertEquals(5, list.size());
        
       
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> three = list.addLast("three");
        
        assertEquals("three", list.set(three, "trois"));
        assertEquals("trois", three.getElement());
        
        assertEquals(first, list.before(second));
        
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> three = list.addLast("three");
        
        assertEquals("three", list.remove(three));
        assertEquals("two", list.last().getElement());
        assertEquals("two", list.remove(second));
        assertEquals("one", list.remove(first));
        
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertEquals(null, list.remove(first));
        
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
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
        
        Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> three = list.addLast("three");
        
        
//        list.addFirst("one");
//        list.addLast("two");
//        list.addLast("three");
        
        it = list.iterator();
        
        assertTrue(it.hasNext());
        assertEquals(first.getElement(), it.next());
        assertEquals(second.getElement(), it.next());
        assertEquals(three.getElement(), it.next());
        
        it.remove();
        
        // Use your ArrayBasedList and SinglyLinkedList test cases as a guide
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        it.remove();
        
        assertEquals(2, list.size());
    }

}
