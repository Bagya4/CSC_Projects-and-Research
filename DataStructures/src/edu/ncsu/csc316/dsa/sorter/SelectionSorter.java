package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor to create instance of the class
	 */
	public SelectionSorter() {
		this(null);
	}
    
	/**
	 * Constructor to create instance of the class
	 * @param comparator specified by user
	 */
    public SelectionSorter(Comparator<E> comparator) {
    	super(comparator);
    } 
    
    /**
	 * Implements the selection sort algorithm for sorting an array of any data type
	 * @param data is an unsorted array of data
	 */
    public void sort(E[] data) {
        int size = data.length;
        for(int i = 0; i < size; i++) {
        	int min = i;
        	for(int j = i + 1; j < size; j++) {
        		if(compare(data[j], data[min]) < 0) {
        			min = j;
        		}
        	}
        	if(!(i == min)) {
        		E x = data[i];
        		data[i] = data[min];
        		data[min] = x;
        	}
        }
    }
}
