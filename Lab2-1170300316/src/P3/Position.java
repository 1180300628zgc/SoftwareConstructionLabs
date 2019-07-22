package src.P3;

public class Position {
	private int x; // Grid num in row
	private int y; // Grid num in column
	private int positionX; // X in the coordination
	private int positionY; // Y in the coordination

	// Abstraction function:
	// x + y -> position
	// Representation invariant:
	// both x and y >= 0

	/**
	 * Instantiate Position with a coordination
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void SetPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private void TransferPostoAbs() {

	}

	public int GetX() {
		return this.x;
	}

	public int GetY() {
		return this.y;
	}

	public boolean Equal(Position position) {
		if (x == position.GetX() && y == position.GetY()) {
			return true;
		}
		return false;
	}
	
	private void checkRep() {
		assert x >= 0;
		assert y >= 0;
	}
}
