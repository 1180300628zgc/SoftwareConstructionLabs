import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import centralObject.CentralObject;
import centralObject.Stellar;
import circularOrbit.StellarSystem;

class CentralObjectTest {
	
	@Test
	public void GetNameTest() {
		CentralObject co = new Stellar("Sun", 32334, 2342);
		assertTrue(co.GetName().equals("Sun"));
	}
	
	@Test
	public void isEqualTest() {
		CentralObject co = new Stellar("Sun", 32334, 2342);
		CentralObject bo = new Stellar("Sun", 32334, 2342);
		assertTrue(co.isEqual(bo));
	}
}
