package circularOrbit;

import java.util.List;
import java.util.Set;

import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.JPanel;

import centralObject.CentralObject;
import extensions.Position;
import physicalObject.PhysicalObject;
import physicalObject.Planet;
import track.Track;

public class StellarSystem extends ConcreteCircularOrbit {

	/**
	 * 
	 * @return
	 */
	public int GetSizeOfTracks() {
		return tracks.size();
	}

	public void Update() {

	}

	public void Init(JPanel panel) {
		//List<Track> tmpTrack = SortedTracks();
		
		//------------NEED DEBUG------------
		
		Track[] sortedTracks = new Track[tracks.size()];
		//SortedTracks();
		System.arraycopy(SortedTracks(), 0, sortedTracks, 0, tracks.size());
		
		
		for (int i = 0; i < tracks.size(); i++) {
			sortedTracks[i].Init(200 + 50 * i, panel);
			if (i == 1) {
				
			}
		}
		
		System.out.println("LENGTH" + SortedTracks().length);
	}

	public void Update(JPanel panel) {
		Track[] sortedTracks = new Track[tracks.size()];
		//SortedTracks();
		System.arraycopy(SortedTracks(), 0, sortedTracks, 0, tracks.size());
		
		for (int i = 0; i < tracks.size(); i++) {
			sortedTracks[i].Update(200 + 50 * i, panel);
		}
	}
	
	@Override
	public boolean AddObjectToTrack(PhysicalObject physicalObject) {
		Track track = new Track((Planet)physicalObject);
		return this.AddTrack(track);
	}

	@Override
	public int GetNumOfTracks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void SetNumOfTracks(int num) {
		// TODO Auto-generated method stub
		
	}

}
