package circularOrbit;

import java.util.*;

import javax.swing.JPanel;

import centralObject.CentralObject;
import extensions.Position;
import physicalObject.Atom;
import physicalObject.Friend;
import physicalObject.PhysicalObject;
import physicalObject.Planet;
import track.Track;

public interface CircularOrbit<L, E> {
	
	// L represents the CentralObject and R represents the Track
	
	public static CircularOrbit<CentralObject, Track> emptyStellarSystem() {
		return new StellarSystem();
	}
	
	public static CircularOrbit<CentralObject, Track> emptyAtomStructure() {
		return new AtomStructure();
	}
	
	public static CircularOrbit<CentralObject, Track> emptySocialNetworkCircle() {
		return new SocialNetworkCircle();
	}
	/**
	 * Add a new orbit
	 * @return true if successfully added while false if failed to add
	 */
	public boolean AddTrack(Track track);
	
	/**
	 * Remove an existed orbit
	 * @return true if successfully removed while false if failed to find the orbit
	 */
	public boolean RemoveTrack(Track track);
	
	public boolean AddObjectToCentre(CentralObject centralObject);
	
	/**
	 * TO BE FIXED
	 * @param physicalObject
	 * @return
	 */
	public boolean AddObjectToTrack(PhysicalObject physicalObject);
	
	public boolean AddRelationBetweenCentreAndTrack();
	
	public boolean AddRelationBetweenTwoTracks();
	
	/**
	 * Get all the track of a circular orbit.
	 * WARNING: be careful with the change of the set
	 * @return the set of the tracks
	 */
	public Set<Track> GetTrack();

	public Set<Position> getPositions();
	
	public Set<Double> getRadius();
	
	public void FramePosRefresh();
	
	public Track[] SortedTracks();
	
	public void Init(JPanel panel);

	boolean AddObjectToTrack(Planet physicalObject);

	boolean AddObjectToTrack(Atom physicalObject, Track track);
	
	public int GetNumOfTracks();
	
	public void SetNumOfTracks(int num);
	
	public boolean NewFriend(Friend friend);
	
	public Set<Friend> GetFriends();
	
	public void Update(JPanel panel);
}
