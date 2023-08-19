package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;

public abstract class Piece {

	public final PieceType pieceType;
	public final Color color;

	public Piece(PieceType pieceType, Color color) {
		this.pieceType = pieceType;
		this.color = color;
	}

	public char getNotation() {
		return pieceType.getNotation(color);
	}

	public char getSymbol() {
		return pieceType.getSymbol(color);
	}

	public int getValue() {
		return pieceType.getValue();
	}

}
