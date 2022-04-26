package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

class TestPlanTest {

	/**
	 * Tests TestPlan constructor
	 */
	@Test
	public void testPlanTest() {
		TestPlan t = new TestPlan("A New Test Plan");
		assertEquals("A New Test Plan", t.getTestPlanName());
	}
	
	/**
	 * Tests addTestCase method
	 */
	@Test
	public void testAddTestCase() {
		TestPlan tp = assertDoesNotThrow(() -> new TestPlan("A New Test Plan"));
		TestCase tc1 = new TestCase("A Test Case 1", "requirements", "the best test ever", "Pop up that says hello");
		tp.addTestCase(tc1);
		assertEquals(tp.getTestCase(0), tc1);
	}

	/**
	 * Tests getTestCasesAsArray()
	 */
	@Test
	public void testGetTestCasesAsArray() {
		TestPlan tp = assertDoesNotThrow(() -> new TestPlan("Test Plan"));
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");

		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		assertDoesNotThrow(() -> tp.addTestCase(tc3));
		
		assertEquals(tp.getTestCase(0), tc1);
		String[][] testCasesArray = tp.getTestCasesAsArray();
		
		//row 1
		assertEquals("A Test Case", testCasesArray[0][0]);
		assertEquals("requirements", testCasesArray[0][1]);
		assertEquals("FAIL", testCasesArray[0][2]);
		
		//row 2
		assertEquals("B Test Case", testCasesArray[1][0]);
		assertEquals("requirements2", testCasesArray[1][1]);
		assertEquals("FAIL", testCasesArray[1][2]);
		
		//row 2
		assertEquals("C Test Case", testCasesArray[2][0]);
		assertEquals("requirements3", testCasesArray[2][1]);
		assertEquals("FAIL", testCasesArray[2][2]);
		
	}

}
