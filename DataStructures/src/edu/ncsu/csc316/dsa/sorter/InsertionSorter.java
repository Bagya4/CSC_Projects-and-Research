package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Bagya Maharajan
 * @param <E> the generic type of data to sort
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor to create instance of the class
	 */
	public InsertionSorter() {
		this(null);
	}
	
	/**
	 * Constructor to allow client to specify the comparator to be used
	 * @param comparator to be used(gpa, or id etc)
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
    }
	
	/**
	 * Implements the insertion sort algorithm for sorting an array of any data type
	 * The compare() method being called is from the abstract comparison sorter
	 * @param unsortArr is an unsorted array of data
	 */
	@Override
	public void sort(E[] unsortArr) {
		int size = unsortArr.length;
		for(int i = 1; i <= size - 1; i++) {
			E x = unsortArr[i];
			int j = i - 1;
			while(j >= 0 && compare(unsortArr[j], x) > 0 && x != null && unsortArr[j] != null) {
				unsortArr[j + 1] = unsortArr[j];
				j = j - 1;
			}
			unsortArr[j + 1] = x;
		}
		
	}

	
}
