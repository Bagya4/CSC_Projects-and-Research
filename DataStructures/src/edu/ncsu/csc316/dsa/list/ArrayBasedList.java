package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	/**
	 * The initial capacity of the list if the client does not provide a capacity
	 * when constructing an instance of the array-based list
	 **/
	private final static int DEFAULT_CAPACITY = 0;

	/** The array in which elements will be stored **/
	private E[] data;

	/** The number of elements stored in the array-based list data structure **/
	private int size;

	/**
	 * Constructs a new instance of an array-based list data structure with the
	 * default initial capacity of the internal array
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a new instance of an array-based list data structure with the
	 * provided initial capacity
	 *
	 * @param capacity the initial capacity of the internal array used to store the
	 *                 list elements
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	/**
	 * Adds a new element to the list at the specified index.
	 *
	 * @param index   the index at which to add the new element to the list
	 * @param element the new element to add to the list
	 * @throws IndexOutOfBoundsException if the provided index is not a valid index
	 *                                   based on the current state of the list
	 */
	@Override
	public void add(int index, E element) {

		checkIndexForAdd(index);
		
		
		//growing array if element is added one after last element
		ensureCapacity(size + 1);

		//adding the element
		for(int i = size; i > index; i--) {
			data[i] = data[i - 1]; 
		} 

		data[index] = element;

		//size increases as element is added
		size++;

		

	}

	/**
	 * Keeps track of current position in a list
	 * @return Iterator which is the iterator created
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = oldCapacity * 2 + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	/**
	 * Returns the element at the specified index of the list
	 *
	 * @param index the index at which to retrieve the element
	 * @return the element at the specified index of the list
	 * @throws IndexOutOfBoundsException if the specified index is not a valid index
	 *                                   based on the current state of the list
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return data[index];
	}

	/**
	 * Removes and returns the element at the specified index of the list
	 *
	 * @param index the index of the element to remove from the list
	 * @return the element at the specified index of the list
	 * @throws IndexOutOfBoundsException if the provided index is not a valid index
	 *                                   based on the current state of the list
	 */
	@Override
	public E remove(int index) {
		E removed = get(index);

		//invalid index
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		for(int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}

		//to "close" the list
		data[size - 1] = null;		
		//size decreases as element is removed
		size--;

		return removed;
	}

	/**
	 * Updates the element at the specified index of the list
	 *
	 * @param index   the index at which an existing element should be updated
	 * @param element the new element to store are the provided index
	 * @return the original element that was replaced by the updated element
	 * @throws IndexOutOfBoundsException if the provided index is not a valid index
	 *                                   based on the current state of the list
	 */
	@Override
	public E set(int index, E element) {

		checkIndex(index);
		

		E old = data[index];
		data[index] = element;

		return old;

	}

	/**
	 * Returns the number of elements in the list
	 *
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Iterator to visit each element in the list of data
	 * @author bmahara
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/** stores position of iterator */
	    private int position;
	    /** stores true if element can be removed */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	    	position = 0;
	        removeOK = false;
	    }

	    /**
	     * Checks if the element after the cursor exists i.e. if iterator is before the last element
	     * @return true if next element exists
	     */
	    @Override
	    public boolean hasNext() {
	    	if(position < size) {
	    		return true;
	    	}
	    	
	    	return false;
	        
	    }

	    /**
	     * Moves cursor to the next element
	     * @return the element before the cursor
	     * @throws NoSuchElementException is looking for the next element after the last one
	     */
	    @Override
	    public E next() {
	    	if(position == size()) {
	    		throw new NoSuchElementException();
	    	}
	    	E nextElement = data[position];
	    	position++;
	    	
	    	removeOK = true;
	    	
	    	return nextElement;
	    }
	        
	    /**
	     * Removes the element before the iterator
	     * Delegates to outer class' remove method
	     */
	    @Override
	    public void remove() {
	    	if(!removeOK) throw new IllegalStateException();
	    	if(removeOK) {
	    		ArrayBasedList.this.remove(position - 1);
	    		removeOK = false;
	    		position--;
	    	}
	        
	    } //end of remove
	}
}
