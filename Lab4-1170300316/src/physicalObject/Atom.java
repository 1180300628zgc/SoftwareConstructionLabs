package physicalObject;

import static org.junit.Assert.assertTrue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Atom extends PhysicalObject {
	
	private int level;

	//AF
	//level represents a electron
	//RI
	//level should be smaller than the number of the tracks
	
	public Atom(int level) {
		this.level = level;
	}
	
	public int GetLevel() {
		return level;
	}
	
	public void SetLevel(int level) {
		assertTrue(level >= 0);
		this.level = level;
	}
	
	public void Init(double degree, int radius, JPanel panel) {
		JLabel electron = new JLabel("New label");
		electron.setIcon(new ImageIcon(
				"lib/AtomStructure/electron.png"));
		//planet.setBounds(555, 70, 60, 60);
		
		electron.setBounds((int) (radius * Math.cos(degree * Math.PI / 180)) -30 + 480, (int) (radius * Math.sin(degree * Math.PI / 180)) - 30 + 320, 60, 60);
		panel.add(electron);
		
		System.out.println("New electron INITIATED");
	}
	
//	public void Init(int radius, JPanel panel) {
//		JLabel planet = new JLabel("New label");
//		planet.setIcon(new ImageIcon(
//				"/Users/tony/Documents/Software Construction/Handin/Lab3-1170300316/lib/AtomStructure/electron.png"));
//		//planet.setBounds(555, 70, 60, 60);
//		planet.setBounds(0 -30 + 480, 0 - 30 + 320, 60, 60);
//		panel.add(planet);
//	}
}
