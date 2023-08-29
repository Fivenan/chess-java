package main.java.chess.models.oop.moves;

import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Piece;

public class PromotionCapturingMove extends CapturingMove {

	private Piece promotedPiece;

	public PromotionCapturingMove(Tile start, Tile end) {
		super(start, end);
	}

	public Piece getPromotedPiece() {
		return promotedPiece;
	}

	public void setPromotedPiece(Piece promotedPiece) {
		this.promotedPiece = promotedPiece;
	}

}
