package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 *
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {

	/**
	 * checks if a node is an internal node
	 * @param p position
	 * @return true if p has more than 0 children
	 */
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    /**
     * Checks if a node is a leaf node
     * @param p position
	 * @return true if p has  0 children
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }

    /**
     * Checks if a node is the root
     * @param p position
	 * @return true if p is the root
     */
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    /**
     * Checks if tree is empty
	 * @return true if tree has size 0
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Sets the value for a position in the tree
     * @param p is position
     * @param value is value to be set to
     * @return original value that was in the position
     */
    @Override
    public E set(Position<E> p, E value) {
    	AbstractTreeNode<E> temp = validate(p);
        E old = temp.getElement();
        temp.setElement(value);
        
        return old;
    }

    /**
     * Preorder traversal of a tree
     * @return an iterable collection of positions in pre-order
     */
    @Override
    public Iterable<Position<E>> preOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Helper method for pre-order traversal
     * @param p position
     * @param traversal collection
     */
    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
        traversal.add(p);
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }   

    /**
     * Postorder traversal of a tree
     * @return an iterable collection of positions in post-order
     */
    @Override
    public Iterable<Position<E>> postOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            postOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Helper method for post order traversal
     * @param p position
     * @param traversal collection
     */
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
        for(Position<E> c: children(p)) {
        	postOrderHelper(c, traversal);
        }
        
        traversal.add(p);
    }
    
    /**
     * Level order traversals in a tree
     * @return an iterable collection of positions in level-order
     */
    @Override
    public Iterable<Position<E>> levelOrder() { 
    	PositionCollection traversal = new PositionCollection();
    	if(!isEmpty()) {
    		Queue<Position<E>> tree = new ArrayBasedQueue<>();
            tree.enqueue(root());
            
            while(!tree.isEmpty()) {
            	Position<E> p = tree.dequeue();
            	traversal.add(p);
            	for(Position<E> c: children(p)) {
            		tree.enqueue(c);
            	}
            }
    	}
        
        return traversal;
        
    }

    /**
     * Safely casts a Position, p, to be an AbstractTreeNode.
     *
     * @param p the position to cast to a AbstractTreeNode
     * @return a reference to the AbstractTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  AbstractTreeNode
     */
    protected abstract AbstractTreeNode<E> validate(Position<E> p);

    protected abstract static class AbstractTreeNode<E> implements Position<E> {

    	/** element in a node */
        private E element;

        public AbstractTreeNode(E element) {
            setElement(element);
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * PositionCollection implements the {@link Iterable} interface to allow traversing
     * through the positions of the tree. PositionCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class PositionCollection implements Iterable<Position<E>> {

    	/** list of positions of elements in the tree */
        private List<Position<E>> list;

        /**
         * Construct a new PositionCollection
         */
        public PositionCollection() {
            list = new SinglyLinkedList<Position<E>>();
        }

        /**
         * Add a position to the collection. Null positions or positions with null
         * elements are not added to the collection
         * 
         * @param position the position to add to the collection
         */
        public void add(Position<E> position) {
            if (position != null && position.getElement() != null) {
                list.addLast(position);
            }
        }

        /**
         * Return an iterator for the PositionCollection
         * @return iterator
         */
        public Iterator<Position<E>> iterator() {
            return new PositionSetIterator();
        }

        private class PositionSetIterator implements Iterator<Position<E>> {
        	
        	/** Iterator for positions in the list */
            private Iterator<Position<E>> it;

            public PositionSetIterator() {
                it = list.iterator();
            }

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Position<E> next() {
                return it.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }

    /**
     * toString method
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Private helper method
     * @param sb builder
     * @param indent space
     * @param root of tree
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
}
