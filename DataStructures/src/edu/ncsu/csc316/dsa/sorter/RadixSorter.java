package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author bmahara
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
	
	/** constant for storing max length of counter array */
	public static final int MAX_ARRB = 10;
	
	/**
	 * default constructor
	 */
	public RadixSorter() {
		//this(null);
	}
	
	/**
	 * inherited method from Sorter to sort data
	 * @param data is an array of data
	 */
	public void sort(E[] data) {
		
		int max = 0;
		int size = data.length;
		
		//finding max
		for(int i = 0; i < size; i++) {
			
			if(data[i].getId() > max) {
				max = data[i].getId();
			}			
		} //end of for loop
		
		double f = Math.log(max + 1) / Math.log(10);
		int maxDigit = (int) Math.ceil(f);
		
		int p = 1;
		for(int j = 1; j <= maxDigit; j++) {
			int[] arrB = new int[MAX_ARRB];
			
			for(int i = 0; i < size; i++) {
				int idx = (data[i].getId() / p) % MAX_ARRB;
				arrB [idx] = arrB[idx] + 1;
			} //end of i for loop
			
			for(int i = 1; i < 10; i++) {
				arrB[i] = arrB[i - 1] + arrB[i];
			}
			
			@SuppressWarnings("unchecked")
			E[] arrF = (E[])(new Identifiable[size]);
			
			for(int i = size - 1; i >= 0; i--) {
				int commonIdx = (data[i].getId() / p) % MAX_ARRB;
				arrF[ arrB[commonIdx] - 1] = data[i];
				arrB[commonIdx] = arrB[commonIdx] - 1;
			}
			
			for(int i = 0; i < size; i++) {
				data[i] = arrF[i];
			}
			p = p * MAX_ARRB;
		} //end of j for loop
		
	}
	
}
