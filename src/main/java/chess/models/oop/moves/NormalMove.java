package main.java.chess.models.oop.moves;

import java.util.List;

import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.Piece;

public class NormalMove extends Move {

	public NormalMove(Tile start, Tile end) {
		super(start, end);
	}

	@Override
	public void apply(OopChessBoard b) {
		getEnd().setPiece(getMovingPiece());
		getStart().clear();
		if (getMovingPiece().pieceType == PieceType.PAWN) {
			b.resetHalfmoveClock();
		} else {
			b.incrementHalfmoveClock();
		}
	}

	@Override
	public String getNotation(List<Move> otherMovesWithSameTarget) {
		StringBuilder res = new StringBuilder();
		Piece piece = getStart().getPiece();
		if (!(piece instanceof Pawn)) {
			res.append(piece.getNotation());
		}
		res.append(getSpecifyingTile(otherMovesWithSameTarget));
		res.append(getEnd().getPosition());
		return res.toString();
	}
}

