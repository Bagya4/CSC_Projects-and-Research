package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
//import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 *
 * @author Dr. King
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     *
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */
    public MergeSorter() {
        this(null);
    }

    /**
	 * inherited method from Sorter to sort data
	 * @param data is an array of data
	 */
	@Override
	public void sort(E[] data) {
		int size = data.length;
		if(size < 2) {
			return;
		}
		int mid = size / 2;
		
		E[] left = Arrays.copyOfRange(data, 0, mid);		
		E[] right = Arrays.copyOfRange(data, mid, size);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
		
	}

	/**
	 * Merges left and right sorted arrays into original array
	 * @param left sorted array
	 * @param right sorted array
	 * @param data original array
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int lIdx = 0;
		int lSize = left.length;
		int rIdx = 0;
		int rSize = right.length;
		int size = data.length;
		
		while(lIdx + rIdx < size) {
			if(rIdx == rSize || (lIdx < lSize && compare(left[lIdx], right[rIdx]) < 0)) {
				data[lIdx + rIdx] = left[lIdx];
				lIdx = lIdx + 1;
			}
			else {
				data[lIdx + rIdx] = right[rIdx];
				rIdx = rIdx + 1;
			}
		}
		
	}

}
