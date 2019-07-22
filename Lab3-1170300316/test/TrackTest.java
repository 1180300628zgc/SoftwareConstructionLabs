import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import physicalObject.Friend;
import track.Track;

public class TrackTest {
	Track track = new Track(1);
	Friend a = new Friend();
	Friend b = new Friend();
	
	@Test
	public void TestAdd() {
		assertTrue(track.AddFriendObject(a));
	}
	
	@Test
	public void TestSize() {
		assertEquals(0, track.GetFriendObject().size());
	}
}
