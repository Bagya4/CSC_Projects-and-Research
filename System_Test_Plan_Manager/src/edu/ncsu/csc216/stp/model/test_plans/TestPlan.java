package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * 
 * The TestPlan class extends AbstractTestPlan and implements the Comparable
 * interface. It can get the associated test cases as a 2D array, add a test
 * case, and compare the names of test plans.
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	/**
	 * Constructs the TestPlan with the given name. If the proposed name is the same
	 * as FailingListTest.FAILING_TEST_LIST_NAME (case insensitive), then throw an
	 * IAE with message “Invalid name.
	 * 
	 * @param testPlanName the name of the test plan
	 * @throws IllegalArgumentException if If the proposed name is the same as
	 *                                  FailingListTest.FAILING_TEST_LIST_NAME (case
	 *                                  insensitive)
	 */
	public TestPlan(String testPlanName) {
		super(testPlanName);
		if("Failing Tests".equalsIgnoreCase(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}

	/**
	 * Returns a 2D String array where the first column is the test case id, the
	 * second column is the test type, and the third column is the status (passed or failed).
	 * 
	 * @return Returns a 2D String array where the first column is the test case id,
	 *         the second column is the test type, and the third column is the
	 *         status (passed or failed).
	 */
	public String[][] getTestCasesAsArray() {
		ISwapList<TestCase> testCases = getTestCases();
		String[][] testCasesArray = new String[testCases.size()][3];

		for (int i = 0; i < testCases.size(); ++i) {
			TestCase t = testCases.get(i);
			testCasesArray[i][0] = t.getTestCaseId();
			testCasesArray[i][1] = t.getTestType();
			testCasesArray[i][2] = t.getStatus();
		}
		return testCasesArray;
	}

	/**
	 * Override the AbstractTestPlan.addTestCase() method so that it 1) adds the
	 * test case via call to super AND sets the TestCase’s TestPlan to the current
	 * TestPlan (use the keyword this).
	 */
	@Override
	public void addTestCase(TestCase t) {
		super.addTestCase(t);
		t.setTestPlan(this);
	}

	/**
	 * Compares the names of the TestPlans. This comparison is case insensitive.
	 */
	@Override
	public int compareTo(TestPlan t) {
		String other = t.getTestPlanName().toLowerCase();
		String current = getTestPlanName().toLowerCase();

		return current.compareTo(other);

	}
}
