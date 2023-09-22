package main.java.chess.models.oop.moves;

import java.util.List;

import main.java.chess.models.enums.Color;
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
		if (getMovingPiece().pieceType == PieceType.KING && getMovingPiece().color == Color.BLACK) {
			b.setBlackCanCastleQueenSide(false);
			b.setBlackCanCastleKingSide(false);

		}
		if (getMovingPiece().pieceType == PieceType.KING && getMovingPiece().color == Color.WHITE) {
			b.setWhiteCanCastleQueenSide(false);
			b.setWhiteCanCastleKingSide(false);

		}
		if (getMovingPiece().pieceType == PieceType.ROOK && getMovingPiece().color == Color.BLACK) {
			if (getStart().file == 0) {
				b.setBlackCanCastleQueenSide(false);
			}
			if (getStart().file == 7) {
				b.setBlackCanCastleKingSide(false);
			}
		}
		if (getMovingPiece().pieceType == PieceType.ROOK && getMovingPiece().color == Color.WHITE) {
			if (getStart().file == 0) {
				b.setWhiteCanCastleQueenSide(false);
			}
			if (getStart().file == 7) {
				b.setWhiteCanCastleKingSide(false);
			}
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

