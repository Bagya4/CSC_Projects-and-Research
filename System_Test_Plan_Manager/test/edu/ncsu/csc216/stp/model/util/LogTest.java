package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test Log class
 * @author marikilgus
 *
 */
public class LogTest {

	/**
	 * Test add method
	 */
	@Test
	public void testAdd() {
		//test adding to an empty list
		Log<Integer> a = new Log<Integer>();
		a.add(1);
		assertEquals(1, a.size());
		assertTrue(a.get(0).equals(1));

		//test adding to the end of the list
		a.add(2);
		assertTrue(a.get(a.size() - 1).equals(2));
		
		//test adding a null element
		assertThrows(NullPointerException.class, () -> a.add(null));
		
		//test adding duplicate element
		assertDoesNotThrow(() -> a.add(2), "Should not throw exception");

		//test adding element outside of capacity
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		a.add(10);
		a.add(11);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		a.add(10);
		a.add(11);
	}
	
	/**
	 * Test get method
	 */
	@Test
	public void testGet() {
		Log<Integer> a = new Log<Integer>();
		
		//test getting at a negative index
		assertThrows(IndexOutOfBoundsException.class, () -> a.get(-1));
		
		//test getting at an index outside of capacity
		assertThrows(IndexOutOfBoundsException.class, () -> a.get(20));
	}
	
//	/**
//	 * Test size method
//	 */
//	@Test
//	public void testSize() {
//	}
}
