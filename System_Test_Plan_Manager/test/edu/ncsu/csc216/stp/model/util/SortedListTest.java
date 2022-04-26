package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

class SortedListTest {

	@Test
	public void testSortedList() {
		// test the constructor
		SortedList<TestPlan> s = new SortedList<TestPlan>();
		assertEquals(0, s.size());
	}

	@Test
	public void testAdd() {
		SortedList<TestPlan> s = new SortedList<TestPlan>();
		TestPlan tp1 = new TestPlan("A Test Plan");
		TestPlan tp2 = new TestPlan("B Test Plan");
		TestPlan tp3 = new TestPlan("C Test Plan");
		TestPlan tp4 = new TestPlan("D Test Plan");
		TestPlan tp5 = new TestPlan("E Test Plan");

		s.add(tp1);
		// Check that tp1 was added
		assertEquals(s.get(0), tp1);

		s.add(tp2);
		s.add(tp5);
		s.add(tp4);
		s.add(tp3);
		
		//try to add null element
		assertThrows(NullPointerException.class, () -> s.add(null));

		//May fail TS case
//		//try to add duplicate element
		assertThrows(IllegalArgumentException.class, () -> s.add(tp5));

		// Check that tp2 is after tp1
		assertEquals(s.get(1), tp2);
		// Check that tp3 is after tp2
		assertEquals(s.get(2), tp3);

		// Check that tp4 is after tp3
		assertEquals(s.get(3), tp4);

		// Check that tp5 is after tp4
		assertEquals(s.get(4), tp5);

	}

	@Test
	public void testRemove() {
		SortedList<TestPlan> s = new SortedList<TestPlan>();
		TestPlan tp1 = new TestPlan("A Test Plan");
		TestPlan tp2 = new TestPlan("B Test Plan");
		TestPlan tp3 = new TestPlan("C Test Plan");
		TestPlan tp4 = new TestPlan("D Test Plan");
		TestPlan tp5 = new TestPlan("E Test Plan");

		s.add(tp1);
		s.add(tp2);
		s.add(tp5);
		s.add(tp4);
		s.add(tp3);
		assertEquals(5, s.size());

		//remove from beginning of list
		assertDoesNotThrow(() -> s.remove(0));
		assertEquals(4, s.size());
		assertEquals(s.get(0), tp2);
		assertEquals(s.get(1), tp3);
		assertEquals(s.get(2), tp4);
		assertEquals(s.get(3), tp5);
		
		//remove from end of list
		assertDoesNotThrow(() -> s.remove(3));
		assertEquals(s.get(0), tp2);
		assertEquals(s.get(1), tp3);
		assertEquals(s.get(2), tp4);
		
		//remove from middle of list
		assertDoesNotThrow(() -> s.remove(1));
		assertEquals(s.get(0), tp2);
		assertEquals(s.get(1), tp4);
		
	}
	
	@Test
	public void testSize() {
		SortedList<TestPlan> s = new SortedList<TestPlan>();
		TestPlan tp1 = new TestPlan("A Test Plan");
		TestPlan tp2 = new TestPlan("B Test Plan");
		TestPlan tp3 = new TestPlan("C Test Plan");
		TestPlan tp4 = new TestPlan("D Test Plan");
		TestPlan tp5 = new TestPlan("E Test Plan");

		s.add(tp1);
		s.add(tp2);
		s.add(tp5);
		s.add(tp4);
		s.add(tp3);
		assertEquals(5, s.size());

		//remove from beginning of list
		assertDoesNotThrow(() -> s.remove(0));
		assertEquals(4, s.size());

		
		//remove from end of list
		assertDoesNotThrow(() -> s.remove(3));
		assertEquals(3, s.size());

		//remove from middle of list
		assertDoesNotThrow(() -> s.remove(1));
		assertEquals(2, s.size());
		
	}
}
