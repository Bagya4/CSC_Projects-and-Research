package edu.ncsu.csc216.stp.model.util;

/**
 * Log The Log class implements the ILog interface. The interface provides
 * Javadoc that describes what each method should do and the exceptions that
 * should be thrown. The Log allows duplicate elements.
 * 
 * @author bmahara
 * @author Mari Kilgus
 * @param <E> element type of the list The class to implement log interface
 */
public class Log<E> implements ILog<E> {

	/** list of type E */
	private E[] log;
	/** size of the list */
	private int size;
	/** initial size of log list */
	private static final int INIT_CAPACITY = 10;

	/**
	 * Constructor for Log
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		log = (E[]) new Object[INIT_CAPACITY];
		size = 0;
	}

	/**
	 * Adds the element E and allows for duplicates to be added to list
	 * 
	 * @param element is the element being added to the list
	 * @throws NullPointerException if element to be added is null
	 */
	@Override
	public void add(E element) {
		int ensureCapacity = size + 1;
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (log.length < ensureCapacity) {
			growArray();
		}
		log[size] = element;
		size++;
	}

	/**
	 * Method for returning the object at index
	 * 
	 * @param index the index to get the object from the ArrayList
	 * @return the object at the index of the ArrayList
	 * @throws IndexOutOfBoundsException if attempting to get an element outside of
	 *                                   range
	 */
	@Override
	public E get(int index) {
//		if(index == -1) {
//			return log[size - 1];
//		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return log[index];
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

	/**
	 * Grows the array when we reach capacity
	 */
	@SuppressWarnings("unchecked")
	public void growArray() {
		E[] newList = (E[]) new Object[log.length * 2];
		for (int i = 0; i < size(); i++) {
			newList[i] = log[i];
		}
		log = newList;
	}
}
