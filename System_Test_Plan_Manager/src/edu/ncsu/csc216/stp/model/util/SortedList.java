package edu.ncsu.csc216.stp.model.util;

/**
 * The SortedList class implements the ISortedList interface. The generic type
 * should extend the Comparable interface. The SortedList class uses the
 * Comparable.compareTo() method to determine the ordering of elements.
 * 
 * @author bmahara
 * @author Mari Kilgus
 * @param <E> type of element class to implement a sorted list and determines
 *            ordering of elements
 *
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/** size of the list */
	private int size;
	/** reference to front of list node */
	private ListNode front;

	/**
	 * The ListNode data structure. Inner class for SortedList implements list node.
	 * 
	 * @author bmahara
	 * @author Mari Kilgus
	 */
	public class ListNode {

		/** field for data in a node */
		public E data;
		/** field for the next node reference */
		public ListNode next;

		/**
		 * constructor for class
		 * 
		 * @param element to be added
		 * @param node    is the reference to the next node or null
		 */
		public ListNode(E element, ListNode node) {
			this.data = element;
			this.next = node;
		}
	}

	/**
	 * Adds the element E and does not allow for duplicates to be added to list
	 * 
	 * @param element is the element being added to the list
	 * @throws NullPointerException     if element to be added is null
	 * @throws IllegalArgumentException if the element is a duplicate of another
	 *                                  present in the list
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		if (front == null) {
			front = new ListNode(element, front);
			size++;
		} else {
			ListNode current = front;
			if (front.data.compareTo(element) > 0) {
				front = new ListNode(element, front);
				size++;
				return;
			}

			while (current.next != null) {
				if ((current.next.data).compareTo(element) < 0) {
					current = current.next;
				}

				else {
					break;
				}
			}
			// if trying to add to the end of the list
			current.next = new ListNode(element, current.next);
			size++;

		}
	}

	/**
	 * Removes the element at the specified index.
	 * 
	 * @param idx is index of element to be removed
	 * @return E element
	 * @throws IndexOutOfBoundsException if index is out of range
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		if(size() == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E value = null;
		if (idx == 0) {
			value = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return value;
	}

	/**
	 * Checks that index is in bounds
	 * 
	 * @param idx to be checked
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * method to check if an element exists or not in the list
	 * 
	 * @param element to check for
	 * @return true if element is present in list else false
	 */
	@Override
	public boolean contains(E element) {
		boolean inList = false;
		ListNode current = front;
		for (int i = 0; i < size(); i++) {
			if (current.data.equals(element)) {
				inList = true;
				break;
			}
			current = current.next;
		}
		return inList;
	}

	/**
	 * Returns the element at the specified index
	 * 
	 * @param idx for index at which element is needed
	 * @return E element at index
	 * @throws IndexOutOfBoundsException if index is not in range
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		if(size() == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		try {
			ListNode current = front;
			for (int i = 0; i < idx; i++) {
				current = current.next;

			}

			return current.data;
		} catch (NullPointerException e) {
			//
		}
		return null;
	}

	/**
	 * Returns the current size of the log list.
	 * 
	 * @return size of list as an integer value
	 */
	@Override
	public int size() {
		return size;
	}
}
