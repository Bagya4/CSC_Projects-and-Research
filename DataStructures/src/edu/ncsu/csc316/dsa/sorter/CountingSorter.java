package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Bagya Maharajan
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
	
	/**
	 * default constructor
	 */
	public CountingSorter() {
		//this(null);
	}
    
	/**
	 * inherited method from Sorter to sort data
	 * @param data is an array of data
	 */
	public void sort(E[] data) {
		
		E min = data[0];
		E max = data[0];
		int size = data.length;
		//calculate minimum and max
		for(int i = 0; i < size; i++) {
			
			if(data[i].getId() < min.getId()) {
				min = data[i];
			}
			
			if(data[i].getId() > max.getId()) {
				max = data[i];
			}
		}
		//range
		int range = max.getId() - min.getId() + 1;
		
		int arrB[] = new int[range];
		for(int i = 0; i < size; i++) {
			int idx = data[i].getId() - min.getId();
			arrB[idx] = arrB[idx] + 1;
		}
		
		for(int i = 1; i < range; i++) {
			arrB[i] = arrB[i - 1] + arrB[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] arrF = (E[])(new Identifiable[size]);
		
		for(int i = size - 1; i >= 0; i--) {
			int commonIdx = data[i].getId() - min.getId();
			arrF[ arrB[commonIdx] - 1] = data[i];
			arrB[commonIdx] = arrB[commonIdx] - 1;
		}
		
		//copying elements from F to data
		for(int i = 0; i < size; i++) {
			data[i] = arrF[i];
		}

        // Since we constrained E to be Identifiable,
        // we can now access the .getId() method of E objects
        // from within this sort method
    }
}
