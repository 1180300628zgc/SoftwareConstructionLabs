/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

import graph.Graph;
// import jdk.jfr.internal.PrivateAccess;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

	private final Graph<String> graph = Graph.empty(new HashSet<String>());
	private String lines = "";
	// Abstraction function:
	// Build a word graph for corpus. Corpus File -> Word Graph
	// Decide whether to insert a word in given string with the help
	// of word graph. String + Word Graph -> New Poem String
	// Representation invariant:
	// A graph which saves the word graph generated from corpus file
	// Safety from rep exposure:
	// Set all rep to private
	// Copy a mutable variable when exposed

	/**
	 * Create a new poet with the graph from corpus (as described above).
	 * 
	 * @param corpus text file from which to derive the poet's affinity graph
	 * @throws IOException if the corpus file cannot be found or read
	 */
	public GraphPoet(File corpus) throws IOException {
		// Read the corpus file
		FileReader inputFileReader = new FileReader(corpus);
		BufferedReader br = new BufferedReader(inputFileReader);
		
		String line;
		while ((line = br.readLine()) != null) {
			this.lines = lines.concat(line + " ");
		}
		br.close();
		
	}

	// checkRep
	// Input lines should not be empty
	private void checkRep() {
		assert !lines.isEmpty();
	}

	public String GetRevisedPoet(String input) {
		String toBeInserted = new String(input);
		GenerateGraph(lines);
		return poem(toBeInserted, graph);
	}
	/**
	 * Generate a poem.
	 * 
	 * @param input string from which to create the poem and the word graph of the
	 *              corpus
	 * @return poem (as described above)
	 */
	public String poem(String input, Graph<String> graph) {

		int flag = 0;

		String[] wordsInInput = input.split(" ");
		List<String> wordsInInputList = new ArrayList<String>();
		for (String string : wordsInInput) {
			wordsInInputList.add(string);
		}

		// System.out.println("Origin List: " + wordsInInputList);
		for (int i = 0; i < wordsInInput.length - 1; i++) {
			for (String elementString : graph.vertices()) {
				for (String elementString2 : graph.vertices()) {
					for (String elementString3 : graph.vertices()) {
						if (wordsInInput[i].toLowerCase().equals(elementString)
								&& wordsInInput[i + 1].toLowerCase().equals(elementString3)) {
							// System.out.println("same: " + elementString);
							if (graph.targets(elementString).containsKey(elementString2)
									&& graph.sources(elementString3).containsKey(elementString2)) {
								wordsInInputList.add(i + 1 + flag, elementString2);
								flag++;
							}
						}
					}
				}
			}
		}

		String resultString = "";
		for (String string : wordsInInputList) {
			resultString = resultString.concat(string + " ");
		}

		return resultString;
	}

	/**
	 * Generate a word graph of the corpus
	 * 
	 * @param corpus
	 * @return the word graph of the corpus
	 */
	public Graph<String> GenerateGraph(String corpus) {
		String[] wordsInCorpus = corpus.split(" ");
		for (int i = 0; i < wordsInCorpus.length - 1; i++) {
			int originWeight = graph.set(wordsInCorpus[i].toLowerCase(), wordsInCorpus[i + 1].toLowerCase(), 1);
			graph.set(wordsInCorpus[i].toLowerCase(), wordsInCorpus[i + 1].toLowerCase(), originWeight + 1);
		}

		return graph;
	}

	// TODO toString() /**

}
