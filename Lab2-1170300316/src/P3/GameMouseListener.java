package src.P3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameMouseListener implements MouseListener {

	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getComponent() + "Click Detected!");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("Click Detected!");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("Click Detected!");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// System.out.println("Click Detected!");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// System.out.println("Click Detected!");
	}

}
