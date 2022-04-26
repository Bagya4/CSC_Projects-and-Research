package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.disjoint_set.DisjointSetForest;
import edu.ncsu.csc316.dsa.disjoint_set.UpTreeDisjointSetForest;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * MinimumSpanningTreeUtil provides a collection of behaviors for computing
 * minimum spanning trees for a given graph.
 *
 * The MinimumSpanningTreeUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Bagya Maharajan
 *
 */
public class MinimumSpanningTreeUtil {

    /**
     * Returns a positional list of minimum spanning tree edges for the given graph
     * using Kruskal's minimum spanning tree algorithm.
     *
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param g the graph for which to compute a minimum spanning tree
     * @return a positional list of minimum spanning tree edges
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
    	//list to store the edges in the MinimumSpanningTree
    	PositionalList<Edge<E>> plist = new PositionalLinkedList<Edge<E>>();
    	// A priority queue to order the edges
    	PriorityQueue<Integer, Edge<E>> pq = new HeapPriorityQueue<Integer, Edge<E>>();
    	// A disjoint set to help with cycle detection
    	DisjointSetForest<Vertex<V>> foret = new UpTreeDisjointSetForest<Vertex<V>>();
    	
    	// inserting the edges into the priority queue, partially ordered according to edge weight
    	for(Edge<E> e: g.edges()) 
    		pq.insert(e.getElement().getWeight(), e);
    	
    	// Initialize each vertex into its own disjoint set
    	for(Vertex<V> v: g.vertices())
    		foret.makeSet(v);
    	
    	int components = g.numVertices();
    	
    	while(components > 1) {
    		// processing edges in order of increasing weight
    		Edge<E> e = pq.deleteMin().getValue();
    		Position<Vertex<V>> u = foret.find(g.endVertices(e)[0]);
    		Position<Vertex<V>> v = foret.find(g.endVertices(e)[1]);
    		
    		if(u != v) {
    			foret.union(u, v);
    			plist.addLast(e);
    			components -= 1;
    			
    		}
    	}
    	
    	return plist;
    }

    /**
     * Returns a positional list of minimum spanning tree edges for the given graph
     * using Prim-Jarnik's minimum spanning tree algorithm.
     *
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param g the graph for which to compute a minimum spanning tree
     * @return a positional list of minimum spanning tree edges
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
        AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();

        PositionalList<Edge<E>> tree = new PositionalLinkedList<>();

        Vertex<V> src = g.vertices().iterator().next();

        for(Vertex<V> v : g.vertices()) {
            if(v == src) {
                weights.put(v, 0);
            } else {
                weights.put(v, Integer.MAX_VALUE);
            }
            pqEntries.put(v, q.insert(weights.get(v), v));
        }
        while(!q.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = q.deleteMin();
            Vertex<V> u = entry.getValue();
            if(connectingEdges.get(u) != null) {
                tree.addLast(connectingEdges.get(u));
            }
            known.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight();
                if(!known.contains(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    connectingEdges.put(z, e);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return tree;
    }

}
