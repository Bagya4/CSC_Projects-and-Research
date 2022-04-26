package edu.ncsu.csc216.stp.model.io;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests TestPlanReader class
 * 
 * @author Mari Kilgus
 *
 */
public class TestPlanReaderTest {
	/** valid test file */
	File validTestFile = new File("test-files/test-plans1.txt");
	/** invalid test file */
	File invalidTestFile = new File("not-a-real-path");
	/** empty test file */
	File emptyTestFile = new File("test-files/expected_empty_export.txt");

	/**
	 * Tests TestPlanReader.readProductsFile()
	 */
	@Test
	public void testReadValidProductsFile() {
		try {
			//expected test plan 1 for test-plans1.txt file
			TestPlan testPlan1 = new TestPlan("WolfScheduler");

			//expected test plan 2 for test-plans1.txt file
			TestPlan testPlan2 = new TestPlan("PackScheduler");

			//expected test cases for testPlan2
			TestCase tp2tc1 = new TestCase("T24 Add new course", "Requirements",
					"Preconditions: PackSchedulerGUI has loaded and registrar user is logged in (T2). T23 has run\n"
							+ "1. Enter the following course information:\n" + "Course Name: CSC 236\n"
							+ "Course Title: Computer Organization and Assembly Language\n" + "Course Section: 001\n"
							+ "Credits: 3\n" + "Instructor Id: lasher\n" + "Start Time: 10:15AM\n"
							+ "End Time: 11:30AM\n" + "Meeting Days: Tuesday & Thursday\n" + "Click Add Course\n"
							+ "Click OK",
					"Course is added to the list in the appropriate location\n" + "- PASS: course added (Lab 5)\n"
							+ "- FAIL: course not added b/c of invalid course name (Lab 6)");

			TestCase tp2tc2 = new TestCase("Invalid course name", "Invalid",
					"Preconditions: PackSchedulerGUI has loaded and registrar user is logged in (T2). T23 has run\n"
							+ "1. Enter the following course information:\n" + "Course Name: CSC236\n"
							+ "Course Title: Computer Organization and Assembly Language\n" + "Course Section: 001\n"
							+ "Credits: 3\n" + "Instructor Id: lasher\n" + "Start Time: 10:15AM\n"
							+ "End Time: 11:30AM\n" + "Meeting Days: Tuesday & Thursday\n" + "Click Add Course\n"
							+ "Click OK",
					"Error message: Invalid course name\n" + "- PASS: correct error message (Lab 5)");

			testPlan2.addTestCase(tp2tc1);
			testPlan2.addTestCase(tp2tc2);

			ISortedList<TestPlan> expectedTestPlans = new SortedList<TestPlan>();
			expectedTestPlans.add(testPlan1);
			expectedTestPlans.add(testPlan2);

			ISortedList<TestPlan> actualTestPlans = assertDoesNotThrow(
					() -> TestPlanReader.readTestPlansFile(validTestFile), "should not throw exception");

			assertEquals(2, actualTestPlans.size());

			for (int i = 0; i < expectedTestPlans.size(); i++) {
				TestPlan expectedTestPlan = expectedTestPlans.get(i);
				TestPlan actualTestPlan = actualTestPlans.get(i);

				assertEquals(expectedTestPlan, actualTestPlan);
			}

		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + validTestFile);
		}

		assertThrows(IllegalArgumentException.class, () -> TestPlanReader.readTestPlansFile(invalidTestFile),
				"Unable to read file.");
		assertThrows(IllegalArgumentException.class, () -> TestPlanReader.readTestPlansFile(emptyTestFile));

	}
	/**
	 * Recreate TS tests
	 */
	@Test
	public void testTsTests() {
		File testFile = new File("test-files/test-plans0.txt");
		ISortedList<TestPlan> actualTestPlans = TestPlanReader.readTestPlansFile(testFile);
		assertEquals(2, actualTestPlans.size());
		assertEquals(2, actualTestPlans.get(0).getTestCases().size());

	}
}
