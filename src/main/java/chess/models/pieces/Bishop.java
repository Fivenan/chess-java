package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;

public class Bishop extends Piece {

	private boolean isLeftItem = false;

	public Bishop(Color color) {
		super(color, color == Color.WHITE ? 'B' : 'b', color == Color.WHITE ? '\u2657' : '\u265d', 3);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isLeftItem ? 3 : 6);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
