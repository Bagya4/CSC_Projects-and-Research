package edu.ncsu.csc316.dsa.list;

/**
 * A skeletal implementation of the List abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the list
 * abstract data type.
 *
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public abstract class AbstractList<E> implements List<E> {

	/**
     * Adds a new element to the front of the list (index 0)
     *
     * @param element the new element to add to the front of the list
     */
    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * Adds a new element to the end of the list (index n)
     *
     * @param element the new element to add to the end of the list
     */
    @Override
    public void addLast(E element) {
        add(size(), element);
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when accessing any specific
     * indexes within the list.
     *
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when adding elements at specific
     * indexes within the list.
     *
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /**
     * Returns the element at the front of the list (index 0)
     *
     * @return the element at the front of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    @Override
    public E first() {
        return get(0);
    }

    /**
     * Returns true if the list is empty; otherwise, returns false
     *
     * @return true if the list is empty, or return false if the list is not empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the element at the end of the list (index n)
     *
     * @return the element at the end of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    @Override
    public E last() {
        return get(size() - 1);
    }

    /**
     * Removes and returns the element at the front of the list (index 0)
     *
     * @return the element at the front of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    @Override
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Removes and returns the element at the end of the list (index n)
     *
     * @return the element at the front of the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    @Override
    public E removeLast() {
        return remove(size() - 1);
    }
}
