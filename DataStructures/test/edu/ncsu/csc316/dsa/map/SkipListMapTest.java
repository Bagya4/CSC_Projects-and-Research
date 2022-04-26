package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SkipListMap
 * @author bmahara
 */
public class SkipListMapTest {

	/** map of string entries */
    private Map<Integer, String> map;
    /** map of student entries */
    private Map<Student, Integer> studentMap;

    /**
     * Create a new instance of a search table map before each test case executes
     */
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SkipListMap[3]", map.toString());
        assertEquals(1, map.size());
        //assertNull(map.put(2, "string2"));
        
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

        assertEquals("string1", map.get(1));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
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
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());

        assertEquals("string5", map.remove(5));
        assertEquals(4, map.size());
        
        assertEquals("SkipListMap[1, 2, 3, 4]", map.toString());
    }

    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertNull(studentMap.put(s1, 1));
        assertEquals("SkipListMap[Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk]]",
        		studentMap.toString());
        assertNull(studentMap.put(s2, 2));
        assertEquals("SkipListMap[Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk], "
        		+ "Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]",
        		studentMap.toString());
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s5, 5));
        
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

        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        assertTrue(2 == it.next().getKey());
        assertEquals("string3", it.next().getValue());
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

        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
     
        assertEquals("string1", it.next());
        
        assertTrue(it.hasNext());
        
        try {
        	it.remove();
        }
        catch(Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }

    }
}

