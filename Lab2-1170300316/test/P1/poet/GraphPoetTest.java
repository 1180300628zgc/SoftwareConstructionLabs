/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

import graph.Graph;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Test Strategy:
    // Build my own corpus and test it with several short poems.
    
	private final File corpus = new File("test/P1/poet/ChellCorpus.txt");
	private final String toBeInserted = new String("Write whatever you want.");
	private final File corpusshort = new File("test/P1/poet/corpusshort.txt");
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraphPoet() throws IOException {
    	GraphPoet graphPoet = new GraphPoet(corpus);
    	
    }
    
    @Test
    public void testGetRevisedPoem() throws IOException {
    	GraphPoet graphPoet = new GraphPoet(corpus);
    	assertTrue(graphPoet.GetRevisedPoet("Write whatever you want.").equals("Write to whatever you want. "));
    }
    
    @Test
    public void testPoem() throws IOException {
    	GraphPoet graphPoet = new GraphPoet(corpus);
    	assertTrue(graphPoet.poem("Write whatever you want.", graphPoet.GenerateGraph("Write to whatever you love\n" + 
    			"No matter it is a piece of paper\n" + 
    			"Or the best video game you endove")).equals("Write to whatever you want. "));
    }
    
    @Test
    public void testGenerateGraph() throws IOException {
    	Graph<String> graph = Graph.empty(new HashSet<String>());
    	graph.set("write", "to", 1);
    	graph.set("to", "whatever", 1);
    	GraphPoet graphPoet = new GraphPoet(corpusshort);
    	assertEquals(graph.vertices(), graphPoet.GenerateGraph("write to whatever").vertices());
    }
}
