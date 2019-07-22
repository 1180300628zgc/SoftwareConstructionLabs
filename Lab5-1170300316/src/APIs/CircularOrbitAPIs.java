package APIs;

import java.util.HashSet;

import circularOrbit.CircularOrbit;
import extensions.Position;
import extensions.Relation;
import physicalObject.Friend;
import physicalObject.PhysicalObject;
import track.Track;

public class CircularOrbitAPIs<E> {

	double getObjectDistributionEntropy(CircularOrbit c) {
		double result = 0;
		for (Track track : (HashSet<Track>) c.GetTrack()) {
			result += 1 / (track.getAtomObjects().size() + track.GetFriendObject().size()
					+ track.getPhysicalObjects().size());
		}
		return result;
	}

	int getLogicalDistance(CircularOrbit c, E e1, E e2, Relation relation) {
		Friend[] e = new Friend[c.GetFriends().size()];
		int i = 0;

		int e1Key = 0, e2Key = 0;

		for (Friend friend : (HashSet<Friend>) c.GetFriends()) {
			if (friend.equals(e1)) {
				e1Key = i;
			}
			if (friend.equals(e2)) {
				e2Key = i;
			}
			e[i] = friend;
			i++;
		}

		int[][] map = new int[10000][10000];
		for (int j = 0; j < map.length; j++) {
			for (int j2 = 0; j2 < map.length; j2++) {
				map[j][j2] = 9999;
			}
		}
		for (i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (relation.isExistedRelation(e[i], e[j])) {
					map[i][j] = 1;
					map[j][i] = 1;
				}
			}
		}

		for (int p = 0; p < c.GetFriends().size(); p++) {
			for (int q = 0; q < c.GetFriends().size(); q++) {
				for (int r = 0; r < c.GetFriends().size(); r++) {
					if (map[p][q] + map[q][r] < map[p][r]) {
						map[p][r] = map[p][q] + map[q][r];
					}
				}
			}
		}

		return map[e1Key][e2Key];
	}

	double getPhysicalDistance(CircularOrbit c, E e1, E e2) {
		PhysicalObject r1 = (PhysicalObject) e1;
		PhysicalObject r2 = (PhysicalObject) e2;
		Position position = r1.GetPosition();
		Position position_1 = r2.GetPosition();
		return Math.sqrt(position.GetRadius() * position.GetRadius() + position_1.GetRadius() * position_1.GetRadius()
				- 2 * position.GetRadius() * position_1.GetRadius()
						* Math.cos((position.GetDegree() - position_1.GetDegree()) * Math.PI / 180));
	}

	Difference getDifference(CircularOrbit c1, CircularOrbit c2) {
		int tracknum = c1.GetTrack().size() - c2.GetTrack().size();
		int objdiff[] = new int[c1.GetTrack().size()];
		int obj1[] = new int[c1.GetTrack().size()];
		int obj2[] = new int[c2.GetTrack().size()];
		int min = c1.GetTrack().size() > c2.GetTrack().size() ? c2.GetTrack().size() : c1.GetTrack().size();
		for (int i = 0; i < min; i++) {
			objdiff[i] = obj1[i] - obj2[i];
		}
		return new Difference(tracknum, objdiff);
	}

}
