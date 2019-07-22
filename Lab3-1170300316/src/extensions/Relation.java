package extensions;

import java.util.HashMap;
import java.util.Map;

import physicalObject.PhysicalObject;

public abstract class Relation {

	// Mutability
	// The distance of a relation can be changed
	// AF:
	// two physical objects -> relation
	// RI:
	// two objects in a relation should be of the same type
	// Safety from rep:
	// All variables are set to protected and the field of Physical Objects can only
	// be accessed by methods

	private int distance;

	private Map<PhysicalObject, PhysicalObject> relations = new HashMap<PhysicalObject, PhysicalObject>();

	/**
	 * Add a relation between a and b
	 * 
	 * @param a
	 * @param b
	 */
	public void addRelation(PhysicalObject a, PhysicalObject b) {
		relations.put(a, b);
	}

	/**
	 * Check if object a and b are related
	 * 
	 * @param a
	 * @param b
	 * @return true if related
	 */
	public boolean isExistedRelation(PhysicalObject a, PhysicalObject b) {
		if (relations.get(a).isEqual(b)) {
			return true;
		}
		if (relations.get(b).isEqual(a)) {
			return true;
		}
		return false;
	}

	/**
	 * get the distance of a relation
	 * 
	 * @return distance
	 */
	public int GetDistance() {
		return distance;
	}

	/**
	 * set the distance of a relation
	 */
	public void SetDistance(int i) {
		this.distance = i;
	}
}
