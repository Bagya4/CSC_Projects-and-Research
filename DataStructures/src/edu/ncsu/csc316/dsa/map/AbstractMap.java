package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 *
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     *
     * @author Dr. King
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {
    	
    	/** Key for the map entry */
        private K key;
        /** Value for the map entry */
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         *
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         *
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         *
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }
    }

    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     *
     * @author Dr. King
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {    	
    	
        /** list of entries */
    	private List<Entry<K, V>> list;

    	/**
    	 * Constructor of class
    	 */
        public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
        }

        /**
         * Adds entry to list
         * @param entry to be added
         */
        public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }

        /**
         * Iterator for all entries
         * @return iterator for entries
         */
        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }

        /**
         * Iterator for entries in the list(map)
         * @author bmahara
         *
         */
        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

        	/** variable for iterator */
            private Iterator<Entry<K, V>> it;

            /**
             * Constructor of inner class
             */
            public EntryCollectionIterator() {
                it = list.iterator();
            }

            /**
        	 * checks if a next entry exists
        	 * @return true if next element exists
        	 */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            /**
       	  * Returns entry at cursor before moving to the next one
       	  * @return next entry
       	  */
            @Override
            public Entry<K, V> next() {
                return it.next();
            }

            /**
       	  * removes entry returned by the previous call to next
       	  * @throws UnsupportedOperationException if entry cannot be removed
       	  */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }

    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map
     *
     * @author Dr. King
     *
     */
    protected class KeyIterator implements Iterator<K> {
    	
    	/** iterator variable */
    	private Iterator<Entry<K, V>> it;
    	
    	/**
    	 * Constructor of class
    	 */
    	public KeyIterator() {
            it = entrySet().iterator();
        }
    	
    	/**
    	 * checks if a next element exists
    	 * @return true if next element exists
    	 */
    	@Override
        public boolean hasNext() {
            return it.hasNext();
        }
    	 
    	 /**
    	  * Returns element at cursor before moving to the next one
    	  * @return Key of next value
    	  */
    	@Override
        public K next() {
            return it.next().getKey();
        }
    	 
    	 /**
    	  * removes element(key) returned by the previous call to next
    	  * @throws UnsupportedOperationException if entry cannot be removed
    	  */
    	@Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
        
    }

    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     *
     * @author Dr. King
     *
     */
    protected class ValueIterator implements Iterator<V> {
    	
    	/** iterator variable */
    	private Iterator<Entry<K, V>> it;
    	
    	/**
    	 * Constructor of class
    	 */
    	public ValueIterator() {
    		it = entrySet().iterator();
    	}
    	
    	/**
    	 * checks if a next element exists
    	 * @return true if next element exists
    	 */
    	@Override
        public boolean hasNext() {
            return it.hasNext();
        }
    	 
    	 /**
    	  * Returns element at cursor before moving to the next one
    	  * @return Key of next value
    	  */
    	@Override
        public V next() {
            return it.next().getValue();
        }
    	 
    	 /**
    	  * removes element(key) returned by the previous call to next
    	  * @throws UnsupportedOperationException if entry cannot be removed
    	  */
    	@Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    	
    }

    /**
     * checks if map is empty
     * @return true if map is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * creates instance of an iterator which will help to iterate through the map
     * @return an iterator
     */
    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    /**
     * an iterable set of map values is made
     * @return iterable map values
     */
    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    private class ValueIterable implements Iterable<V> {
    	
    	/**
    	 * creates instance of an iterator
    	 * @return an iterator
    	 */
    	public Iterator<V> iterator() {
    		return new ValueIterator();
    	}
        
    }

}
