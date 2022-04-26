package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

	/** Delegate to our existing singly linked list class **/
	private SinglyLinkedList<E> list;

	/**
	 * Construct a new singly-linked list to use when modeling the last-in-first-out
	 * paradigm for the stack abstract data type.
	 */
	public LinkedStack() {
		list = new SinglyLinkedList<E>();
	}

	/**
	 * adds element to top of stack or head of list
	 * @param element to be added
	 */
	@Override
	public void push(E element) {
		list.addFirst(element);

	}

	/**
	 * removes first element from stack
	 * @throws EmptyStackException if stack is empty
	 */
	@Override
	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		E topElement = list.removeFirst();
		return topElement;
	}

	/**
	 * returns value of first element in stack
	 * @throws EmptyStackException if stack is empty
	 */
	@Override
	public E top() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		E topElement = list.first();
		return topElement;
	}

	/**
	 * Size of stack
	 * @return number of elements in stack
	 */
	@Override
	public int size() {
		return list.size();
	}
}
