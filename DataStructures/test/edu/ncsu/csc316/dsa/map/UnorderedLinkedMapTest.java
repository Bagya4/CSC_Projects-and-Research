package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 *
 */
public class UnorderedLinkedMapTest {

	/** map of entries */
    private Map<Integer, String> map;

    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertEquals("string3", map.put(3, "stringtrois"));
        assertEquals(1, map.size());
        assertEquals("stringtrois", map.get(3));
        assertNull(map.get(0));
    }

    /**
     * Test the output of the get(k) behavior
     */
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());

        assertNull(map.get(0));
        
    }

    /**
     * Test the output of the remove(k) behavior
     */
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertEquals(4, map.size());
        
        assertEquals("UnorderedLinkedMap[1, 4, 2, 3]", map.toString());
        
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> it = map.iterator();
        assertTrue(1 == it.next());
        assertTrue(it.hasNext());
        
        try {
        	it.remove();
        }
        catch(Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
        
        
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
        
        
        assertTrue(1 == it.next().getKey());
        
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> st = map.values().iterator();        
        assertEquals("string1", st.next());
        
        assertTrue(st.hasNext());
        
        try {
        	st.remove();
        }
        catch(Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }

    }
}
