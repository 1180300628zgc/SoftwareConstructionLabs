package circularOrbit;

import javax.swing.JPanel;

import physicalObject.PhysicalObject;
import physicalObject.Planet;
import track.Track;

public class AtomStructure extends ConcreteCircularOrbit {

	private int numberOfTracks;

	@Override
	public boolean AddObjectToTrack(PhysicalObject physicalObject) {
		Track track = new Track((Planet) physicalObject);
		return this.AddTrack(track);
	}

	@Override
	public void Init(JPanel panel) {
		// List<Track> tmpTrack = SortedTracks();

		// ------------NEED DEBUG------------

		for (int i = 0; i < tracks.size(); i++) {
			for (Track track : tracks) {
				if (track.GetLevel() == i+ 1) {
					track.AtomInit(200 + 50 * i, panel);
				}
			}
		}
	}

	public int GetNumOfTracks() {
		return numberOfTracks;
	}

	public void SetNumOfTracks(int num) {
		this.numberOfTracks = num;
	}

	@Override
	public void Update(JPanel panel) {
		// TODO Auto-generated method stub
		
	}
}
