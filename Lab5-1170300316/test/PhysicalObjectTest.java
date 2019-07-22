import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import physicalObject.Friend;
import physicalObject.PhysicalObject;

class PhysicalObjectTest {

	PhysicalObject obj = new Friend("name", 12, "M");
	
	@Test
	void TestName() {
		assertTrue(obj.GetName().equals("name"));
	}
	
}
