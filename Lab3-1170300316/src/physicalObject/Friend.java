package physicalObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Friend extends PhysicalObject{
	
	private int age;
	private String gender;
	
	public Friend() {
		
	}
	
	public Friend(String string, int age, String string2) {
		this.name = new String(string);
		this.age = age;
		this.gender = new String(string2);
	}
	
	public Friend(Friend a) {
		this.name = new String(a.name);
		this.age = 0;
		this.gender = "UNKNOWN";
	}
	
	public void Init(double degree, int radius, JPanel panel) {
		JLabel electron = new JLabel("New label");
		electron.setIcon(new ImageIcon(
				"lib/SocialNetwork/human.png"));
		//planet.setBounds(555, 70, 60, 60);
		
		electron.setBounds((int) (radius * Math.cos(degree * Math.PI / 180)) -30 + 480, (int) (radius * Math.sin(degree * Math.PI / 180)) - 30 + 320, 60, 60);
		panel.add(electron);
		
		System.out.println("New FRIEND INITIATED");
	}
}
