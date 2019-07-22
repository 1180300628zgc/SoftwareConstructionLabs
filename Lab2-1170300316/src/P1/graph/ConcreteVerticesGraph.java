/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex> vertices = new ArrayList<>();

	// Abstraction function:
	// A List of Vertex -> Graph
	// Representation invariant:
	// No two vertices share the same label
	// vertices is a mutable List with mutable data type Vertex
	// Safety from rep exposure:
	// fields are private and final
	// safe mutator for the mutable data type is provided

	// constructor
	public ConcreteVerticesGraph() {
		
	}
	// checkRep
	// No repetitive vertex
	public void checkRep() {
		for (Vertex vertex : vertices) {
			for (Vertex vertex2 : vertices) {
				assert vertex.getVertex() != vertex2.getVertex();
			}
		}
	}
	
	@Override
	public boolean add(L vertex) {
		Vertex result = new Vertex(vertex, new HashMap<L, Integer>(), new HashMap<L, Integer>());
		for (Vertex element : vertices) {
			if (element.getVertex() == vertex) {
				return false;
			}
		}
		vertices.add(result);
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		int oldWeight = 0;
		for (Vertex vertex : vertices) {
			if (vertex.getVertex() == source) {
				oldWeight = vertex.addTargetVertex(target, weight);
				// return oldWeight;
			}
			if (vertex.getVertex() == target) {
				oldWeight = vertex.addSourceVertex(source, weight);
				// return oldWeight;
			}
		}
		this.add(source);
		this.add(target);
		for (Vertex vertex : vertices) {
			if (vertex.getVertex() == source) {
				vertex.addTargetVertex(target, weight);
			}
			if (vertex.getVertex() == target) {
				vertex.addSourceVertex(source, weight);
			}
		}
		return oldWeight;
	}

	@Override
	public boolean remove(L vertex) {
		for (Vertex element : vertices) {
			if (element.getVertex() == vertex) {
				vertices.remove(element);
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<L> vertices() {
		Set<L> Result = new HashSet<L>();
		for (Vertex element : vertices) {
			Result.add((L) element.getVertex());
		}
		return Result;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		for (Vertex element : vertices) {
			if (element.getVertex() == target) {
				return new HashMap<L, Integer>(element.getSourceVertex());
			}
		}
		return new HashMap<L, Integer>();
	}

	@Override
	public Map<L, Integer> targets(L source) {
		for (Vertex element : vertices) {
			if (element.getVertex() == source) {
				return new HashMap<L, Integer>(element.getTargetVertex());
			}
		}
		return new HashMap<L, Integer>();
	}

	// TODO toString()
	@Override
	public String toString() {
		return "Vertices: " + vertices;
	}
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// TODO fields
	private L vertex;
	private Map<L, Integer> SourceVertex = new HashMap<L, Integer>();
	private Map<L, Integer> TargetVertex = new HashMap<L, Integer>();

	// Abstraction function:
	// SourceVertex -> Points pointing to the vertex
	// TargetVertex -> Points the vertex pointing to
	// vertex -> the label of the vertex

	// Representation invariant:
	// The L(s) in vertex and other maps should be legal L types.
	// No NULL variant is acceptable.

	// Safety from rep exposure:
	// Only addSourceVertex and addTargetVertex are available to change RIs.
	// When passing Map<> params to the constructor, a new Map<> is created to pass
	// the params to the internal variants.

	// TODO constructor
	public Vertex(L vertex, Map<L, Integer> SourceVertex, Map<L, Integer> TargetVertex) {
		this.vertex = vertex;
		this.SourceVertex = new HashMap<L, Integer>(SourceVertex);
		this.TargetVertex = new HashMap<L, Integer>(TargetVertex);
	}

	// TODO checkRep
	public void checkRep() {
		assert this.vertex != null;
	}

	// TODO methods
	/**
	 * Add the source vertices to the vertex. If the vertex to add has already
	 * existed, change the weight according to the new weight.
	 * 
	 * @param source vertex to add
	 * @param weight of the new edge
	 * @return the weight of the old edge and 0 if the edge is newly added
	 */
	public int addSourceVertex(L vertex, int weight) {
		int oldWeight = 0;
		if (this.SourceVertex.containsKey(vertex)) {
			if (this.SourceVertex.get(vertex) != weight) {
				oldWeight = this.SourceVertex.get(vertex);
				this.SourceVertex.put(vertex, weight);
				return oldWeight;
			} else {
				return this.SourceVertex.get(vertex);
			}
		} else {
			this.SourceVertex.put(vertex, weight);
			return 0;
		}
	}

	/**
	 * Add the target vertices to the vertex. If the vertex to add has already
	 * existed, change the weight according to the new weight.
	 * 
	 * @param target vertex to add
	 * @param weight of the new edge
	 * @return the weight of the old edge and 0 if the edge is newly added
	 */
	public int addTargetVertex(L vertex, int weight) {
		int oldWeight = 0;
		if (this.TargetVertex.containsKey(vertex)) {
			if (this.TargetVertex.get(vertex) != weight) {
				oldWeight = this.TargetVertex.get(vertex);
				this.TargetVertex.put(vertex, weight);
				return oldWeight;
			} else {
				return this.TargetVertex.get(vertex);
			}
		} else {
			this.TargetVertex.put(vertex, weight);
			return 0;
		}
	}

	/**
	 * Get Vertex
	 * 
	 * @return the name of the vertex
	 */
	public L getVertex() {
		return this.vertex;
	}

	/**
	 * Get Source Vertex
	 * 
	 * @return vertices that point to the vertex and the weight of these edges.
	 */
	public Map<L, Integer> getSourceVertex() {
		return new HashMap<L, Integer>(this.SourceVertex);
	}

	/**
	 * Get Target Vertex
	 * 
	 * @return vertices that the vertex points to and the weight of these edges.
	 */
	public Map<L, Integer> getTargetVertex() {
		return new HashMap<L, Integer>(this.TargetVertex);
	}

	// TODO toString()
	public String toString() {
		return "Vertex: " + vertex + "\nSourceVertex: " + SourceVertex + "\nTargetVertex" + TargetVertex;
	}
}
