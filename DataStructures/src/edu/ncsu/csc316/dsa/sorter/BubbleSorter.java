package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Bagya Maharajan
 * @param <E> the generic type of data to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor to create instance of the class
	 */
	public BubbleSorter() {
		this(null);
	}
	
	/**
	 * Constructor to allow client to specify the comparator to be used
	 * @param comparator to be used(gpa, or id etc)
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
    }

	/**
	 * Implements the bubble sort algorithm for sorting an array of any data type
	 * The compare() method being called is from the abstract comparison sorter
	 * @param unsortArr is an unsorted array of data
	 */
	@Override
	public void sort(E[] unsortArr) {
		
		boolean loop = true;
		int size = unsortArr.length;
		
		while(loop) {
			loop = false;
			for(int i = 1; i < size; i++) {
				if(compare(unsortArr[i], unsortArr[i - 1]) < 0) {
					E x = unsortArr[i - 1];
					unsortArr[i - 1] = unsortArr[i];
					unsortArr[i] = x;
					loop = true;
				}
			}
		}
		//end of while loop
	}
	
	

}
