package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

	/** Holds an array based list of entries */
    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SearchTableMap() {
        this(null);
    }

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    /**
     * Searches for key, therefore index of element
     * @param key to searched for
     * @return index of element in the list
     */
    private int lookUp(K key) {
    	if(size() == 0) return 0;
    	return binarySearchHelper(0, size() - 1, key);
    	
    }

    /**
     * Helps with inserting values in a sorted order
     * @param min index
     * @param max index
     * @param key target to locate
     * @return index at which the element in the list should be at
     */
    private int binarySearchHelper(int min, int max, K key) {
    	if(min > max) {
    		return -1 * (min + 1);
    	}
    	int mid = (max + min) / 2;
    	
    	if(list.get(mid).getKey().equals(key)) {
    		return mid;
    	}
    	
    	else if(compare(list.get(mid).getKey(), key) > 0) {
    		return binarySearchHelper(min, mid - 1, key);
    	}
    	
    	else {
    		return binarySearchHelper(mid + 1, max, key);
    	}
        
    }

    /**
     * finds number of elements in the list
     * @return size of list
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Gets the value for a given key
     * @param key of value needed
     * @return value for key
     */
    @Override
    public V get(K key) {
        int index = lookUp(key);
        if(index < 0) return null;
        //making sure value is obtained from right index
//        if(index == size() || compare(key, list.get(index).getKey()) != 0) {
//        	return null;
//        }
        return list.get(index).getValue();
    }

    /**
     * Makes use of the entry set
     * @return iterable collection of entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }

    /**
     * Adds a new entry to the map
     * @param key of entry
     * @param value of entry
     * @return value that is inserted
     */
    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        if(index == 0) {
        	if(size() > 0) {
        		V replaced = list.get(index).getValue();
            	list.remove(index);
            	list.add(index, new MapEntry<>(key, value));
            	
            	return replaced;
        	}
        	list.addFirst(new MapEntry<>(key, value));
        	return null;
        	
        }
        if(index < 0) {
        	list.add(-1 * (index + 1), new MapEntry<>(key, value));
        	//if value is newly added
        	return null;
        }
        
        if(index == size()) {
        	list.addLast(new MapEntry<>(key, value));
        	return null;
        }
        
        if(index < size() && compare(key, list.get(index).getKey()) == 0) {
        	V replaced = list.get(index).getValue();
        	list.remove(index);
        	list.add(index, new MapEntry<>(key, value));
        	
        	return replaced;
        	
        }
        
        
        return null;
    }

    /**
     * removes an entry for given key
     * @param key of entry to be removed
     * @return value of removed entry
     */
    @Override
    public V remove(K key) {
        int index = lookUp(key);
        if(index < 0) return null;
      //making sure value is obtained from right index
        if(index == size() || key.compareTo(list.get(index).getKey()) != 0) {
        	return null;
        }
        V old = list.get(index).getValue();
        list.remove(index);
        return old;
       
    }

    /**
     * Converts array based map to string format
     * @return string representation of search table
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
