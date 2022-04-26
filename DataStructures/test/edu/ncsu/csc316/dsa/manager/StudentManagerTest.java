package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;

/**
 * test class for StudentManager
 * @author bmahara
 *
 */
public class StudentManagerTest {

	/** variable for student manager 1 */
	private StudentManager sm;
	/** variable for student manager 2 */
	private StudentManager sm2;
	/** variable for student manager 3 */
	private StudentManager sm3;
	/** variable for gpa comparator */
	private Comparator<Student> gpa = new StudentGPAComparator();
	/** variable for id comparator */
	private Comparator<Student> id = new StudentIDComparator();
	/** variable for insertion sorter  with gpa comparator */
	private InsertionSorter<Student> ins = new InsertionSorter<Student>(gpa);
	/** variable for insertion sorter  with id comparator */
	private InsertionSorter<Student> ins2 = new InsertionSorter<Student>(id);
	
	/**
	 * Setup method to define variables
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
		sm2 = new StudentManager("input/student_randomOrder.csv", ins);
		sm3 = new StudentManager("input/student_descendingID.csv", ins2);
	}
	
	/**
	 * testing sort method with different comparators
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/**
	 * testing gpa comparator
	 */
	public void studentTestGpaSorter() {
		Student[]  sorted = sm2.sort();
		assertTrue(0.4 == sorted[15].getGpa());
		assertEquals("Lewis", sorted[15].getFirst());
		
	}
	
	/**
	 * testing id comparator
	 */
	public void studentTestIdSorter() {
		Student[]  sorted = sm3.sort();
		assertTrue(1 == sorted[0].getId());
		assertEquals("Amber", sorted[0].getFirst());
		
	}
	
	// Suggestions:
	// -> Test that custom comparators sort the data correctly

}
