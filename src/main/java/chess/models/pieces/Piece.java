package main.java.chess.models.pieces;

import main.java.chess.models.PieceLocation;
import main.java.chess.models.enums.Color;

public abstract class Piece {

	public final Color color;
	public final char notation;
	public final char symbol;
	public final int value;

	protected PieceLocation pieceLocation = new PieceLocation();

	public Piece(Color color, char notation, char symbol, int value) {
		this.color = color;
		this.notation = notation;
		this.symbol = symbol;
		this.value = value;
	}

	public static Piece getPieceFromChar(char c) {
		Piece res = null;
		switch (c) {
		case 'P':
			res = new Pawn(Color.WHITE);
			break;
		case 'p':
			res = new Pawn(Color.BLACK);
			break;
		case 'R':
			res = new Rook(Color.WHITE);
			break;
		case 'r':
			res = new Rook(Color.BLACK);
			break;
		case 'N':
			res = new Knight(Color.WHITE);
			break;
		case 'n':
			res = new Knight(Color.BLACK);
			break;
		case 'B':
			res = new Bishop(Color.WHITE);
			break;
		case 'b':
			res = new Bishop(Color.BLACK);
			break;
		case 'Q':
			res = new Queen(Color.WHITE);
			break;
		case 'q':
			res = new Queen(Color.BLACK);
			break;
		case 'K':
			res = new King(Color.WHITE);
			break;
		case 'k':
			res = new King(Color.BLACK);
			break;
		default:
			System.out.println("unrecognizable char");
			break;
		}
		return res;
	}

	public PieceLocation getLocation() {
		return pieceLocation;
	}

	public void setLocation(PieceLocation pieceLocation) {
		this.pieceLocation = pieceLocation;
	}

	public boolean isOnBoard() {
		boolean result = false;
		// todo
		return result;
	}

//	public abstract void resetLocation();

}
