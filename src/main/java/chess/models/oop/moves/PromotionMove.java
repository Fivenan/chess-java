package main.java.chess.models.oop.moves;

import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Piece;

public class PromotionMove extends Move {

	private Piece promotedPiece;

	public PromotionMove(Tile start, Tile end) {
		super(start, end);
	}

	public Piece getPromotedPiece() {
		return promotedPiece;
	}

	public void setPromotedPiece(Piece promotedPiece) {
		this.promotedPiece = promotedPiece;
	}

	@Override
	public String getNotation() {
		return "" + getEnd().getNotation() + "=" + promotedPiece.getNotation();
	}

}
