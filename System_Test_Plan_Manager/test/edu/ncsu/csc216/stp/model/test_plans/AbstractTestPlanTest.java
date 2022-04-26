package edu.ncsu.csc216.stp.model.test_plans;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;


/**
 * class to test abstarct test plan
 * @author Bagya Maharajan
 * @author Mari Kilgus
 *
 */
public class AbstractTestPlanTest {

	/**
	 * Tests addTestCase()
	 */
	@Test
	public void testAddTestCase() {
		TestPlan tp = new TestPlan("Test Plan");
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");
		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		assertDoesNotThrow(() -> tp.addTestCase(tc3));

	}
	/**
	 * Tests addTestResult()
	 */
	@Test
	public void testAddTestResult() {
		TestPlan tp = new TestPlan("Test Plan");
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");
		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		assertDoesNotThrow(() -> tp.addTestCase(tc3));
		assertDoesNotThrow(() -> tp.addTestResult(0, true, "actual results!"));
		assertDoesNotThrow(() -> tp.addTestResult(1, false, "actual results!!"));
		
	}
	/**
	 * Tests getNumberOfFailingTests()
	 */
	@Test
	public void testGetNumberOfFailingTests() {
		TestPlan tp = new TestPlan("Test Plan");
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");
		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		assertDoesNotThrow(() -> tp.addTestCase(tc3));
		assertDoesNotThrow(() -> tp.addTestResult(0, true, "actual results!"));
		assertDoesNotThrow(() -> tp.addTestResult(1, false, "actual results!!"));
		assertDoesNotThrow(() -> tp.addTestResult(2, false, "actual results!!!"));
		assertEquals(2, tp.getNumberOfFailingTests());
		
	}
	
	/**
	 * Tests getTestCase()
	 */
	@Test
	public void testGetTestCase() {
		TestPlan tp = new TestPlan("Test Plan");
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");
		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		assertDoesNotThrow(() -> tp.addTestCase(tc3));
		assertDoesNotThrow(() -> tp.addTestResult(0, true, "actual results!"));
		assertDoesNotThrow(() -> tp.addTestResult(1, false, "actual results!!"));
		assertDoesNotThrow(() -> tp.addTestResult(2, false, "actual results!!!"));

		assertEquals(tc1, tp.getTestCase(0));
		assertEquals(tc2, tp.getTestCase(1));
		assertEquals(tc3, tp.getTestCase(2));

	}
	/**
	 * Tests removeTestCase()
	 */
	@Test
	public void testRemoveTestCase() {
		TestPlan tp = new TestPlan("Test Plan");
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");
		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		assertDoesNotThrow(() -> tp.addTestCase(tc3));
		assertDoesNotThrow(() -> tp.addTestResult(0, true, "actual results!"));
		assertDoesNotThrow(() -> tp.addTestResult(1, false, "actual results!!"));
		assertDoesNotThrow(() -> tp.addTestResult(2, false, "actual results!!!"));

		assertEquals(tc1, tp.getTestCase(0));
		assertEquals(tc2, tp.getTestCase(1));
		assertEquals(tc3, tp.getTestCase(2));
		
		tp.removeTestCase(0);
		assertEquals(tc2, tp.getTestCase(0));
		assertEquals(tc3, tp.getTestCase(1));
		
	}
	
	/**
	 * test to get plan name
	 */
	@Test
	public void testGetTestPlanName() {
		TestPlan tp = new TestPlan("Test Plan");
		assertEquals("Test Plan", tp.getTestPlanName());
	}
	/**
	 * test for getting test cases
	 */
	@Test
	public void testGetTestCases() {
		TestPlan tp = new TestPlan("Test Plan");
		TestCase tc1 = new TestCase("A Test Case", "requirements", "the best test ever", "Pop up that says hello");
		TestCase tc2 = new TestCase("B Test Case", "requirements2", "the best test ever2", "Pop up that says hello2");
		TestCase tc3 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");
		assertDoesNotThrow(() -> tp.addTestCase(tc1));
		assertDoesNotThrow(() -> tp.addTestCase(tc2));
		assertDoesNotThrow(() -> tp.addTestCase(tc3));
		assertEquals(tc1, tp.getTestCases().get(0));
		assertEquals(tc2, tp.getTestCases().get(1));
		assertEquals(tc3, tp.getTestCases().get(2));

	}
	
	/**
	 * testing the equals method
	 */
	@Test
	public void testEquals() {
		TestPlan tp1 = new TestPlan("Test Plan");
		TestPlan tp2 = new TestPlan("Test Plan");
		TestPlan tp3 = new TestPlan("A Test Plan");
		TestCase tc1 = new TestCase("C Test Case", "requirements3", "the best test ever3", "Pop up that says hello3");

		assertEquals(tp1, tp2);
		assertNotEquals(tp1, tp3);
		assertNotEquals(tp1, tc1);
		assertEquals(tp1.hashCode(), tp2.hashCode());
	}
	
}
