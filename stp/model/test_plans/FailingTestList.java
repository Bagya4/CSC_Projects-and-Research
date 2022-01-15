package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
//import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * 
 * A list of failing test cases. It has methods to add a test case, set the test
 * plan name, and get the test cases as an array. Extends AbstractTestPlan.
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 *
 */
public class FailingTestList extends AbstractTestPlan {
	/** Constant holding the name of the failing test case list. */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";

	/**
	 * Constructs the FailingTestList with the expected name.
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
	}

	/**
	 * Overrides the method to check that the TestCase is failing before adding to
	 * the end of the ISwapList. If the TestCase is not failing, the test is not
	 * added to the list and an IAE is thrown with the message "Cannot add passing
	 * test case."
	 * 
	 * @param t the test case to add
	 */
	@Override
	public void addTestCase(TestCase t) {
		
		if(!t.isTestCasePassing()) {
			super.addTestCase(t);
		}
		if(t.isTestCasePassing())
			throw new IllegalArgumentException("Cannot add passing test case.");

	}

	/**
	 * Overrides the method to ensure that the parameter value matches the expected
	 * name (case insensitive). If so, the name is set to the constant value. If
	 * not, an IAE is thrown with the message "The Failing Tests list cannot be
	 * edited."
	 * 
	 * @param testPlanName the name of the test plan to set
	 */
	@Override
	public void setTestPlanName(String testPlanName) {
		
		if(testPlanName.equalsIgnoreCase(FAILING_TEST_LIST_NAME)) {
			super.setTestPlanName(FAILING_TEST_LIST_NAME);
		}
		else {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}

	}

	/**
	 * Returns a 2D String array where the first column is the test case id, the
	 * second column is the test type, and the third column is the test plan name
	 * associated with the TestCase. If the test plan is null, then use empty string
	 * for the test plan name.
	 * 
	 * @return Returns a 2D String array where the first column is the test case id,
	 *         the second column is the test type, and the third column is the test
	 *         plan name associated with the TestCase.
	 */
	public String[][] getTestCasesAsArray() {
		ISwapList<TestCase> testCases = getTestCases();
		String[][] testCasesArray = new String[testCases.size()][3];

		for (int i = 0; i < testCases.size(); ++i) {
			TestCase t = testCases.get(i);
			testCasesArray[i][0] = t.getTestCaseId();
			testCasesArray[i][1] = t.getTestType();
			if(t.getTestPlan() == null) {
				testCasesArray[i][2] = "";
			}
			else
				testCasesArray[i][2] = t.getTestPlan().getTestPlanName();
		}
		return testCasesArray;
		
	}

	/**
	 * Clears the FailingTestList of all TestCases.
	 */
	public void clearTests() {
		boolean remove = true;
		int size = super.getTestCases().size();
		int counter = 0;
		while(remove) {
			counter++;
			super.removeTestCase(0);
			if(counter == size)
				remove = false;
		}
		
		//this.testCases = new SwapList<TestCase>();
	}
}
