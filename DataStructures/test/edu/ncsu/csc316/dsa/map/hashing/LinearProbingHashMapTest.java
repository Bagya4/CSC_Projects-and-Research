package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 *
 */
public class LinearProbingHashMapTest {

	/** holds map  */
    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());

        map.put(2, "string2");
        map.put(23, "str23");
        map.put(4, "str4");
        assertEquals("string2", map.get(2));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertTrue(map.isEmpty());
        map.put(2, "string2");
        map.put(3, "str3");
        map.put(4, "str4");
        
        assertEquals("str3", map.remove(3));
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {

    	map.put(2, "string2");
        map.put(23, "str23");
        map.put(4, "str4");
        map.put(37, "str37");
        
        
       Iterator<Integer> it = map.iterator();
       
       assertEquals(2, (int)it.next()); 
       assertEquals(23, (int)it.next()); 
       assertEquals(4, (int)it.next());
       assertEquals(37, (int)it.next());

    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	map.put(2, "string2");
        map.put(23, "str23");
        map.put(4, "str4");
        map.put(37, "str37");
        
        map.remove(4);
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        assertEquals(2, (int)it.next().getKey()); 
        assertEquals(23, (int)it.next().getKey()); 
        assertEquals(37, (int)it.next().getKey());

    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	Map<Integer, String> map2 = new LinearProbingHashMap<Integer, String>(true);
    	map2.put(2, "string2");
        map2.put(23, "string23");
        
        Iterator<String> it = map2.values().iterator();
        
        assertEquals("string2", it.next()); 
        assertEquals("string23", it.next()); 
        
        Map<Integer, String> map3 = new LinearProbingHashMap<Integer, String>(1);
        map3.put(2, "string2");
        Iterator<String> it3 = map3.values().iterator();
        
        assertEquals("string2", it3.next());
        
        Map<Integer, String> map4 = new LinearProbingHashMap<Integer, String>();
        map4.put(2, "string2");
        Iterator<String> it4 = map4.values().iterator();
        
        assertEquals("string2", it4.next());
        
    }
}