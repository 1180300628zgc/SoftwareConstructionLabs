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

  // static Track[] sortedTracks = new Track[330000];
  static boolean flag = false;

  /** @return */
  public int GetSizeOfTracks() {
    return tracks.size();
  }

  public void Update() {}

  public void Init(JPanel panel) {
    // List<Track> tmpTrack = SortedTracks();

    // ------------NEED DEBUG------------

    Track[] sortedTracks = new Track[tracks.size()];
    // SortedTracks();
    System.arraycopy(SortedTracks(), 0, sortedTracks, 0, tracks.size());

//    if (!flag) {
//      System.arraycopy(SortedTracks(), 0, sortedTracks, 0, tracks.size());
//    }
//    flag = true;
    // Restrict the size of the graph in order to show the graph.
    // A graph which loads too many pics will exceed the heap.
    if (tracks.size() < 15) {
      for (int i = 0; i < tracks.size(); i++) {
        sortedTracks[i].Init(200 + 50 * i, panel);
        if (i == 1) {}
      }
    } else {
      for (int i = 0; i < 15; i++) {
        sortedTracks[i].Init(200 + 50 * i, panel);
        if (i == 1) {}
      }
    }

    System.out.println("LENGTH" + SortedTracks().length);
  }

  public void Update(JPanel panel) {
    Track[] sortedTracks = new Track[tracks.size()];
    // SortedTracks();
    System.arraycopy(SortedTracks(), 0, sortedTracks, 0, tracks.size());

    for (int i = 0; i < tracks.size(); i++) {
      sortedTracks[i].Update(200 + 50 * i, panel);
    }
  }

  @Override
  public boolean AddObjectToTrack(PhysicalObject physicalObject) {
    Track track = new Track((Planet) physicalObject);
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
