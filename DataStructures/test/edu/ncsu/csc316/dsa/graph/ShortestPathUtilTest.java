package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for Shortestpath. Tests dijkstra's algorithm and shortest path tree
 *
 * @author Bagya Maharajan
 *
 */
public class ShortestPathUtilTest {
	
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
    public void dijkstra() {
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
		
        new ShortestPathUtil();
		Map<Vertex<String>, Integer> m = ShortestPathUtil.dijkstra(undirectedGraph, v1);
		//assertEquals(0, m.get(v1));
		assertEquals((Integer) 0, m.get(v1));
		assertEquals((Integer) 5, m.get(v2));
		assertEquals((Integer) 10, m.get(v3));
		assertEquals((Integer) 15, m.get(v4));
		assertEquals((Integer) 20, m.get(v5));
		
		Map<Vertex<String>, Integer> m2 = ShortestPathUtil.dijkstra(undirectedGraph, v4);
		assertEquals((Integer) 35, m2.get(v5)); //v4 to v1 to v5 is shorter than v4 to v5

    }

    /**
     * Testing the shortest path tree
     */
    @Test
    public void shortestPathTree() {
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

		Map<Vertex<String>, Integer> m = ShortestPathUtil.dijkstra(undirectedGraph, v1);
		Map<Vertex<String>, Edge<Highway>> mpath = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, m);

		assertEquals(5, mpath.get(v2).getElement().getLength());
		assertEquals(10, mpath.get(v3).getElement().getLength());
		assertEquals(15, mpath.get(v4).getElement().getLength());
		assertEquals(20, mpath.get(v5).getElement().getLength());
		
		assertNull(mpath.get(v1));
    	
    }
}
