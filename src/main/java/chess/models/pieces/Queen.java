package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;

public class Queen extends Piece {

	public static final PieceType pieceType = PieceType.QUEEN;

	public Queen(Color color) {
		super(pieceType, color);

	}

}
