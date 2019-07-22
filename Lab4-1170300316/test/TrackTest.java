import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import physicalObject.Friend;
import physicalObject.Planet;
import track.Track;

public class TrackTest {
	Track track = new Track(1);
	Friend a = new Friend();
	Friend b = new Friend();
	Planet planet = new Planet("Earth", "Solid", "Blue", 6378.137, 1.49e8, 29.783, "CW", 0);
	
	@Test
	public void TestSize() {
		assertEquals(0, track.GetFriendObject().size());
	}
	
	@Test
	public void NewPlanetTrack() {
		Track planetTrack = new Track(planet);
		Track atomTrack = new Track(1);
		Track trackTrack = new Track(track);
		assertTrue(planetTrack.GetName().equals(planet.GetName()));
		assertTrue(planetTrack.GetRadius() == 1.49e8);
		assertTrue(atomTrack.GetLevel() == 1);
		atomTrack.SetLevel(2);
		assertTrue(atomTrack.GetLevel() == 2);
		assertTrue(planetTrack.SetRadius(8000));
		assertTrue(planetTrack.SetRadius(9000));
	}
}
