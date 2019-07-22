package src.P3;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class ChessGUI extends JFrame {
	
	JButton[][] grids = new JButton[8][8];
	private Position position = new Position(0, 0);
	//private int posI, posJ;
	private String PrelabelStore = "";
	private String PostlabelStore = "";
	private int isPlayer1 = 2;
	private int black = 0;
	private int white = 0;
	
	public ChessGUI() {
		setLayout(new GridLayout(8, 8));
		int count = 0;
		for(int i = 0; i < grids.length; i++, count++) {
			for(int j = 0; j < grids.length; j++) {
				grids[i][j] = new JButton();
				if(count % 2 == 0) {
					grids[i][j].setOpaque(true);
					grids[i][j].setBorderPainted(false);
					grids[i][j].setBackground(Color.white);
					grids[i][j].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							for (int posI = 0; posI < grids.length; posI++) {
								for (int posJ = 0; posJ < grids.length; posJ++) {
									if (e.getSource() == grids[posI][posJ]) {
										if (isPlayer1 > 0) {
											System.out.println("Black Player move " + PrelabelStore + " to " + posI + ", " + posJ);
											//System.out.println("White Player owns " + CountPieces(isPlayer1) + " pieces.");
										}else {
											System.out.println("White Player move " + PrelabelStore + " to " +  + posI + ", " + posJ);
											//System.out.println("Black Player owns " + CountPieces(isPlayer1) + " pieces.");
										}
										PostlabelStore = grids[posI][posJ].getText();
										grids[posI][posJ].setText(PrelabelStore);
										PrelabelStore = PostlabelStore;
										isPlayer1 -= 1;
										if (isPlayer1 == -2) {
											isPlayer1 = 2;
										}
									}
								}
							}
						}
					});
				} else {
					grids[i][j].setOpaque(true);
					grids[i][j].setBorderPainted(false);
					grids[i][j].setBackground(Color.black);
					
					grids[i][j].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							for (int posI = 0; posI < grids.length; posI++) {
								for (int posJ = 0; posJ < grids.length; posJ++) {
									if (e.getSource() == grids[posI][posJ]) {
										if (isPlayer1 > 0) {
											System.out.println("Black Player move " + PrelabelStore + " to " + posI + ", " + posJ);
											//System.out.println("White Player owns " + CountPieces(isPlayer1) + " pieces.");
										}else {
											System.out.println("White Player move " + PrelabelStore + " to " + posI + ", " + posJ);
											//System.out.println("Black Player owns " + CountPieces(isPlayer1) + " pieces.");
										}
										PostlabelStore = grids[posI][posJ].getText();
										grids[posI][posJ].setText(PrelabelStore);
										PrelabelStore = PostlabelStore;
										isPlayer1 -= 1;
										if (isPlayer1 == -2) {
											isPlayer1 = 2;
										}
									}
								}
							}
						}
					});
				}
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
		grids[position.GetX()][position.GetY()].setText("<html><span color=orange>"+piece.GetName()+"</span><html>");
	}
	
	public void DeleteLabel(Position position) {
		grids[position.GetX()][position.GetY()].setText("");
	}
	
	public Position PosOnClick() {
		return this.position;
	}
	
	public int CountPieces(boolean isBlack){
		int black = 0, white = 0;
		for (int i = 0; i < grids.length; i++) {
			for (int j = 0; j < grids.length; j++) {
				if (grids[i][j].getText().contains("Black")) {
					black++;
				}else if (grids[i][j].getText().contains("White")) {
					white++;
				}	
			}
		}
		if (isBlack) {
			return black;
		}else {
			return white;
		}
	}
	
	public static void main(String[] args) {
		ChessGUI chessBorder = new ChessGUI();
		chessBorder.setTitle("Chess");
		chessBorder.setLocation(300, 200);
		chessBorder.setSize(400, 400);
		chessBorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBorder.setVisible(true);
	}
	
}

