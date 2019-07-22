import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import extensions.Position;

class PositionTest {

	Position positon = new Position(270, 400);

	@Test
	void DegreeTest() {
		assertEquals(40, positon.GetDegree());
	}
	
	@Test
	void RadiusTest() {
		assertEquals(270, positon.GetRadius());
	}
}
