package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** holds map elements as a positional list */
    private PositionalList<Entry<K, V>> list;

    /**
     * Constructor of class
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }

    /**
     * Looks up position of a key in the map of entries
     * @param key of entry
     * @return psoitino of entry with given key
     */
    private Position<Entry<K, V>> lookUp(K key) {
    	int size = list.size();
    	Position<Entry<K, V>> p = list.first();
    	for(int i = 0; i < size; i++) {
    		if(p.getElement().getKey().equals(key)) {
    			return p;
    		}
    		p = list.after(p);
    	}
    	//if position does not exist
    	return null;
    }
    

    /**
     * Gets the value associated with given key and is M-edTF
     * @return value for the key
     */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p == null) {
     	   return null;
        }
        
        V got = p.getElement().getValue();
        moveToFront(p);
        return got;
        
    }

    /**
     * Implements the move to front heuristic
     * @param position to be moved to front
     */
    private void moveToFront(Position<Entry<K, V>> position) {    	
    	list.addFirst(list.remove(position));
    }

    /**
     *  Updates the entry with the given key to have the new value
     *  @param key of entry to be added
     *  @param value of given key
     *  @return value that was replaced else if it is a new entry return null
     */
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);        
        if(p == null) {
        	MapEntry<K, V> add = new MapEntry<K, V>(key, value);
        	list.addFirst(add);
        	return null;
        }
        
        else {
        	V old = p.getElement().getValue();
        	list.remove(p);
        	MapEntry<K, V> add = new MapEntry<K, V>(key, value);
        	list.addFirst(add);
        	//moveToFront(p);
        	return old;
        }
        
    }

    /**
     * Removes entry for given key
     * @param key to be searched for and its valeu should be removed
     * @return value removed
     */
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if(p == null) return null;
       
       V removed = p.getElement().getValue();       
       list.remove(p);
       return removed;
       
    }

    /**
     * Finds number of entries in map
     * @return size of map
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Helps to iterate over all entries and not just keys or values
     * @return iterable collection of entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {    	
    	EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
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
