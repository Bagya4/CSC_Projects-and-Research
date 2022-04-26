package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import org.junit.jupiter.api.Test;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * test for TestPlanWriter class
 * @author Bagya Maharajan
 * @author Mari Kilgus
 *
 */

public class TestPlanWriterTest {

	/**
	 * Tests writeTestPlanFile()
	 */
	@Test
	public void testWriteTestPlanFile() {
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		TestPlan tp = new TestPlan("BacklogManager");
		TestCase tc1 = new TestCase("A Test Case 1", "requirements", "the best test ever", "Pop up that says hello");

		tp.addTestCase(tc1);
		testPlans.add(tp);
		File f = new File("test-files/test_TestPlanWriter.txt");

		try {
			TestPlanWriter.writeTestPlanFile(f, testPlans);
		}
		catch(IllegalArgumentException i) {
			fail("Cannot write to test plan file");
		}
	}
}
