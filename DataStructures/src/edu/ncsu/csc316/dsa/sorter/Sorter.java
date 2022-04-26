package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Bagya Maharajan
 * @param <E> the generic type of data to sort
 */
public interface Sorter<E> {
	
	/**
	 * Method used by every sorting algorithm to sort an array of objects
	 * @param unsortArr is unsorted or sorted array
	 */
	void sort(E[] unsortArr);
}
