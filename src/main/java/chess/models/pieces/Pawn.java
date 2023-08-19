package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;

public class Pawn extends Piece {

	public static final PieceType pieceType = PieceType.PAWN;

	public Pawn(Color color) {
		super(pieceType, color);

	}

}
