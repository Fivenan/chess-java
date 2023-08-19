package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;

public class King extends Piece {

	public static final PieceType pieceType = PieceType.KING;

	public King(Color color) {
		super(pieceType, color);

	}

}
