package src.P3;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PicPanel extends JPanel{
	Frame frame;
	public PicPanel(Frame frame) {
		this.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        this.frame = frame;
	}
	
	 @Override protected void paintComponent(Graphics g) {
         try {
         BufferedImage bi = ImageIO.read(new File("src/P3/GoBoard.jpg"));
         g.drawImage(bi, 0, 0, this);
         }
         catch (IOException e) {
         e.printStackTrace();
         }
         /*
        for (int j = 0; j < 20; j++) {
            // g.drawRect(50 * i, 50 * j, 50, 50);
            g.drawLine(0, 50 + 50 * j, 1000, 50 + 50 * j);
        }
        for(int j = 0; j < 20; j++) {
            g.drawLine(50 + 50 * j, 0, 50 + 50 * j, 1000);
        }
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {
                if (frame.map[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(50 * j + 25, 50 * i + 25, 50, 50);
                }
                g.setColor(Color.black);
                if (frame.map[i][j] == 2) {
                    g.setColor(Color.black);
                    g.fillOval(50 * j + 25, 50 * i + 25, 50, 50);
                }
            }
        }
        */
    }

}
