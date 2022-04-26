package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;


/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * Looks up position of entry with given key 
     * @param key of entry
     * @return node for entry
     */
    private SkipListNode<K, V> lookUp(K key) {
    	SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    /**
     * Getter for value in a given key
     * @return value of entry with given key. IF it DNE null
     */
    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        while(!isSentinel(temp) && compare(temp.getEntry().getKey(), key) != 0) {
        	temp = temp.getNext();
        }
        
        if(isSentinel(temp)) return null;
        
        return temp.getEntry().getValue();
    }

    /**
     * helper method for inserting a node in the list
     * @param prev node
     * @param down node
     * @param entry in map
     * @return node inserted after above
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        SkipListNode<K, V> newNode = new SkipListNode<>(entry);
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        if(prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext(newNode);
        }
        
        if(newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        
        if(down != null) {
        	down.setAbove(newNode);
        }
        
        return newNode;
        
    }

    /**
     * Inserts a new node
     * @param key of entry
     * @param value of entry
     * @return value of new or replaced entry 
     */
    @Override
    public V put(K key, V value) {
    	//Bottom-most entry immediately before the insertion location
        SkipListNode<K, V> temp = lookUp(key);
        if(!isSentinel(temp) && temp.getEntry().getKey().equals(key)) {
        	V old = temp.getEntry().getValue();
        	while(temp != null) {
        		temp.setEntry(new MapEntry<>(key, value));
        		temp = temp.getAbove();
        	}
        	
        	return old;
        }
        //new entry as we move to the level "above" after inserting into the bottom-most list
        SkipListNode<K, V> q = null;
        int currentLevel = -1;
        int coinFlip = 0;
        do {
        	currentLevel = currentLevel + 1;
        	if(currentLevel >= height) {
        		height = height + 1;
        		SkipListNode<K, V> tail = start.next;
        		start = insertAfterAbove(null, start, null);
        		insertAfterAbove(start, tail, null);
        	}
        	
        	q = insertAfterAbove(temp, q, new MapEntry<>(key, value));
        	while(temp.getAbove() == null) {
        		temp = temp.getPrevious();
        	}
        	temp = temp.getAbove();
        	coinFlip = coinToss.nextInt(2);
        	
        } while (coinFlip == 0);
        
        size++;
        return null;
    }

    /**
     * Removes an entry from the skip list
     * @param key of entry
     * @return value of entry
     */
    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        
        while(!isSentinel(temp) && compare(temp.getEntry().getKey(), key) != 0) {
        	temp = temp.getNext();
        }
        if(isSentinel(temp)) return null;
        
        V old = temp.getEntry().getValue();
        int currentHt = height;
        while(currentHt > 0 && temp != null) {
        	temp.prev.next = temp.next;
        	temp.next.prev = temp.prev;
        	temp = temp.above;
        	currentHt = currentHt - 1;
        }
        
        this.size = this.size - 1;
        
        return old;
        
    }

    /**
     * Finds number of elements in the skip list
     * @return size of list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Makes use of the entry set
     * @return iterable collection of entries in skip list
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
        
    }

    /**
     * Converts skipped list map to string format
     * @return string representation of skip list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
//    public String toFullString() {
//        StringBuilder sb = new StringBuilder("SkipListMap[\n");
//        SkipListNode<K, V> cursor = start;
//        SkipListNode<K, V> firstInList = start;
//        while (cursor != null) {
//            firstInList = cursor;
//            sb.append("-INF -> ");
//            cursor = cursor.next;
//            while (cursor != null && !isSentinel(cursor)) {
//                sb.append(cursor.getEntry().getKey() + " -> ");
//                cursor = cursor.next;
//            }
//            sb.append("+INF\n");
//            cursor = firstInList.below;
//        }
//        sb.append("]");
//        return sb.toString();
//    }
    
    /**
     * Inner class for skip list node    
     * @author bmahara
     *
     * @param <K> key 
     * @param <V> value
     */
    private static class SkipListNode<K, V> {

    	/** Stores entry in the map*/
        private Entry<K, V> entry;
        /** Stores node above current entry in the map*/
        private SkipListNode<K, V> above;
        /** Stores node below current entry in the map*/
        private SkipListNode<K, V> below;
        /** Stores node previous to current entry in the map*/
        private SkipListNode<K, V> prev;
        /** Stores node after the current entry in the map*/
        private SkipListNode<K, V> next;

        /**
         * Constructor of inner class
         * @param entry in skip list
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }

        /**
         * returns node above current one
         * @return node above
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * Getter for entry
         * @return current entry
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * Getter for next node
         * @return next node
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * Getter for previous node
         * @return prev node
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        /**
         * Setter for node above
         * @param up node
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * Setter for node below
         * @param down node
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * Setter for entry
         * @param entry in list
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * Setter for next node
         * @param next node
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * Setter for previous node
         * @param prev node
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }

}
