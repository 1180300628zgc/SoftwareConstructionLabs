package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TopVotedCandidateTest {

	@Test
	void ExampleTest() {
		int[] persons = new int[] {0,1,1,0,0,1,0};
		int[] times = new int[] {0,5,10,15,20,25,30};
		TopVotedCandidate tvc = new TopVotedCandidate(persons, times);
		System.out.println(tvc.q(3));
		System.out.println(tvc.q(12));
		System.out.println(tvc.q(25));
		assertTrue(tvc.q(3) == 0);
		assertEquals(tvc.q(3), 0);
		assertEquals(tvc.q(12), 1);
		assertEquals(tvc.q(25), 1);
	}

}
