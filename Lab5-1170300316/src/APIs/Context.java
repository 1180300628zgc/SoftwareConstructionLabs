package APIs;

import java.util.Set;

import centralObject.CentralObject;
import circularOrbit.CircularOrbit;
import extensions.FriendRelation;
import physicalObject.Friend;
import track.Track;

public class Context {
	private StrategySS strategyss;
	private StrategySN strategysn;
	
	public Context(StrategySS strategy) {
		this.strategyss = strategy;
	}
	
	public void executeStrategySSI(String fileName, CircularOrbit<CentralObject, Track> orbit) {
		strategyss.StellarSystemI(fileName, orbit);
	}
	
	public void executeStrategySSO(String fileName, CircularOrbit<CentralObject, Track> orbit) {
		strategyss.StellarSystemO(fileName, orbit);
	}
	
	public Context(StrategySN strategy) {
		this.strategysn = strategy;
	}
	
	public void executeStrategySNI(String fileName, CircularOrbit<CentralObject, Track> orbit, Set<Friend> addedFriends, FriendRelation relation) {
		strategysn.SocialNetworkI(fileName, orbit, addedFriends, relation);
	}
	
	public void executeStrategySNO(String fileName, CircularOrbit<CentralObject, Track> orbit, Set<Friend> addedFriends, FriendRelation relation) {
		strategysn.SocialNetworkO(fileName, orbit, addedFriends, relation);
	}
}
