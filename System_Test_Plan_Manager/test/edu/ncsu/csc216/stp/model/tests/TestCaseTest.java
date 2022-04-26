package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

/**
 * class to test TestCase
 * @author mbagy
 *
 */
public class TestCaseTest {
	
	/**
	 * testing constructor of TestCase
	 */
	@Test
	public void testTestCase() {
		TestCase testCase1 = assertDoesNotThrow(() -> new TestCase("Invalid File", "Equivalence Class",
				"1. Run WolfScheduler GUI\n"
						+ "2. In the Load Course Catalog File Chooser dialog select: testfiles/invalid_course_records.txt\n"
						+ "3. Click Select\n" + "4. Check Results\n" + "5. Close GUI",
				"course added (Lab 5)"));

		assertNull(testCase1.getTestPlan());
		assertEquals("Invalid File", testCase1.getTestCaseId());
		assertEquals("Equivalence Class", testCase1.getTestType());
		assertEquals("1. Run WolfScheduler GUI\n"
				+ "2. In the Load Course Catalog File Chooser dialog select: testfiles/invalid_course_records.txt\n"
				+ "3. Click Select\n" + "4. Check Results\n" + "5. Close GUI", testCase1.getTestDescription());
		assertEquals("course added (Lab 5)", testCase1.getExpectedResults());

	}
	
	/**
	 * test setter for test plan
	 */
	@Test
	public void testSetTestPlan() {
		TestCase testCase1 = assertDoesNotThrow(() -> new TestCase("Invalid File", "Equivalence Class",
				"1. Run WolfScheduler GUI\n"
						+ "2. In the Load Course Catalog File Chooser dialog select: testfiles/invalid_course_records.txt\n"
						+ "3. Click Select\n" + "4. Check Results\n" + "5. Close GUI",
				"course added (Lab 5)"));

		TestPlan tp = new TestPlan("A New Test Plan");
		assertNull(testCase1.getTestPlan());
		testCase1.setTestPlan(tp);
		assertThrows(IllegalArgumentException.class, () -> testCase1.setTestPlan(null));
	}
	/**
	 * test adding result
	 */
	@Test
	public void testAddTestResult() {
		TestCase testCase1 = assertDoesNotThrow(() -> new TestCase("Invalid File", "Equivalence Class",
				"1. Run WolfScheduler GUI\n"
						+ "2. In the Load Course Catalog File Chooser dialog select: testfiles/invalid_course_records.txt\n"
						+ "3. Click Select\n" + "4. Check Results\n" + "5. Close GUI",
				"course added (Lab 5)"));
		
		assertFalse(testCase1.isTestCasePassing());
		assertDoesNotThrow(() -> testCase1.addTestResult(true,  "course added (Lab 5)"));
		assertEquals("- PASS: course added (Lab 5)\n", testCase1.getActualResultsLog());
		//assertEquals("- PASS: course added (Lab 5)\n", testCase1.toString());

		assertTrue(testCase1.isTestCasePassing());

	}
	
	/**
	 * test getting status of a test case
	 */
	@Test
	public void testGetStatus() {
		TestCase testCase1 = assertDoesNotThrow(() -> new TestCase("Invalid File", "Equivalence Class",
				"1. Run WolfScheduler GUI\n"
						+ "2. In the Load Course Catalog File Chooser dialog select: testfiles/invalid_course_records.txt\n"
						+ "3. Click Select\n" + "4. Check Results\n" + "5. Close GUI",
				"course added (Lab 5)"));
		
		assertEquals("FAIL", testCase1.getStatus());
		assertDoesNotThrow(() -> testCase1.addTestResult(true,  "course added (Lab 5)"));
		assertEquals("PASS", testCase1.getStatus());

	}

}
