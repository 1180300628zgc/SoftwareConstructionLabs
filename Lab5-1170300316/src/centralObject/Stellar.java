package centralObject;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import extensions.Position;

public class Stellar extends CentralObject {

	private double radius;
	private double mass;
	

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Stellar(String string, double radius, double mass) {
		this.name = new String(string);
		this.radius = radius;
		this.mass = mass;
	}

	/**
	 * get the position of stellar, which is (0, 0)
	 * 
	 * @return position 0, 0
	 */
	public Position GetPosition() {
		return position;
	}
	
	//public class StellarGUI{
		protected int width = 10, height = 10;
		protected int x = 300, y = 300;
		ImageIcon image = new ImageIcon("lib/StellarSystem/sun.png");
		JLabel jl = new JLabel(image);
		JButton jb = new JButton();
		
		
		
		public JLabel GetStellarGUI() {
			return jl;
		}
		
		public JButton GetStellarGUI2() {
			jb.setOpaque(true);
			jb.setBorderPainted(false);
			jb.setIcon(image);
			return jb;
		}
	//}
}
