package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 *
 * @author Dr. King
 *@author Bagya Maharajan
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** A reference to the dummy/sentinel node at the front of the list **/
	private LinkedListNode<E> front;

	/** A reference to the last/final node in the list **/
	private LinkedListNode<E> tail;

	/** The number of elements stored in the list **/
	private int size;

	/**
	 * Constructs an empty singly-linked list
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	/**
	 * Inner class for a list node
	 * @author bmahara
	 *
	 * @param <E> generic type of objects to deal with
	 */
	private static class LinkedListNode<E> {

		/** Stores data in a node */
		private E data;
		/** Reference to the next node in the list */
		private LinkedListNode<E> next;

		/**
		 * constructor of inner class
		 * @param data of a node
		 */
		public LinkedListNode (E data) {
			this.data = data;
		}

		/**
		 * constructor of inner class which accepts data and a node
		 * @param data of node
		 * @param node to be added
		 */
		public LinkedListNode (E data, LinkedListNode<E> node) {
			this.data = data;
			this.next = node;
		}

		/**
		 * Gets the next node
		 * @return next node reference
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}

		/**
		 * Gets the element value of a node
		 * @return data value of the node
		 */
		public E getElement() {
			return data;
		}

		/**
		 * Sets the next reference to the current node
		 * @param node to be set as next
		 */
		public void setNext(LinkedListNode<E> node) {
			next = node;
		}

		/**
		 * Sets the data of the current node to the value passed in 
		 * @param value to be set as data
		 */
		public void setElement(E value) {
			data = value;
		}

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

		//invalid index
//		if(index < 0 || index > size) {
//			throw new IndexOutOfBoundsException();
//		}

		if(index == 0) {
			if(front.next == null) {
				front.next = new LinkedListNode<> (element);
				tail = front.next;
				size++;
				return;
			}
			else {
				LinkedListNode<E> newfront = new LinkedListNode<>(element, front.next);
				front.next = newfront;
				size++;
			}

		}

		//&& front.next.next != null
		else if(index == size - 1 && get(index) == null) {
			addLast(element);
		}

		else {
			LinkedListNode<E> curr = front.next;
			LinkedListNode<E> node = new LinkedListNode<>(element, null);


			for(int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}

			node.next = curr.next;
			curr.next = node;


			size++;
		}



		LinkedListNode<E> curr2 = front.next;
		while(curr2.getNext() != null) {
			curr2 = curr2.next;
		}
		tail = curr2;
		

	}

	/**
	 * For a singly-linked list, this behavior has O(1) worst-case
	 * runtime.
	 * @return data of last element
	 */
	@Override
	public E last() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("The list is empty");
		}
		return tail.getElement();
	}

	/**
	 * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
	 * @param element to be added to end of list
	 */    
	@Override
	public void addLast(E element) {
		LinkedListNode<E> last = new LinkedListNode<E>(element);
		if(size == 0) {
			front.next = last;
			tail = last;
			size++;
			return;
		}
		tail.setNext(last);
		tail = last;
		size++;
	}

	/**
	 * Returns the element/ data at the specified index of the list
	 *
	 * @param index the index at which to retrieve the element
	 * @return the element at the specified index of the list
	 * @throws IndexOutOfBoundsException if the specified index is not a valid index
	 *                                   based on the current state of the list
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
//		if(index < 0 || index >= size) {
//			throw new IndexOutOfBoundsException();
//		}

		if(index == 0) {
			return front.next.data;
		}

		if(index == size - 1) {
			return tail.data;
		}

		LinkedListNode<E> curr = front.next;
		for(int i = 0; i < index; i++) {
			curr = curr.next;
		}

		return curr.data;
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
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		

		LinkedListNode<E> curr = front.next;

		if(index == 0) {
			E frontData = curr.data;
			if(size == 1) {
				front.next = null;
				size--;
				return frontData;
			}
			front.next = front.next.next;
			size--;
			return frontData;
		}
		


		for(int i = 0; i < index - 1; i++) {
			curr = curr.next;
		}

		E removed = curr.next.data;
		if(index == size - 1) {
			curr.next = null;
			tail = curr;
						
		}
		else {
			curr.next = curr.next.next;
		}

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
		
		//invalid index
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		LinkedListNode<E> curr = front.next;
		for(int i = 0; i < index; i++) {
			curr = curr.next;
		}
		E old = curr.data;
		curr.setElement(element);
		return old;
	}

	/**
	 * Give sthe Size of list
	 *
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * private class to maintain iterator
	 * @author bmahara
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    /**
	     * Keep track of the next node that will be processed
	     */
	    private LinkedListNode<E> current;
	    
	    /** 
	     * Keep track of the node that was processed on the last call to 'next'
	     */
	    private LinkedListNode<E> previous;
	    
	    /** 
	     * Keep track of the previous-previous node that was processed
	     * so that we can update 'next' links when removing
	     */
	    private LinkedListNode<E> previousPrevious;
	    
	    /**
	     * Keep track of whether it's ok to remove an element (based on whether
	     * next() has been called immediately before remove())
	     */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        current = front.next;
	        previous = front;
	        previousPrevious = previous;
	        
	        removeOK = false;
	    }

	    /**
	     * Checks if the element after the cursor exists i.e. if iterator is before the last element
	     * @return true if next element exists
	     */
	    @Override
	    public boolean hasNext() {
	    	
	    	if(size == 0 || getCurrent() == null) return false;
	    	
	    	if(current.next != null || size == 1) {
	    		return true;
	    	}
	    	if(current.next == null) {
	    		return true;
	    	}
	    	
	    	return false;
	        
	    }

	    /**
	     * Moves cursor to the next element
	     * @return the element before the cursor
	     * @throws NoSuchElementException if next is called at last element
	     */
	    @Override
	    public E next() {
	    	if(!hasNext()) {
	    		throw new NoSuchElementException();
	    	}
	    	
	    	previousPrevious = getPreviousPrevious();
	    	previousPrevious = previous;
	    	setPrevious(current);
	    	
	    	E element = previous.data;
	    	
	    	current = current.next; 
//	    	if(size != 1) {
//	    		element = current.data;
//	    	}   		
    		
    		setRemoveOK(true);
    		return element;
    		
	    }
	     
	    /**
	     * Removes the element before the iterator
	     */
	    @Override    
	    public void remove() {
	    	if(!isRemoveOK()) throw new IllegalStateException();
	    	if(isRemoveOK()) {
	    		setRemoveOK(false);
	    		if(previous.next == null) {
	    			previousPrevious.next = null;
	    			previous = getPreviousPrevious();
	    			tail = previousPrevious;
	    			size--;
	    			return;
	    		}
	    		previousPrevious.next = current;
	    		previous = getPreviousPrevious();
	    		size--;
//	    		LinkedListNode<E> curr = front.next;
//	    		//current = front.next;
//	    		for(int i = 0; i < size; i++) {
//	    			if(curr.data == previous.data) {
//	    				//SinglyLinkedList.this.remove(i);
//	    				previousPrevious.next = current;
////	    				current = front.next;
////	    				
////	    				for(int j = 0; j < i; j++) {
////	    					current = current.next;
////	    				}
//	    				
//	    				previous = previousPrevious;
//	    				return;
//	    			}
//	    			curr = curr.next;
//	    		}
	    	}
   	
	    	
	    }
	    
	    /**
	     * Getter for current node
	     * @return current node
	     */
	    public LinkedListNode<E> getCurrent() {
	    	return current;
	    }
	    
	    /**
	     * Setter for previous node
	     * @param previous node
	     */
	    public void setPrevious(LinkedListNode<E> previous) {
	    	this.previous = previous;
	    }
	    
	    /**
	     * Getter for prevPrevious
	     * @return theprevPrevious
	     */
	    public LinkedListNode<E> getPreviousPrevious() {
	    	return previousPrevious;
	    }
	    
	    /**
	     * Setter for removeOK
	     * @param remove true or false
	     */
	    public void setRemoveOK(boolean remove) {
	    	this.removeOK = remove;
	    }
	    
	    /**
	     * Tells is element can be removed
	     * @return true if removeOK
	     */
	    public boolean isRemoveOK() {
	    	return removeOK;
	    }
	}

}
