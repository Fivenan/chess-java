package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;

public class King extends Piece {

	public King(Color color) {
		super(color, color == Color.WHITE ? 'K' : 'k', color == Color.WHITE ? '\u2654' : '\u265a', 999);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isWhite? 4 : 5);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
