/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    // Implement different graphs with different designs that cover different
	// situations.
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    @Test
    public void testStringAdd() {
    	Graph<String> graph = emptyInstance();
    	assertTrue(graph.add("thefirstvertex"));
    	assertTrue(graph.add("thesecondvertex"));
    	assertTrue(graph.add("thethirdvertex"));
    	assertFalse(graph.add("thesecondvertex"));
    	assertFalse(graph.add("thethirdvertex"));
    }
    
    @Test
    public void testStringSet() {
    	Graph<String> graph = emptyInstance();
    	graph.set("a", "b", 2);
    	graph.set("b", "c", 3);
    	graph.set("c", "d", 4);
    	assertEquals(2, graph.set("a", "b", 5));
    	assertEquals(3, graph.set("b", "c", 0));
    	assertEquals(0, graph.set("d", "c", 6));
    	assertEquals(6, graph.set("d", "c", 6));
    }
    
    @Test
    public void testStringRemove() {
    	Graph<String> graph = emptyInstance();
    	graph.set("a", "b", 2);
    	graph.set("b", "c", 3);
    	graph.set("c", "d", 4);
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.add("d");
    	assertTrue(graph.remove("a"));
    	assertFalse(graph.remove("e"));
    }
    
    @Test
    public void testStringVertices() {
    	Graph<String> graph = emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.add("d");
    	Set<String> set = new HashSet<String>();
    	set.add("a");
    	set.add("b");
    	set.add("c");
    	set.add("d");
    	assertEquals(graph.vertices(), set);
    }
    
    
    @Test
    public void testStringSources() {
    	Graph<String> graph = emptyInstance();
    	graph.set("a", "b", 2);
    	graph.set("b", "c", 3);
    	graph.set("c", "d", 4);
    	graph.set("d", "c", 5);
    	graph.set("e", "c", 6);
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("b", 3);
    	map.put("d", 5);
    	map.put("e", 6);
    	assertEquals(graph.sources("c"), map); //c=6?
    }
    
    @Test
    public void testStringTargets() {
    	Graph<String> graph = emptyInstance();
    	graph.set("a", "b", 2);
    	graph.set("b", "c", 3);
    	graph.set("c", "d", 4);
    	graph.set("c", "e", 5);
    	graph.set("c", "f", 6);
    	graph.set("c", "a", 7);
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("d", 4); //pass
    	map.put("e", 5); //pass
    	map.put("f", 6); //pass
    	map.put("a", 7);
    	assertEquals(graph.targets("c"), map);
    }
    // TODO other tests for instance methods of Graph
    
}
