package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;

public class Pawn extends Piece {

	private boolean isLeftItem = false;

	public Pawn(Color color) {
		super(color, color == Color.WHITE ? 'P' : 'p', color == Color.WHITE ? '\u2659' : '\u265f', 1);

	}

//	@Override
//	public void resetLocation() {
//		if (isWhite) {
//
//		} else {
//
//		}
//	}
}
