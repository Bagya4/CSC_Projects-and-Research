package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;
//import edu.ncsu.csc316.dsa.tree.AbstractTree.PositionCollection;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 *
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	/**
     * Level order traversals in a tree
     * @return an iterable collection of positions in level-order
     */
    @Override
    public Iterable<Position<E>> inOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }

    /**
     * Helper method for in order traversals
     * @param p position
     * @param traversal collection
     */
    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
        if(left(p) != null) {
        	inOrderHelper(left(p), traversal);
        }
        traversal.add(p);
        
        if(right(p) != null) {
        	inOrderHelper(right(p), traversal);
        }
        
        
    }
    
    /**
     * Finds the number of children of position p
     * @param p is position
     * @return number of children
     */
    @Override
    public int numChildren(Position<E> p) {
    	int child = 0;
    	if(left(p) != null) {
    		child++;
    	}
    	if(right(p) != null) {
    		child++;
    	}
    	return child;
    }

    /**
     * Finds position of a node's sibling
     * @param p is position
     * @return null if no sibling exists, else its position
     */
    @Override
    public Position<E> sibling(Position<E> p) {
        Position<E> mom = parent(p);
        //if p is root
        if(mom == null) return null;
        
        if(p.equals(left(mom))) {
        	return right(mom);
        }
        return left(mom);
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}
