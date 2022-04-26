package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The LinkedBinaryTree is implemented as a linked data structure to support
 * efficient Binary Tree abstract data type behaviors.
 *
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 *
 * The LinkedBinaryTree class is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 * @param <E> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/** Root of tree */
    private BinaryTreeNode<E> root;
    /** number of elements in the tree */
    private int size;

    /**
     * Create a new empty binary tree
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Safely casts a Position, p, to be a BinaryTreeNode.
     *
     * @param p the position to cast to a BinaryTreeNode
     * @return a reference to the BinaryTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  BinaryTreeNode
     */
    protected BinaryTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof BinaryTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid linked binary tree node");
        }
        
        BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
    	
    	return node;
    	
    }

    /**
     * A BinaryTreeNode stores an element and references the node's parent, left
     * child, and right child
     *
     * @author Dr. King
     *
     * @param <E> the type of element stored in the node
     */
    public static class BinaryTreeNode<E> extends AbstractTreeNode<E> {
    	/** parent node */
        private BinaryTreeNode<E> parent;
        /** left node */
        private BinaryTreeNode<E> left;
        /** right node */
        private BinaryTreeNode<E> right;

        /**
         * Constructs a new BinaryTreeNode with the provided element
         *
         * @param element the element to store in the node
         */
        public BinaryTreeNode(E element) {
            this(element, null);
        }

        /**
         * Constructs a new BinaryTreeNode with the provided element and provided parent
         * reference
         *
         * @param element the element to store in the node
         * @param parent  the parent of the newly created node
         */
        public BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Returns the left child of the current node
         *
         * @return the left child of the current node
         */
        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child of the current node
         *
         * @return the right child of the current node
         */
        public BinaryTreeNode<E> getRight() {
            return right;
        }

        /**
         * Sets the left child of the current node
         *
         * @param left the node to set as the left child of the current node
         */
        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child of the current node
         *
         * @param right the node to set as the right child of the current node
         */
        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }

        /**
         * Returns the parent of the current node
         *
         * @return the parent of the current node
         */
        public BinaryTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of the current node
         *
         * @param parent the node to set as the parent of the current node
         */
        public void setParent(BinaryTreeNode<E> parent) {
            this.parent = parent;
        }
    }

    /**
     * Position of left node
     * @param p position
     * @return position of p's left node
     */
    @Override
    public Position<E> left(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Position of right node
     * @param p position
     * @return position of p's right node
     */
    @Override
    public Position<E> right(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Creates left child of position p
     * @param p position
     * @param value to be added
     * @return position of new node
     */
    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        BinaryTreeNode<E> child = createNode(value, node, null, null);
        node.setLeft(child);
        size++;
        
        return child;
    }

    /**
     * Creates right child of position p
     * @param p position
     * @param value to be added
     * @return position of new node
     */
    @Override
    public Position<E> addRight(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        
        BinaryTreeNode<E> child = createNode(value, node, null, null);
        node.setRight(child);
        size++;
        
        return child;
    }

    /**
     * Returns root of tree
     * @return position of root
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Finds parent of a position
     * @param p position
     * @return position of parent, null if p is root
     */
    @Override
    public Position<E> parent(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Adds root of tree if tree is empty
     * @param value to add
     * @return position of root
     */
    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    /**
     * Removes a node from the tree
     * @param p position of node to be removed
     * @return value of removed node
     */
    @Override
    public E remove(Position<E> p) {
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("The node has two children");
        }
        BinaryTreeNode<E> node = validate(p);
        BinaryTreeNode<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if(child != null) {
        	child.setParent(node.getParent());
        }
        if(node == root) {
        	root = child;
        }
        else {
        	BinaryTreeNode<E> mom = node.getParent();
        	if(node == mom.getLeft()) {
        		mom.setLeft(child);
        	}
        	else {
        		mom.setRight(child);
        	}
        } // end of else on line 258
        
        size--;
        E old = node.getElement();
        //garbage collection
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        
        node.setParent(node);
        return old;
    }

    /**
     * Size of tree
     * @return number of elements in tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Creates a new binary tree node
     * @param e value
     * @param parent node
     * @param left node
     * @param right node
     * @return binary tree node
     */
    protected BinaryTreeNode<E> createNode(E e, BinaryTreeNode<E> parent, BinaryTreeNode<E> left,
            BinaryTreeNode<E> right) {
        BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}
