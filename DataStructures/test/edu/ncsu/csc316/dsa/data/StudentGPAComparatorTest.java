package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * test class for GPA comparator
 * @author bmahara
 *
 */
public class StudentGPAComparatorTest {
	
	/** variable for student 1 */
	private Student sOne;
	/** variable for student 2 */
	private Student sTwo;
	/** variable for student 3 */
	private Student sThree;
	/** variable for student 4 */
	private Student sFour;
	/** variable for student 5 */
	private Student sFive;
	
	/** variable for comparator */
	private StudentGPAComparator comparator;

	/**
	 * Setup method to define variables
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * testing compare method
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);

		//Adding student test cases
		assertTrue(comparator.compare(sFive, sThree) < 0);
		assertTrue(comparator.compare(sFour, sFour) == 0);
		
	}

}
