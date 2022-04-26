/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * The TestCase class contains the information about each individual test case
 * including the testCaseId, testType, testDescription, expectedResults, and
 * ILog of TestResults. The TestCase additionally contains a TestPlan that the
 * TestCase belongs to.
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 */
public class TestCase {

	/** field for test case ID */
	private String testCaseId;
	/** field for test case type */
	private String testType;
	/** field for test case description */
	private String testDescription;
	/** field for test case's expected results */
	private String expectedResults;
	/** field for test plan of type TestPlan that a test case belongs to */
	private TestPlan testPlan;

	/** log of test results */
	private Log<TestResult> testResults;
	/** constant for passing test */
	private static final String PASS = "PASS";
	/** constant for failing test */
	private static final String FAIL = "FAIL";

	/**
	 * TestCase(String testCaseId, String testType, String testDescription, String
	 * expectedResults). Constructs the TestCase with the given parameters. The
	 * testResults field is constructed to an empty Log of TestResults. The testPlan
	 * is set to null.
	 * 
	 * @param testCaseId      of test case
	 * @param testType        of test case
	 * @param testDescription of test case
	 * @param expectedResults of test case
	 * @throws IllegalArgumentException if null or empty string with message
	 *                                  "Invalid test information."
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		setTestCaseId(testCaseId);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
		testResults = new Log<TestResult>();
		testPlan = null;
	}

	/**
	 * Getter for test case Id.
	 * 
	 * @return the testCaseId
	 */
	public String getTestCaseId() {
		return testCaseId;
	}

	/**
	 * Setter for test case Id
	 * 
	 * @param testCaseId the testCaseId to set
	 * @throws IllegalArgumentException if null or empty string with message
	 *                                  "Invalid test information."
	 */
	private void setTestCaseId(String testCaseId) {
		if (testCaseId == null || "".equals(testCaseId)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testCaseId = testCaseId;
	}

	/**
	 * Getter for test case type.
	 * 
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * Setter for test case type.
	 * 
	 * @param testType the testType to set
	 * @throws IllegalArgumentException if null or empty string with message
	 *                                  "Invalid test information."
	 */
	private void setTestType(String testType) {
		if (testType == null || "".equals(testType)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testType = testType;
	}

	/**
	 * Getter for test case description.
	 * 
	 * @return the testDescription
	 */
	public String getTestDescription() {
		return testDescription;
	}

	/**
	 * setter for test case description
	 * 
	 * @param testDescription the testDescription to set
	 * @throws IllegalArgumentException if null or empty string with message
	 *                                  "Invalid test information."
	 */
	private void setTestDescription(String testDescription) {
		if (testDescription == null || "".equals(testDescription)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testDescription = testDescription;
	}

	/**
	 * Getter for test case expected results.
	 * 
	 * @return the expectedResults
	 */
	public String getExpectedResults() {
		return expectedResults;
	}

	/**
	 * Setter for test case expected results.
	 * 
	 * @param expectedResults the expectedResults to set
	 * @throws IllegalArgumentException if null or empty string with message
	 *                                  "Invalid test information."
	 */
	private void setExpectedResults(String expectedResults) {
		if (expectedResults == null || "".equals(expectedResults)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.expectedResults = expectedResults;
	}

	/**
	 * Creates a TestResult from the given values and adds the TestResult to the end
	 * of the testResults log. This method throws an IAE if the TestResult cannot be
	 * constructed.
	 * 
	 * @param passing       test case or not
	 * @param actualResults of test result
	 * @throws IllegalArgumentException if test result cannot be constructed
	 */
	public void addTestResult(boolean passing, String actualResults) {
		try {
			TestResult testResult = new TestResult(passing, actualResults);
			testResults.add(testResult);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid test results.");
		}
	}

	/**
	 * Returns true if the last TestResult in the Log is passing. If there are no
	 * TestResults in the Log the test is considered failing since it has not yet
	 * run and false is returned.
	 * 
	 * @return true if test case is passing, otherwise returns false
	 */
	public boolean isTestCasePassing() {
		if (testResults.size() == 0) {
			return false;
		} 
		else {
			return testResults.get(testResults.size() - 1).isPassing();
		}
	}

	/**
	 * Returns the status of the TestCase as "PASS" or "FAIL" using constants from
	 * TestResult.
	 * 
	 * @return "PASS" if test case is passing, otherwise "FAIL"
	 */
	public String getStatus() {
		if (isTestCasePassing()) {
			return PASS;
		}
		return FAIL;
	}

	/**
	 * Returns a string representation of the testResults Log. A leading â€œ- â€œ is
	 * added to the start of each TestResult and a â€œ\nâ€� is added to the end. Uses
	 * TestResult.toString() here.
	 * 
	 * @return Returns a string representation of the testResults Log. A leading â€œ-
	 *         â€œ is added to the start of each TestResult and a â€œ\nâ€� is added to the
	 *         end.
	 */
	public String getActualResultsLog() {
		String results = "";
		for(int i = 0; i < testResults.size(); i++) {
			results += "- " + testResults.get(i).toString() + "\n";
		}
		return results;
	}

	/**
	 * Sets the testPlan field to the given TestPlan
	 * 
	 * @param testPlan of type TestPlan
	 * @throws IllegalArgumentException if testPlan is null
	 */
	public void setTestPlan(TestPlan testPlan) {
		if(testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		this.testPlan = testPlan;
	}

	/**
	 * getter for test plan that a test case belongs to
	 * 
	 * @return test plan
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}

	/**
	 * Returns a string representation of the TestCase for printing to a file can
	 * use the method getActualResultsLog().
	 * 
	 * @return Returns a string representation of the testResults Log. A leading â€œ-
	 *         â€œ is added to the start of each TestResult and a â€œ\nâ€� is added to the
	 *         end.
	 */
	public String toString() {
		return "# " + getTestCaseId() + "," + getTestType() + "\n" + "* " + getTestDescription() 
				+ "\n" + "* " + getExpectedResults() + "\n" + getActualResultsLog();
	}

}
