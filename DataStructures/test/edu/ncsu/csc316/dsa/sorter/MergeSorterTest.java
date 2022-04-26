package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * test class for MergeSorter
 * @author bmahara
 *
 */
public class MergeSorterTest {

	/** variable for an array of data in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** variable for an array of data in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** variable for an array of data in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	/** variable for an array of data with same elements */
	private Integer[] dataEqual = { 0, 0, 0 };

	/** variable selection sorter */
	private MergeSorter<Integer> mergeSorter;

	/**
	 * setup to define variables
	 */
	@Before
	public void setUp() {
		mergeSorter = new MergeSorter<>();
	}

	/**
	 * test sorting different arrays
	 */
	@Test
	public void testSortIntegers() {
		mergeSorter.sort(dataAscending);
		assertTrue(1 == dataAscending[0]);
		assertTrue(2 == dataAscending[1]);
		assertTrue(3 == dataAscending[2]);
		assertTrue(4 == dataAscending[3]);
		assertTrue(5 == dataAscending[4]);

		mergeSorter.sort(dataDescending);
		assertTrue(1 == dataDescending[0]);
		assertTrue(2 == dataDescending[1]);
		assertTrue(3 == dataDescending[2]);
		assertTrue(4 == dataDescending[3]);
		assertTrue(5 == dataDescending[4]);

		mergeSorter.sort(dataRandom);
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
	public void testSortStudent() {
		mergeSorter.sort(dataEqual);
		assertTrue(0 == dataEqual[0]);
		assertTrue(0 == dataEqual[1]);
		assertTrue(0 == dataEqual[2]);
	}
}
