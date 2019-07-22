package src.P3;

import javax.swing.JFrame;

public class GoGame extends Game{
	
	GoGUI goBoard = new GoGUI();
	
	public GoGame() {
		goBoard.setTitle("Go");
		goBoard.setLocation(200, 200);
		goBoard.setSize(900, 900);
		goBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		goBoard.setVisible(true);
	}
}
