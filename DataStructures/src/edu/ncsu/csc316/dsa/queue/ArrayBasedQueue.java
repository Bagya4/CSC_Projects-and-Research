package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
 * The Array-based Queue is implemented as a circular array-based data structure
 * to support efficient, O(1) worst-case Queue abstract data type behaviors. The
 * internal array dynamically resizes using the doubling strategy to ensure O(1)
 * amortized cost for adding to the queue.
 *
 * @author Dr. King
 *@author Bagya Maharajan
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

    /**
     * Internal array to store the data within the queue
     */
    private E[] data;

    /**
     * A reference to the index of the first element in the queue
     */
    private int front;

    /**
     * A reference to the index immediately after the last element in the queue
     */
    private int rear;

    /**
     * The number of elements stored in the queue
     */
    private int size;

    /**
     * The initial default capacity of the internal array that stores the data
     */
    private static final int DEFAULT_CAPACITY = 0;

    /**
     * Constructs a new array-based queue with the given initialCapcity for the
     * array
     *
     * @param initialCapacity the initial capacity to use when creating the initial
     *                        array
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedQueue(int initialCapacity) {
        data = (E[]) (new Object[initialCapacity]);
        size = 0;
    }

    /**
     * Constructs a new array-based queue with the default initial capacity for the
     * array
     */
    public ArrayBasedQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * To ensure amortized O(1) cost for adding to the array-based queue, use the
     * doubling strategy on each resize. Here, we add +1 after doubling to handle
     * the special case where the initial capacity is 0 (otherwise, 0*2 would still
     * produce a capacity of 0).
     *
     * @param minCapacity the minimium capacity that must be supported by the
     *                    internal array
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) (new Object[newCapacity]);
            //copying element at front to 0th index
            for(int i = 0; i < size; i++) {
            	newData[i] = data[front];
            	front++;
            	//going back to 0 if end of queue is reached which will be 1 more than n - 1
            	if(front == size) {
            		front = 0;
            	}
            }
            
            data = newData;
            front = 0;
            //rear is one after the last element
            rear = size;
            
            // Remember that we cannot copy an array the same way we do
            // when resizing an array-based list since we do not want to
            // "break" the elements in the queue since there may be wrap-around
            // at the end of the array
        }
    }

    /**
	 * Adds a new element to the back of the queue
	 *
	 * @param element the new element to add to the queue
	 */
	@Override
	public void enqueue(E element) {
		//not just size because default capacity starts with 0
		ensureCapacity(size + 1);
		int idx = (front + size) % data.length;
		data[idx] = element;
		rear = rear + 1;
		size++;
		
	}

	/**
	 * Removes and returns the front/first element in the queue
	 *
	 * @return the front/first element in the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		E first = data[front];
		
		front = (front + 1) % data.length;
		size--;
		return first;
	}

	/**
	 * Returns (but does not remove) the front/first element in the queue
	 *
	 * @return the front/first element in the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E front() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		E first = data[front];
		return first;
	}

	/**
	 * Returns the number of elements stored in the queue
	 *
	 * @return the number of elements stored in the queue
	 */
	@Override
	public int size() {
		return size;
	}

}
