package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;

public class Rook extends Piece {

	private boolean isLeftItem = false;

	public Rook(Color color) {
		super(color, color == Color.WHITE ? 'R' : 'r', color == Color.WHITE ? '\u2656' : '\u265c', 5);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isLeftItem ? 1 : 8);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
