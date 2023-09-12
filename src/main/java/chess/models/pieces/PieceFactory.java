package main.java.chess.models.pieces;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;

public class PieceFactory {

	private PieceFactory() {
	}

	public static Piece create(PieceType type, Color color) {

		Piece newPiece = null;
		switch (type) {
		case KING:
			newPiece = new King(color);
			break;
		case QUEEN:
			newPiece = new Queen(color);
			break;
		case ROOK:
			newPiece = new Rook(color);
			break;
		case BISHOP:
			newPiece = new Bishop(color);
			break;
		case KNIGHT:
			newPiece = new Knight(color);
			break;
		case PAWN:
			newPiece = new Pawn(color);
			break;
		}

		return newPiece;
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
}
