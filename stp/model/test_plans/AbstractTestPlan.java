package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * The AbstractTestPlan class is an abstract class at the top of the hierarchy
 * for test plans. The AbstractTestPlan knows its testPlanName and the ISwapList
 * of TestCases.
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 *
 */
public abstract class AbstractTestPlan {
	/** The name of the test plan */
	private String testPlanName;
	/** SwapList list of test cases */
	SwapList<TestCase> testCases;
	/**
	 * Sets the fields from the parameters and constructs a SwapList for the
	 * TestCases. An IAE is thrown with the message “Invalid name. if the
	 * testPlanName is null or empty string.
	 * 
	 * @param testPlanName the name of the test plan
	 * 
	 */
	public AbstractTestPlan(String testPlanName) {
		testCases = new SwapList<TestCase>();
		setTestPlanName(testPlanName);
	}

	/**
	 * Sets the test plan name. An IAE is thrown with the message “Invalid name. if
	 * the testPlanName is null or empty string. This method is public because it
	 * can be used to edit the test plan.
	 * 
	 * @throws IllegalArgumentException if the testPlanName is null or an empty
	 *                                  string.
	 * @param testPlanName the name of the test plan
	 */
	public void setTestPlanName(String testPlanName) {
		if(testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.testPlanName = testPlanName;
	}

	/**
	 * Gets the test plan name
	 * 
	 * @return the test plan name
	 */
	public String getTestPlanName() {
		return testPlanName;
	}

	/**
	 * Gets the test cases as an ISwapList
	 * 
	 * @return the test cases
	 */
	public ISwapList<TestCase> getTestCases() {
		return testCases;
	}

	/**
	 * Adds the TestCase to the end of the list. Note that any exceptions from the
	 * list should be thrown out of the method (you don’t have to do anything for
	 * this except document it!)
	 * 
	 * @param t the test case to add
	 */
	public void addTestCase(TestCase t) {
		testCases.add(t);
	}

	/**
	 * Removes the TestCase from the list of test cases and returns the removed test
	 * case. Note that any exceptions from the list should be thrown out of the
	 * method (you don’t have to do anything for this except document it!)
	 * 
	 * @param idx the index to remove the test case
	 * @return the removed test case
	 */
	public TestCase removeTestCase(int idx) {
		if(idx < 0 || idx >= testCases.size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		TestCase t = testCases.get(idx);
		testCases.remove(idx);
		return t;
	}

	/**
	 * Gets the test case at the specified index
	 * 
	 * @param idx the index to get the test case at
	 * @return the test case at the specified index
	 */
	public TestCase getTestCase(int idx) {
		return testCases.get(idx);
	}

	/**
	 * Counts the number of TestCases that are failing. Remember that TestCases have
	 * a method to tell you if they are passing or failing.
	 * 
	 * @return the number of failing test cases
	 */
	public int getNumberOfFailingTests() {
		int failingTests = 0;
		for(int i = 0; i < testCases.size(); i++) {
			TestCase testCase = testCases.get(i);
			if(!testCase.isTestCasePassing()) {
				failingTests += 1;
			}
		}
		return failingTests;
	}

	/**
	 * Sends the test result parameters to the TestCase at the given index. Note
	 * that any exceptions from the list should be thrown out of the method (you
	 * don’t have to do anything for this except document it!)
	 * 
	 * @param idx           the index to add
	 * @param passing       whether it is passing or not
	 * @param actualResults the results of the test
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		testCases.get(idx).addTestResult(passing, actualResults);
	}

	/**
	 * Returns a 2D String array where the first column is the test case id, the
	 * second column is the test type, and the third column is the status (“PASS or
	 * “FAIL).
	 * 
	 * @return returns a 2D String array of the test cases
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * Generates the hashcode
	 * 
	 * @return returns the hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
		return result;
	}

	/**
	 * Compares two TestPlan objects. If the names are the same, it returns true.
	 * 
	 * @return true if the two test plans are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		if (testPlanName == null) {
			if (other.testPlanName != null)
				return false;
		} else if (!testPlanName.equalsIgnoreCase(other.testPlanName))
			return false;
		return true;
	}

}
