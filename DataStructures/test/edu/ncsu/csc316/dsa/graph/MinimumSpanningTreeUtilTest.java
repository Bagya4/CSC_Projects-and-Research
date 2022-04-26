package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for Shortestpath. Tests dijkstra's algorithm and shortest path tree
 *
 * @author Bagya Maharajan
 *
 */
public class MinimumSpanningTreeUtilTest {
	
	/** undirected graph */
    private Graph<String, Highway> undirectedGraph;

    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Highway>();
    }

    /**
     * An object-highway used as edges
     * @author Bagya Maharajan
     *
     */
    public class Highway implements Weighted {
        
        /** length of highway in km */
        private int length;
        
        /**
         * Constructs a highway
         * @param l length
         */
        public Highway(int l) {
            setLength(l);
        }


        /**
         * Gets highway length
         * @return length
         */
        public int getLength() {
            return length;
        }

        /**
		 * Sets highway length
		 * @param length of highway
		 */
        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
    
    /**
     * testing Dijkstra's algorithm on an undirected graph
     */
    @Test
    public void kruskal() {
    	//building undirected sample
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
		undirectedGraph.insertEdge(v1, v2, new Highway(5));
		undirectedGraph.insertEdge(v1, v3, new Highway(10));
		undirectedGraph.insertEdge(v1, v4, new Highway(15));
		undirectedGraph.insertEdge(v1, v5, new Highway(20));
		undirectedGraph.insertEdge(v2, v3, new Highway(25));
		undirectedGraph.insertEdge(v2, v4, new Highway(30));
		undirectedGraph.insertEdge(v2, v5, new Highway(35));
		undirectedGraph.insertEdge(v3, v4, new Highway(40));
		undirectedGraph.insertEdge(v3, v5, new Highway(45));
		undirectedGraph.insertEdge(v4, v5, new Highway(50));
		
        new MinimumSpanningTreeUtil();
		PositionalList<Edge<Highway>> edgeList = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
		Iterator<Edge<Highway>> it = edgeList.iterator();
		assertEquals(5, it.next().getElement().getLength());  //v1 to v2
		assertEquals(10, it.next().getElement().getLength()); //   to v3
		assertEquals(15, it.next().getElement().getLength()); //   to v4
		assertEquals(20, it.next().getElement().getLength()); //   to v5
		assertFalse(it.hasNext());

    }

    /**
     * Testing the shortest path tree
     */
    @Test
    public void primJarnik() {
    	//building undirected sample
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
		Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
		Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
		Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
		Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
		undirectedGraph.insertEdge(v1, v2, new Highway(5));
		undirectedGraph.insertEdge(v1, v3, new Highway(10));
		undirectedGraph.insertEdge(v1, v4, new Highway(15));
		undirectedGraph.insertEdge(v1, v5, new Highway(20));
		undirectedGraph.insertEdge(v2, v3, new Highway(25));
		undirectedGraph.insertEdge(v2, v4, new Highway(30));
		undirectedGraph.insertEdge(v2, v5, new Highway(35));
		undirectedGraph.insertEdge(v3, v4, new Highway(40));
		undirectedGraph.insertEdge(v3, v5, new Highway(45));
		undirectedGraph.insertEdge(v4, v5, new Highway(50));

		PositionalList<Edge<Highway>> edgeList = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
		Iterator<Edge<Highway>> it = edgeList.iterator();
		assertEquals(5, it.next().getElement().getLength());  //v1 to v2
		assertEquals(10, it.next().getElement().getLength()); //   to v3
		assertEquals(15, it.next().getElement().getLength()); //   to v4
		assertEquals(20, it.next().getElement().getLength()); //   to v5
		assertFalse(it.hasNext());
    	
    }
}
