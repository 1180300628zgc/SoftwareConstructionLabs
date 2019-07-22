package extensions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import physicalObject.Friend;

public class FriendRelation extends Relation {

	Map<Friend, Set<Friend>> pair = new HashMap<Friend, Set<Friend>>();
	
	Set<Friend> friends = new HashSet<Friend>();
	
	Friend center;

	private Set<Friend> Searched = new HashSet<Friend>();
	
	public FriendRelation(Set<Friend> set) {
		friends = set;
	}

	public Map<Friend, Set<Friend>> GetMap() {
		return pair;
	}
	
	public void NewRelation(Friend a, Friend b) {
		if (pair.keySet().contains(a)) {
			pair.get(a).add(b);
		} else {
			pair.put(a, new HashSet<Friend>());
			pair.get(a).add(b);
		}
		if (pair.keySet().contains(b)) {
			pair.get(b).add(a);
		} else {
			pair.put(b, new HashSet<Friend>());
			pair.get(b).add(a);
		}
	}

	public boolean isRelated(Friend a, Friend b) {
		if (pair.get(a).contains(b)) {
			return true;
		}
		return false;
	}
	
	
	public void SetCenter(Friend a) {
		this.center = new Friend(a);
		for (Friend friend : friends) {
			if (friend.equals(center)) {
				Searched.add(friend);
			}
		}
	}

	/*
	 * public void ShortestDistanceTo(Friend a) { Set<Friend> Searched = new
	 * HashSet<Friend>(); Searched.add(a); for (Friend friend : Searched) { for
	 * (Friend friend2 : pair.get(friend)) {
	 * 
	 * } } }
	 * 
	 * public Set<Friend> FriendsOnLevel(int level) {
	 * 
	 * }
	 */
}
