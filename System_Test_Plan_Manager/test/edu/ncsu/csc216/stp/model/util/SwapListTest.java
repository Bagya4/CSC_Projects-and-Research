package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwapListTest {
	
	/**
	 * Test add method
	 */
	@Test
	void testAdd() {
		//test adding to an empty list
		SwapList<Integer> a = new SwapList<Integer>();
		a.add(1);
		assertEquals(1, a.size());
		assertTrue(a.get(0).equals(1));
		
		//test adding to the end of the list
		a.add(2);
		assertTrue(a.get(a.size() - 1).equals(2));
		
		assertThrows(IndexOutOfBoundsException.class, () -> a.get(3));
		
		assertThrows(NullPointerException.class, () -> a.add(null));
		
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);		
		a.add(10);
		a.add(11);
		
		assertEquals(11, a.size());
		a.add(12);
	}
	
	/**
	 * Test get method
	 */
	@Test
	public void testGet() {
		SwapList<Integer> a = new SwapList<Integer>();
		
		//test getting at a negative index
		assertThrows(IndexOutOfBoundsException.class, () -> a.get(-1));
		
		//test getting at an index outside of capacity
		assertThrows(IndexOutOfBoundsException.class, () -> a.get(20));
	}
	
	/**
	 * test move up method
	 */	
	@Test
	public void testMoveUp() {
		SwapList<Integer> a = new SwapList<Integer>();
		a.add(1);
		a.add(2);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		
		a.moveUp(2);
		assertTrue(a.get(1).equals(5));
		assertTrue(a.get(2).equals(2));
		
	}
	
	/**
	 * test move down method
	 */	
	@Test
	public void testMoveDown() {
		SwapList<Integer> a = new SwapList<Integer>();
		a.add(1);
		a.add(2);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		
		a.moveDown(0);
		assertTrue(a.get(0).equals(2));
		assertTrue(a.get(1).equals(1));
		
	}
	
	/**
	 * test move to front
	 */	
	@Test
	public void testMoveToFront() {
		SwapList<Integer> a = new SwapList<Integer>();
		a.add(1);
		a.add(2);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		
		a.moveToFront(3);
		assertTrue(a.get(0).equals(6));
		assertTrue(a.get(1).equals(1));
		
	}
	
	/**
	 * test move to back
	 */	
	@Test
	public void testMoveToBack() {
		SwapList<Integer> a = new SwapList<Integer>();
		a.add(1);
		a.add(2);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		
		a.moveToBack(3);
		assertTrue(a.get(5).equals(6));
		assertTrue(a.get(0).equals(1));
		assertTrue(a.get(3).equals(7));
		
	}

}
