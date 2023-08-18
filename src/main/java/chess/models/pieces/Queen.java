package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color, color == Color.WHITE ? 'Q' : 'q', color == Color.WHITE ? '\u2655' : '\u265b', 9);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isWhite? 5 : 4);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
