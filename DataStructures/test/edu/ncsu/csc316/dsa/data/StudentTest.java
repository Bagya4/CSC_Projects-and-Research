package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * test class for Student
 * @author bmahara
 *
 */
public class StudentTest {

	/** variable for student 1 */
	private Student sOne;
	/** variable for student 1 duplicate */
	private Student sOneDuplicate;
	/** variable for student 2 */
	private Student sTwo;
	/** variable for student similar to 2 */
	private Student sAlmostTwo;
	//private Student sThree;
	//private Student sFour;
	/** variable for student 5 */
	private Student sFive;
	/** variable for student similar to 1 */
	private Student sAlmostOne;
	/** variable for student 1 toString() */
	private String stu1;

	/**
	 * Setup method to define variables
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneDuplicate = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sAlmostOne = new Student("OneFirst", "OneLast", 2, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sAlmostTwo = new Student("TwoFirs", "TwoLast", 2, 2, 2.0, "twoUnityID");
		//sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		//sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		stu1 = "Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]";
	}

	/**
	 * testing setFirst
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * testing Student last name
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * testing Student id setter
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * testing Student gpa setter
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * testing Student unityID
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * testing compareTo- default
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sTwo.compareTo(sAlmostTwo) > 0);
		assertTrue(sAlmostTwo.compareTo(sTwo) < 0);
		assertTrue(sOne.compareTo(sAlmostOne) < 0);
		assertTrue(sAlmostOne.compareTo(sOne) > 0);
		assertSame(sOne.compareTo(sOne), 0);
		assertSame(sTwo.compareTo(sTwo), 0);
	}
	
	/**
	 * testing equals
	 */
	@Test
	public void studentEqualsandHashC() {
		assertTrue(sOne.equals(sOne));
		assertTrue(sOne.equals(sOneDuplicate));
		assertFalse(sOne.equals(sFive));
		assertFalse(sOne.equals(sAlmostOne));
		Object b = null;
		assertFalse(sOne.equals(b));
		
		//testing hash code
		assertEquals(sOne.hashCode(), sOneDuplicate.hashCode());
	}
	
	/**
	 * testing Student toSTring
	 */
	@Test
	public void studentToString() {
		assertEquals(stu1, sOne.toString());
		
	}
}
