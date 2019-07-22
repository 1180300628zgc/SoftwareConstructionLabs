package src.P3;

public abstract class Board {

	private int size; // the length size of a chess board
	private boolean inGrid; // True if the piece is put in grid. Otherwise it is put on cross.

	// Abstraction function:
	// size + whether each grid contains a piece -> Graph
	// Representation invariant:
	// size >= 0

	// private Piece[][] pieceOnBoard = new Piece[2][2];
	public Board(int size, boolean inGrid) {
		if (inGrid) {
			size -= 1;
		}
		this.inGrid = inGrid;
		this.size = size;
	}

	private void IniPieceOnBoard() {

	}

	private void checkRep() {
		assert size >= 0;
	}
}
