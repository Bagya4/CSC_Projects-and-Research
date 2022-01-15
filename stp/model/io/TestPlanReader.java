package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * TestPlanReader has one public method readTestPlansFile() that receives a File
 * with the file to read from. If the file cannot be loaded because it doesn’t
 * exist, the method will throw an IllegalArgumentException with the message
 * “Unable to load file. Any invalid test plans or test cases (i.e., they cannot
 * be constructed or information is missing) are ignored.
 * 
 * The input file contains at least one test plan. Each test plan can have zero
 * to many test cases. Each test case can have zero to many results.
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 *
 */
public class TestPlanReader {
	/**
	 * Receives a File with the file to read from. If the file cannot be loaded
	 * because it doesn't exist, the method will throw an IllegalArgumentException
	 * with the message “Unable to load file. Any invalid test plans or test cases
	 * (i.e., they cannot be constructed or information is missing) are ignored.
	 * 
	 * @param fileName The file with the test plans to be read in
	 * @return An ISortedList of TestPlans
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File fileName) {
		// Read file into a single string
		Scanner fileReader = null;
		String string = "";
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
			while (fileReader.hasNextLine()) {
				string += fileReader.nextLine() + '\n';
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		if ("".equals(string)) {
			throw new IllegalArgumentException();
		}
		// If the first character in the file is not ! throw an IllegalArgumentException
		// with message “Unable to load file."
		if (string.charAt(0) != '!') {
			throw new IllegalArgumentException("Unable to load file.");
		}
		fileReader.close();

		// Processes each TestPlan and collects into a list
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		Scanner newFileReader = new Scanner(string);

		newFileReader.useDelimiter("\\r?\\n?[!]");
		while (newFileReader.hasNext()) {
			try {
				TestPlan testPlan = processTestPlan(newFileReader.next());
//				if (testPlan.getTestCases().size() > 0) {
				testPlans.add(testPlan);
//				}
//				else {
//					System.out.println("testPlan skipped because 0 cases.");
//				}
			} catch (NoSuchElementException | IllegalArgumentException e) {
//				// Can stay empty.
			}
		}
		newFileReader.close();
		return testPlans;
	}

	/**
	 * To break the line into test cases, each of which we will call a test case
	 * token, use \\r?\\n?[#] as a delimiter. This will break apart the contents by
	 * end of line characters \n and \r and a following # character that represents
	 * a test case. The first line of the test case token is the test case id and
	 * the test case type.
	 * 
	 * 
	 * @param nextLine is the next line read in by the scanner
	 * @return the TestPlan object
	 */
	private static TestPlan processTestPlan(String nextLine) {
		Scanner lineReader = new Scanner(nextLine);
		String testPlanName = lineReader.nextLine().trim();
		TestPlan testPlan = new TestPlan(testPlanName);
//		Scanner TestCaseLineReader = new Scanner(nextLine);
		lineReader.useDelimiter("\\r?\\n?[#]");
		while (lineReader.hasNext()) {
			try {
				TestCase testCase = processTest(testPlan, lineReader.next());
				testPlan.addTestCase(testCase);
			} catch (NoSuchElementException | IllegalArgumentException e) {
//				e.printStackTrace();
			}
		}
		lineReader.close();
		return testPlan;
	}

	/**
	 * To break the line into test cases, each of which we will call a test case
	 * token, use \\r?\\n?[#] as a delimiter. This will break apart the contents by
	 * end of line characters \n and \r and a following # character that represents
	 * a test case. The first line of the test case token is the test case id and
	 * the test case type. After removing the test case id and the test case type
	 * from the test case token, you can then use the \\r?\\n?[-] delimiter to break
	 * apart the string into the description and expected results and the actual
	 * results tokens. The first token contains the description and expected results
	 * that can be broken apart using the delimiter \\r?\\n?[*].
	 * 
	 * @param t           the abstract test plan
	 * @param testDetails the next line read in by the scanner
	 * @return the TestCase object
	 */
	private static TestCase processTest(AbstractTestPlan t, String testDetails) {
		// Splitting up tasks and reading cases
		Scanner casesReader = new Scanner(testDetails);
		casesReader.useDelimiter("\\r?\\n?[-]");
		String idAndType = casesReader.nextLine();
		Scanner testIdAndType = new Scanner(idAndType);
		testIdAndType.useDelimiter(",");

		String testCaseId = testIdAndType.next().trim();
		String type = testIdAndType.next().trim();

		Scanner caseInfoReader = new Scanner(casesReader.next());
		caseInfoReader.useDelimiter("\\r?\\n?[*]");

//		Getting case information
		String description = caseInfoReader.next().trim();
		String expectedResults = caseInfoReader.next().trim();

		// Creating a case
		TestCase testCase = new TestCase(testCaseId, type, description, expectedResults);

		while (casesReader.hasNext()) {
			boolean pass = false;
			String[] passing = casesReader.next().split(":");
			if (passing.length == 2) {
				if (passing[0].trim().equals("PASS")) {
					pass = true;
				}
				String actualResult = passing[1].trim();
				testCase.addTestResult(pass, actualResult);
			} else {
				testIdAndType.close();
				casesReader.close();
				caseInfoReader.close();
				throw new IllegalArgumentException();
			}
		}
		testIdAndType.close();
		casesReader.close();
		caseInfoReader.close();
		return testCase;
	}
}
