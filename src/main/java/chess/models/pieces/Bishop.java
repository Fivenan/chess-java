package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;

public class Bishop extends Piece {

	public static final PieceType pieceType = PieceType.BISHOP;

	private boolean isOnBlack = false;

	public Bishop(Color color) {
		super(pieceType, color);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isLeftItem ? 3 : 6);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
