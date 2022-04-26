package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

class FailingTestListTest {

	/**
	 * Test FailingTestList constructor
	 */
	@Test
	public void testFailingTestList() {
		FailingTestList t = new FailingTestList();
		assertEquals("Failing Tests", t.getTestPlanName());
		
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
				
		assertDoesNotThrow(() -> tc1.addTestResult(false, "not actual results!!"));
		assertDoesNotThrow(() -> tc2.addTestResult(true, "actual results!!"));
		assertDoesNotThrow(() -> t.addTestCase(tc1));
		
		try {
			t.addTestCase(tc2);
			fail();
		}
		catch (IllegalArgumentException i) {
			assertEquals("Cannot add passing test case.", i.getMessage());
		}
	}
	
	/**
	 * testing getTestCasesAsArray
	 */	
	@Test
	public void testTestCasesAsArray() {
		
		FailingTestList t = new FailingTestList();
		TestPlan tp = assertDoesNotThrow(() -> new TestPlan("Test Plan"));
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");

		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		
		assertDoesNotThrow(() -> tc1.addTestResult(false, "not actual results!"));
		assertDoesNotThrow(() -> tc2.addTestResult(false, "not actual results!!"));
		assertDoesNotThrow(() -> tc3.addTestResult(false, "not actual results!!!"));

		assertDoesNotThrow(() -> t.addTestCase(tc1));
		assertDoesNotThrow(() -> t.addTestCase(tc2));
		assertDoesNotThrow(() -> t.addTestCase(tc3));
		
		assertEquals(t.getTestCase(0), tc1);
		String[][] testCasesArray = t.getTestCasesAsArray();
		
		//row 1
		assertEquals("A Test Case", testCasesArray[0][0]);
		assertEquals("requirements", testCasesArray[0][1]);
		assertEquals("Test Plan", testCasesArray[0][2]);
		
		//row 2
		assertEquals("B Test Case", testCasesArray[1][0]);
		assertEquals("requirements2", testCasesArray[1][1]);
		assertEquals("Test Plan", testCasesArray[1][2]);
		
		//row 2
		assertEquals("C Test Case", testCasesArray[2][0]);
		assertEquals("requirements3", testCasesArray[2][1]);
		assertEquals("", testCasesArray[2][2]);
	}
	
	@Test
	public void testClearFailingTests() {
		FailingTestList t = new FailingTestList();
		assertEquals("Failing Tests", t.getTestPlanName());
		
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		
		assertDoesNotThrow(() -> tc1.addTestResult(false, "not actual results!!"));
		assertDoesNotThrow(() -> t.addTestCase(tc1));
		
		assertEquals(tc1, t.getTestCase(0));
		
		t.clearTests();
		
		try {
			//list should be cleared and size will be set to 0
			t.getTestCase(0);
			fail();
		}
		catch(IndexOutOfBoundsException e) {
			//do nothing
		}
		
	}

}
