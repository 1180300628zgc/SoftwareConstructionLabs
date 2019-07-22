/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
	 */
	@Override
	public Graph<String> emptyInstance() {
		return new ConcreteEdgesGraph();
	}

	/*
	 * Testing ConcreteEdgesGraph...
	 */

	// Testing strategy for ConcreteEdgesGraph.toString()
    // Add 5 vertices to the graph and test if the 5 vertices 
    // and their source vertices and target vertices have been
    // added

	// TODO tests for ConcreteEdgesGraph.toString()
	@Test
	public void testToString() {
		Graph<String> graph = emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.add("d");
    	graph.add("e");
    	assertEquals("Vertices: [a, b, c, d, e]\n" + 
    			"Edges: []", graph.toString());
	}
	
	/*
	 * Testing Edge...
	 */

	// Testing strategy for Edge
	// Testing each method by creating different edges with different params 

	// TODO tests for operations of Edge
	@Test
	public void testGetWeight() {
		Edge edge1 = new Edge("a", "b", 1);
		Edge edge2 = new Edge("b", "c", 2);
		Edge edge3 = new Edge("c", "a", 3);
		assertEquals(1, edge1.getWeight());
		assertEquals(2, edge2.getWeight());
		assertEquals(3, edge3.getWeight());
	}
	
	@Test
	public void testGetSource() {
		Edge edge1 = new Edge("a", "b", 1);
		Edge edge2 = new Edge("b", "c", 2);
		Edge edge3 = new Edge("c", "a", 3);
		assertEquals("a", edge1.getSource());
		assertEquals("b", edge2.getSource());
		assertEquals("c", edge3.getSource());
	}
	
	@Test
	public void testGetTarget() {
		Edge edge1 = new Edge("a", "b", 1);
		Edge edge2 = new Edge("b", "c", 2);
		Edge edge3 = new Edge("c", "a", 3);
		assertEquals("b", edge1.getTarget());
		assertEquals("c", edge2.getTarget());
		assertEquals("a", edge3.getTarget());
	}
	
	@Test
	public void testSameEdge() {
		Edge edge1 = new Edge("a", "b", 1);
		Edge edge2 = new Edge("b", "c", 2);
		Edge edge3 = new Edge("c", "a", 3);
		Edge edge4 = new Edge("a", "b", 3);
		assertTrue(edge1.sameEdge(edge4));
		assertTrue(edge4.sameEdge(edge1));
		assertFalse(edge1.sameEdge(edge3));
		assertFalse(edge2.sameEdge(edge3));
	}
}
