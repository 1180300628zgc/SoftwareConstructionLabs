/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package extensions;

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
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge> edges = new ArrayList<>();

	// Abstraction function:
	// vertices + edges -> Graph
	// Representation invariant:
	// Edge weights >= 0
	// No two edges share the same source and target
	// edges is an ordered mutable list. Edge is an immutable type
	// vertices is a mutable set. L is immutable.
	// Safety from rep exposure:
	// fields are private and final
	// immutable data types

	// constructor
//	public ConcreteEdgesGraph() {
//		
//	}
//	
//	public ConcreteEdgesGraph(Set<L> vertices, List<Edge<L>> edges){
//        this.vertices.addAll(vertices);
//        this.edges.addAll(edges);
//    }
//	
//	public ConcreteEdgesGraph(List<Edge<L>> edges, Set<L> vertices){
//        this.vertices.addAll(vertices);
//        this.edges.addAll(edges);
//    }

	public ConcreteEdgesGraph(Set<L> vertices){
        this.vertices.addAll(vertices);
    }

	@Override
	public boolean add(L vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int set(L source, L target, int weight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(L vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<L> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// checkRep
	// The weight of each edge must be larger than 0 or equal to 0
//	private void checkRep() {
//		for (Edge edge : edges) {
//			assert edge.getWeight() >= 0;
//		}
//	}
//
//	@Override
//	public boolean add(L vertex) {
//		return vertices.add(vertex);
//	}
//
//	@Override
//	public int set(L source, L target, int weight) {
//		Edge edgeToBeAdded = new Edge(source, target, weight);
//		for (Edge element : edges) {
//			// Update the weight if the edge has already existed.
//			if (element.sameEdge(edgeToBeAdded)) {
//				if (element.getWeight() != 0) {
//					if (edgeToBeAdded.getWeight() == 0) {
//						edges.remove(element);
//						return element.getWeight();
//					}
//
//					edges.remove(element);
//					edges.add(edgeToBeAdded);
//					return element.getWeight();
//				}
//			}
//		}
//		// The edge is totally new.
//		this.add(source);
//		this.add(target);
//		edges.add(edgeToBeAdded);
//		return 0;
//	}
//
//	@Override
//	public boolean remove(L vertex) {
//		if (!vertices.contains(vertex))
//			return false;
//		else {
//			int size = edges.size();
//			for (int i = 0; i < size; i++) {
//				if (edges.get(i).getSource() == vertex || edges.get(i).getTarget() == vertex) {
//					System.out.println("Remove: " + edges.get(i).getSource() + " " + edges.get(i).getTarget());
//					edges.remove(edges.get(i));
//					size--;
//				}
//			}
//			vertices.remove(vertex);
//			return true;
//		}
//	}
//
//	@Override
//	public Set<L> vertices() {
//		return vertices;
//	}
//
//	@Override
//	public Map<L, Integer> sources(L target) {
//		Map<L, Integer> resultMap = new HashMap<L, Integer>();
//		for (Edge element : edges) {
//			if (element.getTarget() == target) {
//				resultMap.put((L) element.getSource(), element.getWeight());
//			}
//		}
//		return resultMap;
//	}
//
//	@Override
//	public Map<L, Integer> targets(L source) {
//		Map<L, Integer> resultMap = new HashMap<L, Integer>();
//		for (Edge element : edges) {
//			if (element.getSource() == source) {
//				resultMap.put((L) element.getTarget(), element.getWeight());
//			}
//		}
//		return resultMap;
//	}
//
//	// TODO toString()
//	@Override
//	public String toString() {
//		return "Vertices: " + vertices + "\nEdges: " + edges;
//	}
//	
	
}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */


class Edge<L> {

	// TODO fields
	private final L source;
	private final L target;
	private final int weight;

	// Abstraction function:
	// source -> source vertex of the edge
	// target -> target point of the edge
	// weight -> the weight of the edge

	// Representation invariant:
	// weight >= 0

	// Safety from rep exposure:
	// Set the RIs to private final data type
	// Edge is set to be an immutable data type

	// TODO constructor
	public Edge(L startPoint, L endPoint, int weight) {
		this.source = startPoint;
		this.target = endPoint;
		this.weight = weight;
	}

	// TODO checkRep
	private void checkRep() {
		assert (weight >= 0);
	}

	// TODO methods
	/**
	 * @return the weight of an edge
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the source vertex of an edge
	 */
	public L getSource() {
		return source;
	}

	/**
	 * @return the target vertex of an edge
	 */
	public L getTarget() {
		return target;
	}

	/**
	 * Judge if it is the same edge
	 * 
	 * @param Edge
	 * @return true for the same edge��false for the different edge
	 */
	public boolean sameEdge(Edge edge) {
		if (edge.getSource() == this.source && edge.getTarget() == this.target) {
			return true;
		}
		return false;
	}
}
