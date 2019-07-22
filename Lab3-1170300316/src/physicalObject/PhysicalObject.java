package physicalObject;

import java.util.Set;

import extensions.Position;

public abstract class PhysicalObject {
	protected String name;
	protected Position position;
	
	//Mutability
	//Both the name and the position can be changed
	//AF: 
	//name + position -> PhysicalObject
	//RI:
	//The position should be a legal position
	//Safety from rep:
	//All variables are set to protected and the field of Physical Objects can only be accessed by methods
	
	/**
	 * Get the name of the obj
	 * @return name
	 */
	public String GetName() {
		return new String(name);
	}
	
	/**
	 * Set the name of the obj
	 * @param name
	 */
	public void SetName(String name) {
		this.name = new String(name);
	}
	
	/**
	 * Set the pos of the obj
	 * @param position
	 * @return true if successfully changed, false if the position is the same as the original
	 */
	public boolean SetPosition(Position position) {
		if (this.position.isEqual(position)) {
			return false;
		}
		this.position = position;
		return true;
	}
	
	/**
	 * Get the exact position of the obj
	 * @return position
	 */
	public Position GetPosition() {
		return position;
	}
	
	/**
	 * Check whether two physicalobjs are of the equal
	 * @param physicalObject
	 * @return true if the names are of the same, false if the names are different
	 */
	public boolean isEqual(PhysicalObject physicalObject) {
		if (physicalObject.GetName().equals(name)) {
			return true;
		}
		return false;
	}
	
	@Override
    public boolean equals(Object obj) {
        PhysicalObject physicalObject = (PhysicalObject)obj;
        if (physicalObject.GetName().equals(this.name)) {
			return true;
		}
        return false;
    }
}
