package src.P3;

public class Piece {
	
	private String pieceName = new String();
	private Position position = new Position(0, 0);
	private boolean isAlive;
	
	// Abstraction function:
	// name + position -> piece
	// Representation invariant:
	// pieceName should not be null
	
	public Piece(String pieceName, int x, int y) {
		this.pieceName = pieceName;
		this.isAlive = true;
		this.position.SetPosition(x, y);
	}
	
	public String GetName() {
		return this.pieceName;
	}
	
	public void SetPosition(int x, int y) {
		position.SetPosition(x, y);
	}
	
	public void GetKilled() {
		this.isAlive = false;
	}
	
	public Position GetPosition() {
		return this.position;
	}
	
	private void checkRep() {
		assert pieceName != null;
	}
}
