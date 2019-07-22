package centralObject;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import extensions.Position;

public abstract class CentralObject{

	//AF
	//name + position -> CentralObject
	//RI
	//position should not be located elsewhere other than (0, 0)
	//Safety from exposure
	//All variants are set to protected and can only be revised through methods provided
	
	protected boolean enabled;
	protected String name;
	protected Position position = new Position(0, 0);
	
	/**
	 * To make sure that the position remains constant
	 */
	public void checkRep() {
		Position position = new Position(0, 0);
		assertTrue(this.position.isEqual(position));
	}
	
	/**
	 * Get the name of the central obj
	 * 
	 * @return name
	 */
	public String GetName() {
		return new String(name);
	}

	/**
	 * Determine whether two centralobjs are the same by their name
	 * 
	 * @param centralObject
	 * @return true: same; false: different
	 */
	public boolean isEqual(CentralObject centralObject) {
		if (centralObject == null) {
			return true;
		}
		if (centralObject.GetName().equals(this.name)) {
			return true;
		}
		return false;
	}

	/**
	 * Determine whether a central obj really exists
	 * 
	 * @return true: existed; false: not existed
	 */
	public abstract boolean isEnabled();
	
	
	//------- GUI PART -------//
//	protected int width = 10, height = 10;
//	protected int x = 300, y = 300;
//	
//	public void draw(Graphics g) {
//		g.setColor(Color.red);
//		g.fillOval(x, y, width, height);
//	}
//	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.fillOval(x, y, width, height);
//	}
}
