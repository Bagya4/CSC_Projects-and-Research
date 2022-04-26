package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * test class for SelectionSorter
 * @author bmahara
 *
 */
public class QuickSorterTest {

	/** variable for an array of data in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** variable for an array of data in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** variable for an array of data in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** variable for an array of data with same elements */
	private Integer[] dataEqual = { 0, 0, 0 };

	/** variable selection sorter */
	private QuickSorter<Integer> quickSorter;
	/** variable selection sorter */
	private QuickSorter<Student> quickSorterGPA;
	/** variable selection sorter with pivot as first element */
	private QuickSorter<Integer> quickSorterFirst;
	/** variable selection sorter with pivot as last element */
	private QuickSorter<Integer> quickSorterLast;
	/** variable selection sorter with pivot as middle element */
	private QuickSorter<Integer> quickSorterMid;
	/** variable for comparator */
	private StudentGPAComparator compGpa;
	
	/** variable for student 1 */
	private Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
	/** variable for student 2 */
	private Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
	/** variable for student 3 */
	private Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
	/** variable for student 4 */
	private Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
	/** variable for student 5 */
	private Student sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	
	/** array of students */
	private Student[] students = {sFive, sOne, sTwo, sThree, sFour };
	
	/** Threshold delta for comparing floating points */
	private static final double DELTA = 0.000001;
	


	/**
	 * setup to define variables
	 */
	@Before
	public void setUp() {
		quickSorter = new QuickSorter<>();
		quickSorterFirst = new QuickSorter<>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		quickSorterLast = new QuickSorter<>(QuickSorter.LAST_ELEMENT_SELECTOR);
		quickSorterMid = new QuickSorter<>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		compGpa = new StudentGPAComparator();
		
		quickSorterGPA = new QuickSorter<>(compGpa);
	}

	/**
	 * test sorting different arrays
	 */
	@Test
	public void testSortIntegers() {
		quickSorter.sort(dataAscending);
		assertTrue(1 == dataAscending[0]);
		assertTrue(2 == dataAscending[1]);
		assertTrue(3 == dataAscending[2]);
		assertTrue(4 == dataAscending[3]);
		assertTrue(5 == dataAscending[4]);

		quickSorter.sort(dataDescending);
		assertTrue(1 == dataDescending[0]);
		assertTrue(2 == dataDescending[1]);
		assertTrue(3 == dataDescending[2]);
		assertTrue(4 == dataDescending[3]);
		assertTrue(5 == dataDescending[4]);

		quickSorter.sort(dataRandom);
		assertTrue(1 == dataRandom[0]);
		assertTrue(2 == dataRandom[1]);
		assertTrue(3 == dataRandom[2]);
		assertTrue(4 == dataRandom[3]);
		assertTrue(5 == dataRandom[4]);
	}
	
	/**
	 * test sorting different arrays with pivot as first element
	 */ 
	@Test
	public void testSortIntegersFirstPivot() {
		quickSorterFirst.sort(dataAscending);
		assertTrue(1 == dataAscending[0]);
		assertTrue(2 == dataAscending[1]);
		assertTrue(3 == dataAscending[2]);
		assertTrue(4 == dataAscending[3]);
		assertTrue(5 == dataAscending[4]);

		quickSorterFirst.sort(dataDescending);
		assertTrue(1 == dataDescending[0]);
		assertTrue(2 == dataDescending[1]);
		assertTrue(3 == dataDescending[2]);
		assertTrue(4 == dataDescending[3]);
		assertTrue(5 == dataDescending[4]);

		quickSorterFirst.sort(dataRandom);
		assertTrue(1 == dataRandom[0]);
		assertTrue(2 == dataRandom[1]);
		assertTrue(3 == dataRandom[2]);
		assertTrue(4 == dataRandom[3]);
		assertTrue(5 == dataRandom[4]);
	}
	
	/**
	 * test sorting different arrays with pivot as last element
	 */ 
	@Test
	public void testSortIntegersLastPivot() {
		quickSorterLast.sort(dataAscending);
		assertTrue(1 == dataAscending[0]);
		assertTrue(2 == dataAscending[1]);
		assertTrue(3 == dataAscending[2]);
		assertTrue(4 == dataAscending[3]);
		assertTrue(5 == dataAscending[4]);

		quickSorterLast.sort(dataDescending);
		assertTrue(1 == dataDescending[0]);
		assertTrue(2 == dataDescending[1]);
		assertTrue(3 == dataDescending[2]);
		assertTrue(4 == dataDescending[3]);
		assertTrue(5 == dataDescending[4]);

		quickSorterLast.sort(dataRandom);
		assertTrue(1 == dataRandom[0]);
		assertTrue(2 == dataRandom[1]);
		assertTrue(3 == dataRandom[2]);
		assertTrue(4 == dataRandom[3]);
		assertTrue(5 == dataRandom[4]);
	}
	
	/**
	 * test sorting different arrays with pivot as middle first element
	 */ 
	@Test
	public void testSortIntegersMidPivot() {
		quickSorterMid.sort(dataAscending);
		assertTrue(1 == dataAscending[0]);
		assertTrue(2 == dataAscending[1]);
		assertTrue(3 == dataAscending[2]);
		assertTrue(4 == dataAscending[3]);
		assertTrue(5 == dataAscending[4]);

		quickSorterMid.sort(dataDescending);
		assertTrue(1 == dataDescending[0]);
		assertTrue(2 == dataDescending[1]);
		assertTrue(3 == dataDescending[2]);
		assertTrue(4 == dataDescending[3]);
		assertTrue(5 == dataDescending[4]);

		quickSorterMid.sort(dataRandom);
		assertTrue(1 == dataRandom[0]);
		assertTrue(2 == dataRandom[1]);
		assertTrue(3 == dataRandom[2]);
		assertTrue(4 == dataRandom[3]);
		assertTrue(5 == dataRandom[4]);
	}

	/**
	 * student test with equal data
	 */
	@Test
	public void testSortEqual() {
		quickSorter.sort(dataEqual);
		assertTrue(0 == dataEqual[0]);
		assertTrue(0 == dataEqual[1]);
		assertTrue(0 == dataEqual[2]);
	}
	
	/**
	 * sort students by gpa
	 */
	@Test
	public void testSortStudentsGPA() {
		quickSorterGPA.sort(students);
		assertEquals(5.0, students[0].getGpa(), DELTA);
		assertEquals(4.0, students[1].getGpa(), DELTA);
		assertEquals(1.0, students[4].getGpa(), DELTA);
	}
}

