package src.P3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GoGUI extends JFrame {

	boolean[][] isOccupied = new boolean[19][19];
	JButton[][] grids = new JButton[19][19];
	private Position position = new Position(0, 0);
	// private int posI, posJ;
	private String PrelabelStore = "";
	private String PostlabelStore = "";
	private String file = "src/P3/cross.jpg";
	private Icon icon = new ImageIcon(file);
	private String blackfile = "src/P3/blackcross.jpg";
	private String whitefile = "src/P3/whitecross.jpg";
	private Icon blackicon = new ImageIcon(blackfile);
	private Icon whiteicon = new ImageIcon(whitefile);
	private boolean isBlack = true;
	private int black = 0, white = 0;

	public GoGUI() {
		for (int i = 0; i < grids.length; i++) {
			for (int j = 0; j < grids.length; j++) {
				isOccupied[i][j] = false;
			}
		}
		setLayout(new GridLayout(19, 19));
		int count = 0;
		for (int i = 0; i < grids.length; i++, count++) {
			for (int j = 0; j < grids.length; j++) {
				grids[i][j] = new JButton(icon);
				// if(count % 2 == 0) {
				grids[i][j].setOpaque(true);
				grids[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						for (int posI = 0; posI < grids.length; posI++) {
							for (int posJ = 0; posJ < grids.length; posJ++) {
								if (e.getSource() == grids[posI][posJ]) {
									if (isBlack) {
										if (!isOccupied[posI][posJ]) {
											System.out.println("Black Player place piece on " + posI + ", " + posJ);
											grids[posI][posJ].setIcon(blackicon);
											isBlack = false;
											setTitle("White Player");
											black++;
											System.out.println(
													"Black Pieces Number: " + black + " White Pieces Number: " + white);
											isOccupied[posI][posJ] = true;
										}else {
											grids[posI][posJ].setIcon(icon);
										}

									} else {
										if (!isOccupied[posI][posJ]) {
											System.out.println("White Player place piece on " + posI + ", " + posJ);
											grids[posI][posJ].setIcon(whiteicon);
											isBlack = true;
											setTitle("Black Player");
											white++;
											System.out.println(
													"Black Pieces Number: " + black + " White Pieces Number: " + white);
											isOccupied[posI][posJ] = true;
										}else {
											grids[posI][posJ].setIcon(icon);
										}

									}

								}
							}
						}
					}
				});
				// }
//				else {
//					grids[i][j].setOpaque(true);
////					grids[i][j].setBorderPainted(false);
////					grids[i][j].setBackground(Color.black);
//					
//					grids[i][j].addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							for (int posI = 0; posI < grids.length; posI++) {
//								for (int posJ = 0; posJ < grids.length; posJ++) {
//									if (e.getSource() == grids[posI][posJ]) {
//										if (isBlack) {
//											System.out.println("Black Player place piece on " + posI + ", " + posJ);
//											grids[posI][posJ].setIcon(blackicon);
//											isBlack = false;
//											setTitle("White Player");
//										}else {
//											System.out.println("White Player place piece on " + posI + ", " + posJ);
//											isBlack = true;
//											setTitle("Black Player");
//										}
//									}
//								}
//							}
//						}
//					});
//				}
				add(grids[i][j]);
				count++;
			}
		}
	}

	public String GetLabel(Position position) {
		return grids[position.GetX()][position.GetY()].getText();
	}

	public void ChangeLabelofPosition(Piece piece, Position position) {
		grids[piece.GetPosition().GetX()][piece.GetPosition().GetY()].setText("");
		grids[position.GetX()][position.GetY()].setText(piece.GetName());
	}

	public void SetLabel(Piece piece, Position position) {
		grids[position.GetX()][position.GetY()].setText(piece.GetName());
	}

	public void DeleteLabel(Position position) {
		grids[position.GetX()][position.GetY()].setText("");
	}

	public Position PosOnClick() {
		return this.position;
	}

	public static void main(String[] args) {
		GoGUI chessBorder = new GoGUI();
		chessBorder.setTitle("Go");
		chessBorder.setLocation(200, 200);
		chessBorder.setSize(900, 900);
		chessBorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBorder.setVisible(true);
	}

}
