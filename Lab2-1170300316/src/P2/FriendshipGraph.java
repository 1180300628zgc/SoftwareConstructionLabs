package src.P2;

import java.util.*;

import graph.Graph;

public class FriendshipGraph {

	private int[][] relationGraph = new int[9999][9999];
	private final int INF = 9999;
	private int friendTotal = 0;
	// The graph to save every person
	private Graph<Person> FriendGraph = Graph.empty(new HashSet<Person>());
	private Map<Person, Integer> PersontoInt = new HashMap<Person, Integer>();

	/**
	 * 
	 * 
	 * @param
	 * @return
	 */
	public void initialization() {
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				relationGraph[i][j] = INF; //
		for (int k = 0; k < 100; k++)
			relationGraph[k][k] = 0;
	}

	/**
	 * Build a connectivity map of the people. Only the distance 1 is accounted.
	 */
	public void BasicConnectivityMap() {
		int i = 0;
		for (Person person : FriendGraph.vertices()) {
			PersontoInt.put(person, i);
			i++;
		}

		for (Person person : FriendGraph.vertices()) {
			for (Person person2 : FriendGraph.targets(person).keySet()) {
				relationGraph[PersontoInt.get(person)][PersontoInt.get(person2)] = 1;
			}
		}

		for (int j = 0; j < friendTotal; j++) {
			for (int k = 0; k < friendTotal; k++) {
				if (relationGraph[j][k] != relationGraph[k][j]) {
					relationGraph[j][k] = INF;
					relationGraph[k][j] = INF;
				}
			}
		}
	}

	/**
	 * 
	 * 
	 * @param
	 * @return
	 */
	public boolean addVertex(Person person) {
		if (FriendGraph.add(person)) {
			friendTotal++;
			return true;
		}
		;
		return false;
	}

	/**
	 * Add a relation edge between person 1 and person 2
	 * 
	 * @param person1
	 * @param person2
	 * @return 0 if there was no relation between the two people 1 if there was a
	 *         relation between the two people
	 */
	public boolean addEdge(Person person1, Person person2) {
		if (FriendGraph.set(person1, person2, 1) > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public int getDistance(Person person1, Person person2) {
		if ((relationGraph[PersontoInt.get(person1)][PersontoInt.get(person2)] == INF))
			return -1;
		return relationGraph[PersontoInt.get(person1)][PersontoInt.get(person2)];
	}

	/**
	 * 
	 * 
	 * @param
	 */
	public void floydDistance() {
		for (int i = 0; i < friendTotal; i++)
			for (int j = 0; j < friendTotal; j++)
				for (int k = 0; k < friendTotal; k++)
					relationGraph[i][j] = (relationGraph[i][j] > (relationGraph[i][k] + relationGraph[k][j]) ? (relationGraph[i][k] + relationGraph[k][j]) : relationGraph[i][j]);
	}

	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		// should print 1
		System.out.println(graph.getDistance(rachel, ben));
		// should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		// should print -1
	}
}
