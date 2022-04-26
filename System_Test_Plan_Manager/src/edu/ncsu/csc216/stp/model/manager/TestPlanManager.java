package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * A TestPlanManager has an ISortedList of TestPlans, one FailingTestList, an
 * AbstractTestPlan for the currentTestPlan, and a boolean flag that keeps track
 * of if the TestPlanManager has been changed since the last save.
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 */
public class TestPlanManager {
	/** keeps track of if the TestPlanManager has been changed since the last save.	 */
	private boolean isChanged;
	/** list of test plans */
	private ISortedList<TestPlan> testPlans;
	/** list of failing test plans */
	private FailingTestList failingTestList;
	/** Variable to hold current test plan */
	private AbstractTestPlan currentTestPlan;

	/**
	 * The testPlans field is constructed as a SortedList and the failingTestList is
	 * constructed and is set as the currentTestPlan. isChanged is initialized to
	 * false. The clearTestPlans() method can be helpful for completing these tasks.
	 */
	public TestPlanManager() {
		clearTestPlans();

	}

	/**
	 * The TestPlanReader is used to load the file and return a list of potential
	 * TestPlans to add to the list of TestPlans. If there is already a TestPlan in
	 * the testPlans with the given name, the incoming TestPlan is not added
	 * (remember SortedList already checks for duplicates!). After loading all the
	 * new TestPlans (or not), the currentTestPlan is set to the Failing Tests
	 * list. This should be done using setCurrentTestPlan().
	 * 
	 * @param testPlanFile the file parameter to load
	 */
	public void loadTestPlans(File testPlanFile) {
		ISortedList<TestPlan> listToBeAdded = TestPlanReader.readTestPlansFile(testPlanFile);
		int sizeOfList = listToBeAdded.size();
		for(int i = 0; i < sizeOfList; i++) {
			//checking for duplicates
			if(!testPlans.contains(listToBeAdded.get(i)))
				testPlans.add(listToBeAdded.get(i));
		}
		setCurrentTestPlan("Failing Tests");
		isChanged = true;

	}

	/**
	 * Saves the current TestPlans to the given file. isChanged is updated to false.
	 * 
	 * @param testPlanFile the file parameter to save
	 */
	public void saveTestPlans(File testPlanFile) {
		
		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);
		
		isChanged = false;

	}

	/**
	 * keeps track of if the TestPlanManager has been changed since the last save.
	 * 
	 * @return true if TestPlanManager has been changed since the last save
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * If the new TestPlan’s name is FAILING_TESTS_LIST_NAME or a duplicate of an
	 * existing TestPlan (both case insensitive), then an IAE is thrown with message
	 * "Invalid name." Otherwise, the TestPlan is added to the list of test plans,
	 * the current test plan is updated to the new test plan (use
	 * setCurrentTestPlan()), and isChanged is updated to true. Where possible
	 * delegate to other methods for error checking.
	 * 
	 * @param testPlanName the name of the test plan to be added
	 * @throws IllegalArgumentException If the new TestPlan’s name is
	 *                                  FAILING_TESTS_LIST_NAME or a duplicate of an
	 *                                  existing TestPlan (both case insensitive)
	 */
	public void addTestPlan(String testPlanName) {
		
		if("Failing Tests".equalsIgnoreCase(testPlanName))
			throw new IllegalArgumentException("Invalid name.");
		for(int i = 0; i < testPlans.size(); i++) {
			String name = testPlans.get(i).getTestPlanName();
			if(testPlanName.equalsIgnoreCase(name))
				throw new IllegalArgumentException("Invalid name.");			
		}
				
		TestPlan p = new TestPlan(testPlanName);
		testPlans.add(p);	
		setCurrentTestPlan(testPlanName);
		
		isChanged = true;

	}

	/**
	 * Returns a list of test plan names. The Failing Tests list is always listed
	 * first.
	 * 
	 * @return a list of test plan names
	 */
	public String[] getTestPlanNames() {
		String[] testPlanNames = new String[testPlans.size() + 1];
		
		testPlanNames[0] = "Failing Tests";
		
		for(int i = 0; i < testPlans.size(); i++) {
			testPlanNames[i + 1] = testPlans.get(i).getTestPlanName();
		}
		return testPlanNames;
	}

	/**
	 * A private helper method that is useful for working with the FailingTestList.
	 * The order that TestCases are stored in the FailingTestList list is related to
	 * the order of the TestPlans and then the order of the failing TestCases in
	 * those TestPlans. Maintaining that order via the FailingTestList.add() method
	 * can be a bit tricky. Instead, building the FailingTestList each time there’s
	 * a change can be easier since you iterate through all the TestPlans and add
	 * each failing TestCase. Make sure you clear the FailingTestList first!
	 */
	private void getFailingTests() {
		failingTestList = new FailingTestList();
		for(int i = 0; i < testPlans.size(); i++) {
			TestPlan p = testPlans.get(i);
			int sizeTestCases = p.getTestCases().size();
			for(int j = 0; j < sizeTestCases; j++) {
				TestCase tc = p.getTestCase(j);
				if(!tc.isTestCasePassing())
					failingTestList.addTestCase(tc);
			}
		}
	}

	/**
	 * Sets the currentTestPlan to the AbstractTestPlan with the given name. If a
	 * TestPlan with that name is not found, then the currentTestPlan is set to the
	 * failingTestList (don't forget to update the failingTestList first!).
	 * 
	 * @param testPlanName the name of the current test plan to set
	 */
	public void setCurrentTestPlan(String testPlanName) {
		getFailingTests();
		int counter = 0;
		
		for(int i = 0; i < testPlans.size(); i++) {
			if(testPlans.get(i).getTestPlanName().equals(testPlanName)) {
				currentTestPlan = testPlans.get(i);
				counter++;
				break;
			}
		}
		
		if(counter == 0) {
			currentTestPlan = failingTestList;
		}
		
	}

	/**
	 * Gets the current test plan
	 * 
	 * @return an AbstractTestPlan object representing the current test plan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}

	/**
	 * An IAE is thrown if the currentTestPlan is an FailingTestList (message “The
	 * Failing Tests list may not be edited.), if the new name matches “Failing
	 * Tests (case insensitive) (message “Invalid name.), or is a duplicate of the
	 * name of another TestPlan (case insensitive and including if the name is the
	 * same as the list that will be renamed) (message “Invalid name.). Note that
	 * when editing, you are editing the currentTestPlan and you cannot edit the
	 * TestPlan in place in the ISortedList. You must maintain sorted order (i.e.,
	 * remove, edit, add). isChanged is updated to true.
	 * 
	 * @param testPlanName the name of the test plan to edit
	 * @throws IllegalArgumentException if currentTestPlan is in the FailingTestList
	 *                                  the new name matches “Failing Tests (case
	 *                                  insensitive) (message “Invalid name.), or
	 *                                  is a duplicate of the name of another
	 *                                  TestPlan (case insensitive and including if
	 *                                  the name is the same as the list that will
	 *                                  be renamed) (message “Invalid name.)
	 */
	public void editTestPlan(String testPlanName) {
		
		if(currentTestPlan.equals(failingTestList)) 
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		
		if("Failing Tests".equalsIgnoreCase(testPlanName) || testPlanName.equals(currentTestPlan.getTestPlanName()))
			throw new IllegalArgumentException("Invalid name.");
		for(int i = 0; i < testPlans.size(); i++) {
			String name = testPlans.get(i).getTestPlanName();
			if(testPlanName.equalsIgnoreCase(name))
				throw new IllegalArgumentException("Invalid name.");			
		}
		
		currentTestPlan.setTestPlanName(testPlanName);
		
		isChanged = true;
		
	}

	/**
	 * An IAE is thrown if the currentTestPlan is an FailingTestList with the
	 * message “The Failing Tests list may not be deleted. Otherwise, the
	 * currentTestPlan is removed and then set to the failingTestList. isChanged is
	 * updated to true.
	 * 
	 * @throws IllegalArgumentException if the currentTestPlan is an FailingTestList
	 *                                  with the message “The Failing Tests list may
	 *                                  not be deleted.
	 */
	public void removeTestPlan() {
		
		if(currentTestPlan.equals(failingTestList))
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		
		for(int i = 0; i < testPlans.size(); i++) {
			if(testPlans.get(i).getTestPlanName().equals(currentTestPlan.getTestPlanName())) {
				testPlans.remove(i);
				break;
			}
		}
		
		currentTestPlan = failingTestList;
		isChanged = true;
	}

	/**
	 * A TestCase can only be added directly to a TestPlan. If the currentTestPlan
	 * is not a TestPlan do nothing with the TestCase. If the currentTestPlan is a
	 * TestPlan, then add the test case and check if the TestCase is failing. If so,
	 * then you can update the failingTestList. This is a place where the helper
	 * method getFailingTests() can be helpful. isChanged is updated to true.
	 * 
	 * @param t the test case to add
	 */
	public void addTestCase(TestCase t) {
		
		if(!currentTestPlan.equals(failingTestList)) {
			currentTestPlan.addTestCase(t);
			if(!t.isTestCasePassing()) {
				getFailingTests();
			}
			
			isChanged = true;
		}

	}

	/**
	 * Adds the test result to the test case at the given index in the current test
	 * plan. If the tests are failing, then the Failing Test List should be updated.
	 * 
	 * @param idx the idx to add at
	 * @param passing whether it is passing
	 * @param actualResult the actual result of the test
	 */
	public void addTestResult(int idx, boolean passing, String actualResult) {
		
		currentTestPlan.getTestCase(idx).addTestResult(passing, actualResult);
		if(!currentTestPlan.getTestCase(idx).isTestCasePassing())
			getFailingTests();

	}

	/**
	 * Clears out the TestPlanManager by setting testPlans to an empty SortedList,
	 * failingTestList to an empty FailingTestList(), currentTestPlan to the
	 * failingTestList, and isChanged to false.
	 */
	public void clearTestPlans() {
		testPlans = new SortedList<TestPlan>();
		failingTestList = new FailingTestList();
		currentTestPlan = failingTestList;
		isChanged = false;

	}
}
