package main.java.chess.models.oop.moves;

import java.util.List;

import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Piece;
import main.java.chess.models.pieces.PieceFactory;

public class PromotionMove extends OopMove {

	private Piece promotedPiece;

	public PromotionMove(Tile start, Tile end) {
		super(start, end);
	}

	@Override
	public void apply(OopChessBoard b) {

	}

	public void apply(OopChessBoard b, PieceType pieceType) {
		getEnd().setPiece(PieceFactory.create(pieceType, getMovingPiece().color));
		getStart().clear();
	}

	public Piece getPromotedPiece() {
		return promotedPiece;
	}

	public void setPromotedPiece(Piece promotedPiece) {
		this.promotedPiece = promotedPiece;
	}

	@Override
	public String getNotation(List<OopMove> otherMovesWithSameTarget) {
		return "" + getEnd().getNotation() + "=" + promotedPiece.getNotation();
	}

}
