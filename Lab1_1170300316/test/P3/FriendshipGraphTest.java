
package P3;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class FriendshipGraphTest {

	/**
	 * Tests that assertions are enabled.
	 */
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}

	@Test
	public void addVertexTest() {
		FriendshipGraph graph = new FriendshipGraph();
		graph.initialization(graph);
		Person chell = new Person("Chell");
		Person ted = new Person("Ted");
		Person landon = new Person("Landon");
		Person arthur = new Person("Arthur");
		Person fantom = new Person("Fantom");
		Person anyoneelse = new Person("Anyoneelse");
		graph.addVertex(chell);
		assertEquals(true, graph.friendName.contains(chell));
		graph.addVertex(ted);
		assertEquals(true, graph.friendName.contains(ted));
		assertEquals(false, graph.friendName.contains(fantom));
		graph.addVertex(landon);
		assertEquals(true, graph.friendName.contains(landon));
		graph.addVertex(arthur);
		assertEquals(true, graph.friendName.contains(arthur));
		graph.addVertex(fantom);
		assertEquals(false, graph.friendName.contains(anyoneelse));
	}

	@Test
	public void addEdgeTest() {
		FriendshipGraph graph = new FriendshipGraph();
		graph.initialization(graph);
		Person chell = new Person("Chell");
		Person ted = new Person("Ted");
		Person landon = new Person("Landon");
		Person arthur = new Person("Arthur");
		Person fantom = new Person("Fantom");
		Person anyoneelse = new Person("Anyoneelse");
		graph.addVertex(chell);
		graph.addVertex(ted);
		graph.addVertex(landon);
		graph.addVertex(arthur);
		graph.addVertex(fantom);
		assertFalse(graph.addEdge(chell, ted));
		assertFalse(graph.addEdge(ted, landon));
		assertTrue(graph.addEdge(landon, ted));
		assertFalse(graph.addEdge(landon, arthur));
		assertFalse(graph.addEdge(arthur, fantom));
		assertTrue(graph.addEdge(fantom, arthur));
	}

	@Test
	public void getDistanceTest() {
		FriendshipGraph graph = new FriendshipGraph();
		graph.initialization(graph);
		Person chell = new Person("Chell");
		Person ted = new Person("Ted");
		Person landon = new Person("Landon");
		Person arthur = new Person("Arthur");
		Person fantom = new Person("Fantom");
		Person anyoneelse = new Person("Anyoneelse");
		graph.addVertex(chell);
		graph.addVertex(ted);
		graph.addVertex(landon);
		graph.addVertex(arthur);
		graph.addVertex(fantom);
		graph.addEdge(chell, ted);
		graph.addEdge(chell, fantom);
		graph.addEdge(ted, landon);
		graph.addEdge(landon, ted);
		graph.addEdge(landon, arthur);
		graph.addEdge(arthur, fantom);
		graph.addEdge(fantom, arthur);
		graph.addEdge(fantom, chell);
		graph.floydDistance(graph.relationGraph);
		assertEquals(-1, graph.getDistance(chell, ted));
		assertEquals(-1, graph.getDistance(ted, chell));
		assertEquals(1, graph.getDistance(ted, landon));
		assertEquals(0, graph.getDistance(arthur, arthur));
		assertEquals(2, graph.getDistance(arthur, chell));
	}

}