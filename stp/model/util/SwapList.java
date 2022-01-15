/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * The SwapList class implements the ISwapList interface. The interface provides
 * Javadoc that describes what each method should do and the exceptions that
 * should be thrown. The SwapList allows duplicate elements.
 * 
 * @author bmahara
 * @param <E> type of element class to implement the ISwapList
 */
public class SwapList<E> implements ISwapList<E> {

	/** initializes the size */
	private static final int INITIAL_CAPACITY = 10;
	/** list of type E */
	private E[] list;
	/** size of the list */
	private int size;

	/**
	 * Constructor for swap list
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}

	/**
	 * Should throw a NullPointerException with message Cannot add null element
	 * if the parameter is null. Should throw an IllegalArgumentException if necessary with
	 * message Cannot add element
	 * 
	 * @param element is the element being added to the list
	 * @throws NullPointerException if element to be added is null
	 * @throws IllegalArgumentException with message "Cannot add element" 
	 */
	@Override
	public void add(E element) {
		if(element == null)
			throw new NullPointerException("Cannot add null element.");
		
		if(size != 0) {
			checkCapacity(size);
		}
		list[size] = element;
		size++;
		
	}

	/**
	 * method to check capapcity
	 * 
	 * @param size of list
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int size) {
		
		int sizeFull = 0;		
		for(int i = 0; i < size; i++) {
			if(get(i) == null)
				sizeFull++;
		}
		
		//if null element or space exists in array, array is not full and counter will be 0
		
		if(sizeFull == 0) {
			E[] tempList = (E[]) new Object[size];
			//copying list contents to a temp list
			for(int j = 0; j < size; j++) {
				tempList[j] = list[j];
			}
			//doubling size of list
			list = (E[]) new Object[size * 2];
			
			//copying back original contents of list from tempList
			for(int i = 0; i < size(); i++) {
				list[i] = tempList[i];
			}
		}
		
	}

	/**
	 * removes element at index idx
	 * 
	 * @param idx is index of element to be removed
	 * @return E element
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	@Override
	public E remove(int idx) {
		
		checkIndex(idx);
		
		E rtn = list[idx];
		for(int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		//to prevent memory leak and make sure there are no spare references remaining
		list[size - 1] = null;
		size--;
		return rtn;
	}

	/**
	 * method to check if index is in bounds
	 * 
	 * @param idx to be checked
	 */
	private void checkIndex(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * method to move element at an index up by 1
	 * 
	 * @param idx of element
	 */
	@Override
	public void moveUp(int idx) {
		
		if(idx == 0 && size() == 0)
			throw new IndexOutOfBoundsException("Invalid index.");
		
		if(size() == 1 && idx == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		checkIndex(idx);
		if(idx != 0) {
			E temp = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = temp;
			
		}
	}

	/**
	 * method to move element at an index down by 1
	 * 
	 * @param idx of element
	 */
	@Override
	public void moveDown(int idx) {
		
		if(size() == 1 && idx == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		checkIndex(idx);
		if(idx != size - 1) {
			E temp = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = temp;
			
		}
	}

	/**
	 * method to move element at an index to front of list
	 * 
	 * @param idx of element
	 */
	@Override
	public void moveToFront(int idx) {
		
		if(size() == 1 && idx == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		checkIndex(idx);
		
		E elem = get(idx);
		
		if(idx != 0) {
			for(int i = idx - 1; i >= 0; i--) {
				list[i + 1] = list[i];
			}
			list[0] = elem;
		}
	}

	/**
	 * method to move element at an index to back of list
	 * 
	 * @param idx of element
	 */
	@Override
	public void moveToBack(int idx) {
		
		if(size() == 1 && idx == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		if(size() == 0)
			throw new IndexOutOfBoundsException("Invalid index.");
		
		checkIndex(idx);
		E elem = get(idx);
	
		if(idx != size - 1) {
			for(int i = idx; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = elem;
		}
	}

	/**
	 * getter for element at an index
	 * 
	 * @param idx for index at which element is needed
	 * @return E element at index
	 * @throws IndexOutOfBoundsException if index is not in range
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}

	/**
	 * returns the size of the log list
	 * 
	 * @return size of list as an integer value
	 */
	@Override
	public int size() {
		return size;
	}

}
