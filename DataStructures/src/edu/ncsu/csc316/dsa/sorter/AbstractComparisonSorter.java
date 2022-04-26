package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
/**
 * Parent class to implement common functionalities in sorters that use comparsion
 * @author bmahara
 *
 * @param <E> is type of object
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	
	/** stores the comparator type to be used*/
    private Comparator<E> comparator;
    
    /**
     * Constructor to set the comparator
     * @param comparator default one or specified by user 
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Setter for the comparator
     * @param comparator specified
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * Compares two elements with the criteria specified by a comparator
     * @param data1 an element
     * @param data2 another element
     * @return 0 if equal, or other numbers as specified by the comparator
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
    
    /**
     * Inner class which handles the default sorting
     * @author bmahara
     *
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    
}
