package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * test class for CountingSorter
 * @author bmahara
 *
 */
public class CountingSorterTest {
	
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
	/** variable for student 16 */
	private Student sSixTeen;
	
	/** variable for counting sorter */
	private CountingSorter<Student> sorter;

	/**
	 * setup method to define variables
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sSixTeen = new Student("sixTFirst", "SixTLast", 16, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * tests sorting students
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * tests sorting students in ascending order
	 */
	@Test
	public void stuTestAscending() {
		Student[] original = { sOne, sTwo, sThree, sFour, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * tests sorting students in descending order
	 */
	@Test
	public void stuTestDescending() {
		Student[] original = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * testing with fewer elements and wider Id range
	 */
	@Test
	public void stuInt() {
		Student[] original = { sSixTeen, sThree, sTwo };
		sorter.sort(original);
		
		assertEquals(sTwo, original[0]);
		assertEquals(sThree, original[1]);
		assertEquals(sSixTeen, original[2]);
		
	}
	

}
