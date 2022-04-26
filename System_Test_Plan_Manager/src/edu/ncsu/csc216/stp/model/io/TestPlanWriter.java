package edu.ncsu.csc216.stp.model.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * TestPlanWriter has one public method writeTestPlanFile() that receives a File
 * with the file name to write to and an ISortedList of TestPlans to write to
 * file. TestPlanWriter should use TestCase’s toString() method to create the
 * properly formatted output for a TestCase. If there are any errors or
 * exceptions, an IllegalArgumentException is thrown with the message "Unable to
 * save file."
 * 
 * @author Mari Kilgus
 * @author Bagya Maharajan
 *
 */
public class TestPlanWriter {
	/**
	 * Receives a File with the file name to write to and an ISortedList of
	 * TestPlans to write to file. TestPlanWriter should use TestCase’s toString()
	 * method to create the properly formatted output for a TestCase. If there are
	 * any errors or exceptions, an IllegalArgumentException is thrown with the
	 * message “Unable to save file"
	 * 
	 * @param file the file to write to
	 * @param testPlans the ISortedList of test plans
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> testPlans) {
		
		try {
			PrintStream fileWriter = new PrintStream(file);
			
			for (int i = 0; i < testPlans.size(); i++) {
				String toBePrintedPlan = "! " + testPlans.get(i).getTestPlanName();
				fileWriter.println(toBePrintedPlan);
				TestPlan currentPlan = testPlans.get(i);
				for(int j = 0; j < testPlans.get(i).getTestCases().size(); j++) {
					String testCaseDetailsToPrint = currentPlan.getTestCases().get(j).toString();
					fileWriter.print(testCaseDetailsToPrint); 
				}
			}
		
			fileWriter.close();
		}
		catch(FileNotFoundException f) {
			throw new IllegalArgumentException();
		}		
	    
	}
}
