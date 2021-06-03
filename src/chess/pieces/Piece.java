package chess.pieces;

import chess.models.PieceLocation;

public abstract class Piece {

	public final boolean isWhite;
	public final char notation;
	public final char symbol;
	public final int value;
	
	protected PieceLocation pieceLocation = new PieceLocation();

	public Piece(boolean isWhite, char notation, char symbol, int value) {
		this.isWhite = isWhite;
		this.notation = notation;
		this.symbol = symbol;
		this.value = value;
	}

	public static Piece getPiecefromChar(char c) {
		Piece res = null;
		switch (c) {
		case 'P':
			res = new Pawn(true);
			break;
		case 'p':
			res = new Pawn(false);
			break;
		case 'R':
			res = new Rook(true);
			break;
		case 'r':
			res = new Rook(false);
			break;
		case 'N':
			res = new Knight(true);
			break;
		case 'n':
			res = new Knight(false);
			break;
		case 'B':
			res = new Bishop(true);
			break;
		case 'b':
			res = new Bishop(false);
			break;
		case 'Q':
			res = new Queen(true);
			break;
		case 'q':
			res = new Queen(false);
			break;
		case 'K':
			res = new King(true);
			break;
		case 'k':
			res = new King(false);
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
	
	public abstract void resetLocation();

}
