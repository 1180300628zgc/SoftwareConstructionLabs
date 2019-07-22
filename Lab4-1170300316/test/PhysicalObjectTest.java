import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import extensions.Position;
import physicalObject.Friend;
import physicalObject.PhysicalObject;

class PhysicalObjectTest {

	PhysicalObject obj = new Friend("name", 12, "M");
	
	@Test
	void TestNameFunction() {
		assertTrue(obj.GetName().equals("name"));
		obj.SetName("this is a new name");
		assertTrue(obj.GetName().equals("this is a new name"));
	}
	
	@Test
	void TestSetPosition() {
		Position position= new Position(1.0, 2.5);
		obj.SetPosition(position);
		assertTrue(obj.GetPosition().GetRadius() == 1.0);
		assertTrue(obj.GetPosition().GetDegree() == 2.5);
		assertFalse(obj.SetPosition(position));
	}
	
	@Test
	void TestisEqual() {
		PhysicalObject obj2 = new Friend("name", 12, "M");
		assertTrue(obj.isEqual(obj2));
		PhysicalObject obj3 = new Friend("name2", 12, "M");
		assertFalse(obj.isEqual(obj3));
	}
	
	@Test
	void TestEqual() {
		PhysicalObject obj2 = new Friend("name", 12, "M");
		assertTrue(obj.equals(obj2));
		PhysicalObject obj3 = new Friend("name2", 12, "M");
		assertFalse(obj.equals(obj3));
	}
}
