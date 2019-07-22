package centralObject;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import extensions.Position;

public abstract class CentralObject{

	protected boolean enabled;
	protected String name;
	protected Position position = new Position(0, 0);
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
