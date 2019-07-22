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
	
	@Test
	void SetPositionTest() {
		Position newposition = new Position(300, 200);
		positon.SetPosition(newposition);
		assertTrue(positon.GetRadius() == 300);
		assertTrue(positon.GetDegree() == 200);
	}
	
	@Test
	void RotateTest() {
		positon.Rotate(20);
		assertTrue(positon.GetDegree() == 60);
	}
	
	@Test
	void isRadiusEqualTest() {
		Position position2 = new Position(270, 400);
		Position position3 = new Position(200, 400);
		assertTrue(positon.isRadiusEqual(position2));
		assertFalse(positon.isRadiusEqual(position3));
	}
	
	@Test
	void isEqualTest() {
		Position position2 = new Position(270, 400);
		Position position3 = new Position(200, 400);
		assertTrue(positon.isEqual(position2));
		assertFalse(positon.isEqual(position3));
	}
	
	@Test
	void GetStandardXTest() {
		assertTrue(positon.GetStandardX() > 0);
	}
	
	@Test
	void GetStandardYTest() {
		assertTrue(positon.GetStandardY() > 0);
	}
}
