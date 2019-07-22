package test.P3;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import src.P3.Piece;
import src.P3.Player;
import src.P3.Position;

public class MyChessandGoGameTest {

	@Test
	public void testPosition() {
		Position tmpPosition = new Position(1, 2);
		Position tmpPosition2 = new Position(1, 2);
		assertTrue(tmpPosition.Equal(tmpPosition2));
		tmpPosition.SetPosition(2, 3);
		assertEquals(2, tmpPosition.GetX());
		assertEquals(3, tmpPosition.GetY());
	}

	@Test
	public void testPiece() {
		Piece tmpPiece = new Piece("Castle", 5, 19);
		Position tmpPosition = new Position(5, 19);
		assertTrue(tmpPosition.Equal(tmpPiece.GetPosition()));
		String nameString = "Castle";
		assertTrue(nameString.equals(tmpPiece.GetName()));
	}
	
	@Test
	public void testPlayer() {
		Player tmpPlayer = new Player("nobody");
		Piece tmpPiece = new Piece("Castle", 5, 19);
		Piece tmpPiece2 = new Piece("Bishop", 2, 13);
		Piece tmpPiece3 = new Piece("Pawn", 18, 13);
		tmpPlayer.addPiece(tmpPiece);
		tmpPlayer.addPiece(tmpPiece2);
		tmpPlayer.addPiece(tmpPiece3);
		Set<Piece> tmpSet = new HashSet<Piece>();
		tmpSet.add(tmpPiece);
		tmpSet.add(tmpPiece2);
		tmpSet.add(tmpPiece3);
		assertTrue(tmpSet.equals(tmpPlayer.getPieces()));
	}
}
