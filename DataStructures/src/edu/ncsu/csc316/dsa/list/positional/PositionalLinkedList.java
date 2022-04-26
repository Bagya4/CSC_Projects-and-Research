package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 *
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 *
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }
    
    /** 
     * adds between two nodes
     * @param element to be added
     * @param next node after what is to be added
     * @param prev node before what is to be added
     * @return position object
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
    	PositionalNode<E> curr = new PositionalNode<>(element);
    	if(prev == null) {
    		front.next = curr;
    	}
    	else {
    		prev.next = curr;
    	}
    	
    	curr.next = next;
    	
    	if(next == tail) {
    		tail.previous = curr;
    	}
    	
    	Position<E> p = curr;
    	size++;
 
    	return p;
        
    }
    
    /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * PositonalNode is a static inner class
     * @author bmahara
     *
     * @param <E> generic type of obejct
     */
    private static class PositionalNode<E> implements Position<E> {

    	/** element or data of a node*/
        private E element;
        /** stores the next node */
        private PositionalNode<E> next;
        /** stores the previous node */
        private PositionalNode<E> previous;

        public PositionalNode(E value) {
            this(value, null);
        }

        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        public void setElement(E element) {
            this.element = element;
        }
    }

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * Add a new element into a new position that should be added immediately after
	 * the specified position, p
	 *
	 * @param p       the position that should be located before the new position
	 *                that will be created
	 * @param element the element to be added to the list
	 * @return a reference to the Position that was created to store the new element
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E element) {		
		PositionalNode<E> pn = validate(p);
		Position<E> newPos = addBetween(element, pn.next, pn);
		
		return newPos;
	}

	/**
	 * Add a new element into a new position that should be added immediately before
	 * the specified position, p
	 *
	 * @param p       the position that should be located after the new position
	 *                that will be created
	 * @param element the element to be added to the list
	 * @return a reference to the Position that was created to store the new element
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> pn = validate(p);
		Position<E> newPos = addBetween(element, pn, pn.getPrevious());
		
		return newPos;
	}

	/**
	 * Add a new element into a new position at the front of the list
	 *
	 * @param element the element to be added to the front of the list
	 * @return a reference to the Position that was created at the front of the list
	 *         to store the new element
	 */
	@Override
	public Position<E> addFirst(E element) {
		Position<E> p = addBetween(element, front.next, front);
		return p;
	}

	/**
	 * Add a new element into a new position at the end of the list
	 *
	 * @param element the element to be added to the end of the list
	 * @return a reference to the Position that was created at the end of the list
	 *         to store the new element
	 */
	@Override
	public Position<E> addLast(E element) {
		Position<E> p = addBetween(element, tail, tail.previous);
		return p;
		
	}

	/**
	 * Returns a reference to the Position that is located in the list immediately
	 * after the given position, p. Return null if p is at the end of the list.
	 *
	 * @param p the position for which to retrieve the position located after
	 * @return a reference to the Position that is located in the list immediately
	 *         after the given position, p
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> check = validate(p);
		if(check.next == tail) {
			return null;
		}
		
//		PositionalNode<E> f = front.next;
//		
//		for(int i = 0; i < size; i++) {
//			
//			if(f.getElement() == p.getElement()) {
//				break;				
//			}
//		
//			f = f.next;
//		}
//		Position<E> pos = f.next;
//		return pos;
		
		return check.next;
	}

	/**
	 * Returns a reference to the Position that is located in the list immediately
	 * before the given position, p. Return null if p is at the front of the list.
	 *
	 * @param p the position for which to retrieve the position located before
	 * @return a reference to the Position that is located in the list immediately
	 *         before the given position, p
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> check = validate(p);
		if(check.equals(front.next)) {
			return null;
		}
		if(check.equals(front.next)) {
			return null;
		}
		PositionalNode<E> f = front.next;
		PositionalNode<E> pr = front.next;
		for(int i = 0; i < size; i++) {
			//if(f.equals(front.next))
			if(f.getElement() == check.getElement()) {
				break;				
			}
			pr = f;
			f = f.next;
		}
		Position<E> pos = pr;
		return pos;
	}

	/**
	 * Returns a reference to the first Position in the list
	 *
	 * @return a reference to the first position in the list
	 */
	@Override
	public Position<E> first() {
		if(isEmpty()) {
			return null;
		}
		
		Position<E> pos = front.getNext();
		return pos;
	}

	/**
	 * Returns true if the list is empty, otherwise return false. Return null if the
	 * list is empty.
	 *
	 * @return true if the list is empty, otherwise return false
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * Returns a reference to the last/final Position in the list. Return null if
	 * the list is empty.
	 *
	 * @return a reference to the last position in the list
	 */
	@Override
	public Position<E> last() {
		if(isEmpty()) {
			return null;
		}
		
		Position<E> pos = tail.getPrevious();
		return pos;
	}

	/**
	 * Returns a new Iterable collection of list Positions
	 *
	 * @return a new Iterable collection of list positions
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
		
		//return p;
	}

	/**
	 * Removes the position p from the list and returns the element stored at p.
	 *
	 * @param p the position to remove from the list
	 * @return the element stored at p
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> removed = validate(p);
		E val = removed.getElement();
//		PositionalNode<E> prev = removed.getPrevious();
//		PositionalNode<E> nxt = removed.getNext();
//		prev.setNext(nxt);
//		nxt.setPrevious(prev);
//		size--;
//		
		
//		removed.setElement(null);
//		removed.setNext(nxt);
//		removed.setPrevious(null);
//		return val;
		
		int exists = 0;
		PositionalNode<E> c = front.next;
		for(int i = 0; i < size && c != null; i++) {
			if(c.getElement().equals(val)) {
				exists++;
			}
			c = c.next;
		}
		
		if(exists != 0) {
			PositionalNode<E> curr = front.next;
			int currSize = size();
			front = new PositionalNode<E>(null);
			tail = new PositionalNode<E>(null, null, front);
			front.setNext(tail);
			size = 0;
			for(int i = 0; i < currSize && curr.next != null; i++) {
				
				if(curr.getElement().equals(val)) {
					if(i < currSize - 1) {
						addLast(curr.next.getElement());
					}
					
					curr = curr.next;
				}
				else {
					addLast(curr.getElement());
				}
				
				curr = curr.next;
			}		
			
			return val;
		}
		
		return null;
		
	}

	/**
	 * Updates the element in a given position, p, to a new element.
	 *
	 * @param p       the position in which to update the element
	 * @param element the new element that will overwrite the existing element
	 * @return the original element that was replaced by the new element
	 * @throws IllegalArgumentException if the provided position, p, is not a valid
	 *                                  position
	 */
	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> old = validate(p);
		E oldName = old.getElement();
		old.setElement(element);
		
		return oldName;
	}

	/**
	 * The number of elements in the list
	 *
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Position iterator to keep track of current position object
	 * @author bmahara
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/** current position variable */
        private Position<E> current;
        /** stores boolean value for whether element before current can be removed */
        private boolean removeOK;

        /**
         * constructor of class
         */
        public PositionIterator() {
        	current = front;
        	removeOK = false;
            
        }

        /**
         * checks if next element exists
         * @return true if element exists
         */
        @Override
        public boolean hasNext() {
        	if( after(current) == null) {
        		return false;
        	}
        	return true;
            
        }

        /**
         * finds next element
         * @return position of next element
         */
        @Override
        public Position<E> next() {
        	if(current == tail || front.next == tail) throw new NoSuchElementException();
        	
        	if(hasNext()) {
        		removeOK = true;
        		Position<E> afterC = after(current);
        		current = after(current);
        		return afterC;
        		
        	}
        	
        	throw new NoSuchElementException();
            //return null;
        }

        /**
         * removes element before cursor
         */
        @Override
        public void remove() {
        	if(removeOK) {
        		PositionalLinkedList.this.remove(current);
        		removeOK = false;
        		return;
        	}
        	
        	throw new IllegalStateException();
        	
        	
            // You should consider delegating to
            // the outer class's remove(position) method,
            // similar to:
            // PositionalLinkedList.this.remove(position);
        }
    }
	
	/**
	 * Positions contain actual elements and this class delegates and adapts to position iterator
	 * @author bmahara
	 *
	 */
	private class ElementIterator implements Iterator<E> {

		/** position iterator */
        private Iterator<Position<E>> it;

        /**
         * constructor of inner class
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        /**
         * checks for next element
         * @return true if next element is there
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * finds next element
         * @return data of the next element
         */
        @Override
        public E next() {
            return it.next().getElement();
        }

        /**
         * removes element before cursor
         */
        @Override
        public void remove() {
            it.remove();
        }
        
    }
	
	 /**
     * Wrapper class to help adapt PositionIterator
     * @author bmahara
     *
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
    	/**
    	 * method to create and iterable object to loop through positions
    	 * @return iterable object
    	 */
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }


}
