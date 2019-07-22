package src.P3;

import java.util.HashSet;
import java.util.Set;

public class Player {
	private String name = new String();
	private final Set<Piece> pieces = new HashSet<Piece>();
	private Action action = new Action();
	
	// Abstraction function:
	// name + action + piece -> player
	// Representation invariant:
	// name should not be null and action should not be undefined
	/**
	 * Instantiate the name of the player
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Add a new piece named newPiece to a player
	 * @param newPiece
	 * @return true if successfully added and false if already exists
	 */
	public boolean addPiece(Piece newPiece) {
		return pieces.add(newPiece);
	}
	
	/**
	 * Loss a piece
	 * @param piece
	 * @return true if successfully removed
	 */
	public boolean lossPiece(Piece piece) {
		return pieces.remove(piece);
	}
	
	public Set<Piece> getPieces() {
		return pieces;
	}
}
