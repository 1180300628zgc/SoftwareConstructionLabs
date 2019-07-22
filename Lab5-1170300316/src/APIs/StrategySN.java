package APIs;

import java.util.Set;

import centralObject.CentralObject;
import circularOrbit.CircularOrbit;
import extensions.FriendRelation;
import physicalObject.Friend;
import track.Track;

public interface StrategySN {
	public void SocialNetworkI(String fileName, CircularOrbit<CentralObject, Track> orbit, Set<Friend> addedFriends, FriendRelation relation);
	public void SocialNetworkO(String fileName, CircularOrbit<CentralObject, Track> orbit, Set<Friend> addedFriends, FriendRelation relation);
}
