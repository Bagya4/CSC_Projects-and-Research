package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for GraphTraversals- dfs and bfs
 *
 * @author Bagya Maharajan
 *
 */
public class GraphTraversalUtilTest {
	
	/** undirected graph */
    private Graph<String, Integer> undirectedGraph;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Integer>();
        
    }

    
    /**
     * testing depth first search
     * @param <E> edge object
     * @param <V> vertex object
     */
    @Test
    public <E, V> void dfs() {
    	//building undirected sample
    	Vertex<String> v1 = undirectedGraph.insertVertex("A");
        Vertex<String> v2 = undirectedGraph.insertVertex("B");
        Vertex<String> v3 = undirectedGraph.insertVertex("C");
        Vertex<String> v4 = undirectedGraph.insertVertex("D");
        Vertex<String> v5 = undirectedGraph.insertVertex("E");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
    	Map<Vertex<String>, Edge<Integer>> map = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
    	
    	assertEquals(map.size(), 4);
    	Iterator<Entry<Vertex<String>, Edge<Integer>>> it = map.entrySet().iterator();
    	for(int i = 0; i < map.size(); i++) {
    		if(it.next().getKey().getElement().equals("A"))
    			fail(); //vertex A should not be discovered again
    	}	
    }

    /**
     * testing breadth first search
     * @param <E> edge object
     * @param <V> vertex object
     */
    @Test
    public <E, V> void bfs() {
    	//building undirected sample
    	Vertex<String> v1 = undirectedGraph.insertVertex("A");
        Vertex<String> v2 = undirectedGraph.insertVertex("B");
        Vertex<String> v3 = undirectedGraph.insertVertex("C");
        Vertex<String> v4 = undirectedGraph.insertVertex("D");
        Vertex<String> v5 = undirectedGraph.insertVertex("E");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        new GraphTraversalUtil();
    	Map<Vertex<String>, Edge<Integer>> map = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
    	
    	assertEquals(map.size(), 4);
    	Iterator<Entry<Vertex<String>, Edge<Integer>>> it = map.entrySet().iterator();
    	for(int i = 0; i < map.size(); i++) {
    		if(it.next().getKey().getElement().equals("A"))
    			fail(); //vetex A should not be discovered again
    	}	
    }
}
