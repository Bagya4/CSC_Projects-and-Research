package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 *
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 *
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** Variable to keep track of user's choice of pivot */
	private PivotSelector selector;

	/**
	 * Pivot selection strategy that uses the element at the first index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the last index each time a
	 * pivot must be selected
	 */
	public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();

	/**
	 * Pivot selection strategy that uses the element at the middle index each time
	 * a pivot must be selected
	 */
	public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();

	/**
	 * Pivot selection strategy that uses the element at a randomly-chosen index
	 * each time a pivot must be selected
	 */
	public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and a
	 * specified PivotSelector strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 * @param selector   the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
		super(comparator);
		setSelector(selector);
	}

	/**
	 * Constructs a new QuickSorter using the natural ordering of elements. Pivots
	 * are selected using the provided PivotSelector strategy
	 * 
	 * @param selector the pivot selection strategy to use when selecting pivots
	 */
	public QuickSorter(PivotSelector selector) {
		this(null, selector);
	}

	/**
	 * Constructs a new QuickSorter with a provided custom Comparator and the
	 * default random pivot selection strategy
	 * 
	 * @param comparator a custom comparator to use when sorting
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}

	/**
	 * Constructs a new QuickSorter that uses an element's natural ordering and uses
	 * the random pivot selection strategy
	 */
	public QuickSorter() {
		this(null, null);
	}

	private void setSelector(PivotSelector selector) {
		if(selector == null) {
			this.selector = new RandomElementSelector();
		} else {
			this.selector = selector;
		}
	}

	/**
	 * inherited method from Sorter to sort data
	 * @param data is an array of data
	 */    
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}

	/**
	 * Private helper method
	 * @param data original array
	 * @param low index
	 * @param high index
	 */
	private void quickSort(E[] data, int low, int high) {
		if(low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	/**
	 * Implements the partition algorithm
	 * @param data original array
	 * @param low index being sorted
	 * @param high index being sorted
	 * @return index of pivot element after moving all values < pivot to its left
	 */
	private int partition(E[] data, int low, int high) {
		//selecting pivot
		int pivot = selector.selectPivot(low, high);
		//swapping pivot with last element
		E temp = data[pivot];
		data[pivot] = data[high];
		data[high] = temp;
		
		return partitionHelper(data, low, high);
	}
	
	/**
	 * Implements partition helper algorithm
	 * @param data array
	 * @param low index being sorted
	 * @param high index being sorted
	 * @return index of pivot element after all elements to left of pivot are less than itself
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int index = low;
		for(int j = low; j < high; j++) {
			if(compare(data[j], pivot) <= 0) {
				//swapping elements
				E temp = data[index];
				data[index] = data[j];
				data[j] = temp;
				
				index = index + 1;
			}
		}
		
		//swap idx with pivot(data[high])
		E temp = data[index];
		data[index] = data[high];
		data[high] = temp;
		
		return index;
	}

	/**
	 * Defines the behaviors of a PivotSelector
	 * 
	 * @author Dr. King
	 *
	 */
	private interface PivotSelector {
		/**
		 * Returns the index of the selected pivot element
		 * 
		 * @param low  - the lowest index to consider
		 * @param high - the highest index to consider
		 * @return the index of the selected pivot element
		 */
		int selectPivot(int low, int high);
	}

	/**
	 * FirstElementSelector chooses the first index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Dr. King
	 *
	 */
	public static class FirstElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return low;
		}
	}

	/**
	 * HighElementSelector chooses the last index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Dr. King
	 * @author Bagya Maharajan
	 *
	 */
	public static class LastElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return high;
		}
	}

	/**
	 * MidElementSelector chooses the middle index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Dr. King
	 * @author Bagya Maharajan
	 *
	 */
	public static class MiddleElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return (low + high) / 2;
		}
	}

	/**
	 * RandomElementSelector chooses a random index of the array as the index of the
	 * pivot element that should be used when sorting
	 * 
	 * @author Dr. King
	 * @author Bagya Maharajan
	 *
	 */
	public static class RandomElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			Random r = new Random();
			return r.nextInt(high - low) + low;
		}
	}



}
