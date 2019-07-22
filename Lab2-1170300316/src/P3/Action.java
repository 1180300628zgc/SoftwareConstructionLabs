package src.P3;

public class Action {
	
	/**
	 * Move a piece from a position to another specific position
	 * @param piece
	 * @param destination x
	 * @param destination y
	 */
	public void MovePieceTo(Piece piece, int x, int y) {
		piece.SetPosition(x, y);
	}
	
	/*
	public void PlacePiece(Piece piece, int x, int y) {
		
	}
	*/
	
	/**
	 * Remove a piece from the board
	 * @param piece
	 */
	public void RemovePiece(Piece piece) {
		piece.GetKilled();
	}
}
