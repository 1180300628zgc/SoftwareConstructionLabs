/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    // Add 5 vertices to the graph and test if the 5 vertices 
    // and their source vertices and target vertices have been
    // added
    
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToString() {
    	Graph<String> graph = emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.add("d");
    	graph.add("e");
    	assertEquals("Vertices: [Vertex: a\n" + 
    			"SourceVertex: {}\n" + 
    			"TargetVertex{}, Vertex: b\n" + 
    			"SourceVertex: {}\n" + 
    			"TargetVertex{}, Vertex: c\n" + 
    			"SourceVertex: {}\n" + 
    			"TargetVertex{}, Vertex: d\n" + 
    			"SourceVertex: {}\n" + 
    			"TargetVertex{}, Vertex: e\n" + 
    			"SourceVertex: {}\n" + 
    			"TargetVertex{}]", graph.toString());
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // Testing each method by creating different vertices with different params 
    
    // TODO tests for operations of Vertex
    @Test
    public void testGetVertex() {
    	Vertex vertex1 = new Vertex("a", new HashMap<String, Integer>(), new HashMap<String, Integer>());
    	Vertex vertex2 = new Vertex("b", new HashMap<String, Integer>(), new HashMap<String, Integer>());
    	Vertex vertex3 = new Vertex("c", new HashMap<String, Integer>(), new HashMap<String, Integer>());
    	assertEquals("a", vertex1.getVertex());
    	assertEquals("b", vertex2.getVertex());
    	assertEquals("c", vertex3.getVertex());
    }
    
    @Test
    public void testGetSourceVertex() {
    	Map<String, Integer> MapA = new HashMap<String, Integer>();
    	MapA.put("b", 3);
    	MapA.put("c", 4);
    	Map<String, Integer> MapB = new HashMap<String, Integer>();
    	MapB.put("d", 3);
    	MapB.put("e", 4);
    	Vertex vertex1 = new Vertex("a", MapA, MapB);
    	assertEquals(MapA, vertex1.getSourceVertex());
    }
    
    @Test
    public void testGetTargetVertex() {
    	Map<String, Integer> MapA = new HashMap<String, Integer>();
    	MapA.put("b", 3);
    	MapA.put("c", 4);
    	Map<String, Integer> MapB = new HashMap<String, Integer>();
    	MapB.put("d", 3);
    	MapB.put("e", 4);
    	Vertex vertex1 = new Vertex("a", MapA, MapB);
    	assertEquals(MapB, vertex1.getTargetVertex());
    }
}
