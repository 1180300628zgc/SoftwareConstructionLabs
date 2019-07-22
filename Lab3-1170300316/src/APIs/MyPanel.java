package APIs;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * MyPanel.java.
 * 
 * @author Kaiyan Zhang
 */
public class MyPanel extends JPanel {
	int x = 0, y = 400;
	Ball b;

	public MyPanel(Ball b) {
		this.b = b;
		b.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawOval(x, y, 20, 20);
	}

	public static void main(String[] args) {
		Ball b = new Ball();
		MyPanel p = new MyPanel(b);
		b.setPanel(p);
		/* panel thread, paint the monkey */
		JFrame frame = new JFrame();
		frame.add(p);
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

class Ball extends Thread {
//  int x = 0;
//  int y = 400;
	MyPanel p;

	public void setPanel(MyPanel p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			if (p.x > 800) {
				p.x = 0;
			} else {
				p.x = p.x + 1;
			}
			p.repaint();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
