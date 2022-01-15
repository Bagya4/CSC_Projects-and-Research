/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * 
 * Interface for a list that keeps objects in sorted order as defined by the
 * Comparable interface.
 * 
 * @author bmahara
 * 
 * @param <E> type for ISortedList; must implement Comparable
 *
 */
public interface ISortedList<E> {

	/**
	 * Adds the element E and does not allow for duplicates to be added to list
	 * @param element is the element being added to the list
	 * @throws NullPointerException if element to be added is null
	 * @throws IllegalArgumentException if the element is a duplicate of another present in the list
	 */
	void add(E element);

	/**
	 * removes element at index idx
	 * @param idx is index of element to be removed
	 * @return E element
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	E remove(int idx);

	/**
	 * method to check if an element exists or not in the list
	 * @param element to check for
	 * @return true if element is present in list else false
	 */
	boolean contains(E element);

	/**
	 * getter for element at an index
	 * @param idx for index at which element is needed
	 * @return E element at index
	 * @throws IndexOutOfBoundsException if index is not in range
	 */
	E get(int idx);

	/**
	 * returns the size of the log list
	 * @return size of list as an integer value
	 */
	int size();

}
