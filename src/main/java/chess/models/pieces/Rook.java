package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;

public class Rook extends Piece {

	public static final PieceType pieceType = PieceType.ROOK;

	public Rook(Color color) {
		super(pieceType, color);

	}

}
