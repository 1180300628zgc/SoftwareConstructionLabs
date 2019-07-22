package src.P3;

import java.awt.Label;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

public class ChessGame extends Game {

	
	private Piece blackRock1 = new Piece("Black Rock", 0, 0);
	private Piece blackRock2 = new Piece("Black Rock", 0, 7);
	private Piece blackKnight1 = new Piece("Black Knight", 0, 1);
	private Piece blackKnight2 = new Piece("Black Knight", 0, 6);
	private Piece blackBishop1 = new Piece("Black Bishop", 0, 2);
	private Piece blackBishop2 = new Piece("Black Bishop", 0, 5);
	private Piece blackKing = new Piece("Black King", 0, 3);
	private Piece blackQueen = new Piece("Black Queen", 0, 4);
	private Piece blackPawn1 = new Piece("Black Pawn", 1, 0);
	private Piece blackPawn2 = new Piece("Black Pawn", 1, 1);
	private Piece blackPawn3 = new Piece("Black Pawn", 1, 2);
	private Piece blackPawn4 = new Piece("Black Pawn", 1, 3);
	private Piece blackPawn5 = new Piece("Black Pawn", 1, 4);
	private Piece blackPawn6 = new Piece("Black Pawn", 1, 5);
	private Piece blackPawn7 = new Piece("Black Pawn", 1, 6);
	private Piece blackPawn8 = new Piece("Black Pawn", 1, 7);
	
	private Piece whiteRock1 = new Piece("White Rock", 7, 0);
	private Piece whiteRock2 = new Piece("White Rock", 7, 7);
	private Piece whiteKnight1 = new Piece("White Knight", 7, 1);
	private Piece whiteKnight2 = new Piece("White Knight", 7, 6);
	private Piece whiteBishop1 = new Piece("White Bishop", 7, 2);
	private Piece whiteBishop2 = new Piece("White Bishop", 7, 5);
	private Piece whiteKing = new Piece("White King", 7, 3);
	private Piece whiteQueen = new Piece("White Queen", 7, 4);
	private Piece whitePawn1 = new Piece("White Pawn", 6, 0);
	private Piece whitePawn2 = new Piece("White Pawn", 6, 1);
	private Piece whitePawn3 = new Piece("White Pawn", 6, 2);
	private Piece whitePawn4 = new Piece("White Pawn", 6, 3);
	private Piece whitePawn5 = new Piece("White Pawn", 6, 4);
	private Piece whitePawn6 = new Piece("White Pawn", 6, 5);
	private Piece whitePawn7 = new Piece("White Pawn", 6, 6);
	private Piece whitePawn8 = new Piece("White Pawn", 6, 7);
	
	
	private Set<Piece> blackPieces = new HashSet<Piece>();
	private Set<Piece> whitePieces = new HashSet<Piece>();
	
	ChessGUI chessBorder = new ChessGUI();

	Position tmp = chessBorder.PosOnClick();

	public ChessGame() {

		chessBorder.setTitle("Chess");
		chessBorder.setLocation(200, 200);
		chessBorder.setSize(900, 900);
		chessBorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBorder.setVisible(true);

		
		blackPieces.add(blackRock1);
		blackPieces.add(blackRock2);
		blackPieces.add(blackKnight1);
		blackPieces.add(blackKnight2);
		blackPieces.add(blackBishop1);
		blackPieces.add(blackBishop2);
		blackPieces.add(blackKing);
		blackPieces.add(blackQueen);
		blackPieces.add(blackPawn1);
		blackPieces.add(blackPawn2);
		blackPieces.add(blackPawn3);
		blackPieces.add(blackPawn4);
		blackPieces.add(blackPawn5);
		blackPieces.add(blackPawn6);
		blackPieces.add(blackPawn7);
		blackPieces.add(blackPawn8);

		whitePieces.add(whiteRock1);
		whitePieces.add(whiteRock2);
		whitePieces.add(whiteKnight1);
		whitePieces.add(whiteKnight2);
		whitePieces.add(whiteBishop1);
		whitePieces.add(whiteBishop2);
		whitePieces.add(whiteKing);
		whitePieces.add(whiteQueen);
		whitePieces.add(whitePawn1);
		whitePieces.add(whitePawn2);
		whitePieces.add(whitePawn3);
		whitePieces.add(whitePawn4);
		whitePieces.add(whitePawn5);
		whitePieces.add(whitePawn6);
		whitePieces.add(whitePawn7);
		whitePieces.add(whitePawn8);
		
		ImplementPiece(player1, blackPieces);
		ImplementPiece(player2, whitePieces);
		
		//PositionListener();

	}

	@Override
	public void ImplementPiece(Player player, Set<Piece> pieces) {
		for (Piece piece : pieces) {
			System.out.println(piece.GetName());
			player.addPiece(piece);
			chessBorder.SetLabel(piece, piece.GetPosition());
			System.out.println("Label: " + chessBorder.GetLabel(piece.GetPosition()));
		}
	}

//	public void PositionListener() {
//		System.out.println(tmp.GetX() + tmp.GetY());
//		System.out.println(chessBorder.PosOnClick().GetX() + chessBorder.PosOnClick().GetY());
//
//		while (true) {
//
//			if (!tmp.Equal(chessBorder.PosOnClick())) {
//				break;
//
//			}
//		}
//		tmp = chessBorder.PosOnClick();
//		System.out.println(tmp.GetX() + tmp.GetY());
//	}
}
