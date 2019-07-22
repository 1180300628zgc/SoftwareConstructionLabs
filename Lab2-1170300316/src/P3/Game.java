package src.P3;

import java.util.Set;

// Game Structure:
// Game -> Player -> Action -> Piece -> Position
// Game -> Board
public abstract class Game {
	private String name = new String();
	
	Player player1 = new Player("player1");
	Player player2 = new Player("player2");
	
	public void ImplementPiece(Player player, Set<Piece> pieces) {
		
	}
	
	public static Game newChessGame() {
		return new ChessGame();
	}
	
	public static Game newGoGame() {
		return new GoGame();
	}
	
}
