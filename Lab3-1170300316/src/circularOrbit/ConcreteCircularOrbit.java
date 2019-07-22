package circularOrbit;

import java.util.*;

import javax.swing.JPanel;

import centralObject.CentralObject;
import centralObject.EmptyCentralObject;
import extensions.Position;
import physicalObject.Atom;
import physicalObject.Friend;
import physicalObject.PhysicalObject;
import physicalObject.Planet;
import track.Track;

public abstract class ConcreteCircularOrbit implements CircularOrbit<CentralObject, Track> {

	protected Set<Track> tracks = new HashSet<Track>();
	protected CentralObject centralObject;
	
	protected Set<Friend> friends = new HashSet<Friend>();  
	
//	public CircularOrbit<CentralObject, Track> emptyStellarSystem() {
//		return new StellarSystem();
//		
//	}

	public abstract void Init(JPanel panel);
	
	@Override
	public boolean AddTrack(Track track) {
		return tracks.add(track);
	}

	@Override
	public boolean RemoveTrack(Track track) {
		return tracks.remove(track);
	}

	@Override
	public boolean AddObjectToCentre(CentralObject centralObject) {
		if (centralObject.isEqual(this.centralObject)) {
			return false;
		}
		this.centralObject = centralObject;
		return true;
	}

	@Override
	public boolean AddObjectToTrack(Planet physicalObject) {
		Track track = new Track(physicalObject);
		return this.AddTrack(track);
	}
	
	@Override
	public boolean AddObjectToTrack(Atom physicalObject, Track track) {
		track.AddAtomObject(physicalObject);
		return true;
	}

	@Override
	public Set<Track> GetTrack() {
		return tracks;
	}

	@Override
	public Set<Position> getPositions() {
		Set<Position> result = new HashSet<Position>();
		for (Track track : tracks) {
			for (PhysicalObject planet : track.getPhysicalObjects()) {
				result.add(planet.GetPosition());
			}
		}
		return result;
	}

	@Override
	public Set<Double> getRadius() {
		Set<Double> result = new HashSet<Double>();
		for (Track track : tracks) {
			result.add(track.GetRadius());
		}
		return result;
	}

	@Override
	public void FramePosRefresh() {
		// TODO Auto-generated method stub
	}

	// -------------To be implemented--------------//
	@Override
	public boolean AddRelationBetweenCentreAndTrack() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean AddRelationBetweenTwoTracks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Track[] SortedTracks() {
		
		//List<Track> result = new ArrayList<Track>();
		
		//Comparing part
		
		Comparator<Track> c = new MyComparator();
		Track[] tracksArray = new Track[tracks.size()];
		int i = 0;
		//BUGS BELOW
		for (Track track : tracks) {
			tracksArray[i] = new Track(track);
			i++;
		}
		Arrays.sort(tracksArray, c);
		
		for (int j = 0; j < tracks.size(); j++) {
			System.out.println("RANK: " + tracksArray[j].GetName());
		}
		
		return tracksArray;
	}

	@Override
	public boolean NewFriend(Friend friend) {
		return friends.add(friend);
	}
	
	@Override
	public Set<Friend> GetFriends(){
		return this.friends;
	}
}

/**
 * Revise the comparator function to rank the radius of the tracks. From smallest to the largest.
 * @author tony
 *
 */
class MyComparator implements Comparator<Track> {
	@Override
	public int compare(Track o1, Track o2) {
		if (o1.GetRadius() < o2.GetRadius()) {
			return -1;
		} else if (o1.GetRadius() > o2.GetRadius()) {
			return 1;
		} else {
			return 0;
		}
	}
}
