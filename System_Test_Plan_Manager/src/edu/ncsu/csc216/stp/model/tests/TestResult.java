/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

/**
 * Class to represents the result of a test case execution. The TestResult class
 * contains information about the results of a single execution of a TestCase.
 * The TestResult knows if it is passing or failing and has details about the
 * actual results from execution.
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 */
public class TestResult {

	/** constant for passing test */
	public static final String PASS = "PASS";
	/** constant for failing test */
	public static final String FAIL = "FAIL";
	/** boolean field for passing or failing test */
	private boolean passing;
	/** string field for actual results of test test */
	private String actualResults;

	/**
	 * constructs a test result with given parameters.
	 * 
	 * @param passing       test or not
	 * @param actualResults of test
	 * @throws IllegalArgumentException if null or empty string
	 */
	public TestResult(boolean passing, String actualResults) {
		setActualResults(actualResults);
		setPassing(passing);
	}

	/**
	 * Getter for the actual results of the test
	 * 
	 * @return String representation of the actual results of the test
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Private helper method to check for a valid actualResults string. Throws an
	 * IAE if null or empty string. The exception message is “Invalid test
	 * results.”.
	 * 
	 * @param actualResults the actual results of the test to be set
	 * @throws IllegalArgumentException if null or empty string with the message
	 *                                  "Invalid test results."
	 */
	private void setActualResults(String actualResults) {
		if (actualResults == null || "".equals(actualResults)) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		this.actualResults = actualResults;
	}

	/**
	 * Method to check if test is passing or not
	 * 
	 * @return true if passing else false
	 */
	public boolean isPassing() {
		return passing;
	}

	/**
	 * Private method to set the value of field passing
	 * 
	 * @param passing true if passing test, false otherwise
	 */
	private void setPassing(boolean passing) {
		this.passing = passing;
	}

	/**
	 * Returns a string representation of the TestResult for printing to a file and
	 * listing in the GUI. If the test is passing, “PASS” is printed followed by a
	 * colon (:), a space, and the actual results. If the test is failing, “FAIL” is
	 * printed followed by a colon (:), a space, and the actual results.
	 * 
	 * @return If the test is passing, “PASS” is printed followed by a colon (:), a
	 *         space, and the actual results. If the test is failing, “FAIL” is
	 *         printed followed by a colon (:), a space, and the actual results.
	 */
	public String toString() {
		if (isPassing()) {
			return PASS + ": " + getActualResults();
		} else {
			return FAIL + ": " + getActualResults();
		}
	}

}
