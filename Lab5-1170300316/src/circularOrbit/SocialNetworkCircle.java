package circularOrbit;

import javax.swing.JPanel;

import physicalObject.PhysicalObject;
import track.Track;

public class SocialNetworkCircle extends ConcreteCircularOrbit{

	@Override
	public boolean AddObjectToTrack(PhysicalObject physicalObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int GetNumOfTracks() {
		return tracks.size();
	}

	@Override
	public void SetNumOfTracks(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Init(JPanel panel) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tracks.size(); i++) {
			for (Track track : tracks) {
				if (track.GetLevel() == i+ 1) {
					track.FriendInit(200 + 50 * i, panel);
				}
			}
		}
	}

	@Override
	public void Update(JPanel panel) {
		// TODO Auto-generated method stub
		
	}

}
