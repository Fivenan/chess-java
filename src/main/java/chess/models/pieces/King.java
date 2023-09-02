package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.moves.CastlingMove;
import main.java.chess.models.oop.moves.Move;

public class King extends Piece {

	public static final PieceType PIECE_TYPE = PieceType.KING;

	public King(Color color) {
		super(PIECE_TYPE, color);
	}

	public List<Move> generateValidMoves(OopChessBoard b, int rank, int file) {
		List<Move> validMoves = new ArrayList<>();
		generateValidMovesRecursive(b, rank, file, -1, -1, validMoves, false);
		generateValidMovesRecursive(b, rank, file, -1, 1, validMoves, false);
		generateValidMovesRecursive(b, rank, file, 1, -1, validMoves, false);
		generateValidMovesRecursive(b, rank, file, 1, 1, validMoves, false);
		generateValidMovesRecursive(b, rank, file, 0, -1, validMoves, false);
		generateValidMovesRecursive(b, rank, file, 0, 1, validMoves, false);
		generateValidMovesRecursive(b, rank, file, 1, 0, validMoves, false);
		generateValidMovesRecursive(b, rank, file, -1, 0, validMoves, false);
		if (color == Color.WHITE) {
			if (b.isWhiteCanCastleKingSide()) {
				addIfValidForCastling(b, rank, file, file + 2, validMoves);
			}
			if (b.isWhiteCanCastleQueenSide()) {
				addIfValidForCastling(b, rank, file, file - 2, validMoves);
			}
		}
		if (color == Color.BLACK) {
			if (b.isBlackCanCastleKingSide()) {
				addIfValidForCastling(b, rank, file, file + 2, validMoves);
			}
			if (b.isBlackCanCastleQueenSide()) {
				addIfValidForCastling(b, rank, file, file - 2, validMoves);
			}
		}
		return validMoves;
	}

	private void addIfValidForCastling(OopChessBoard b, int rank, int startFile, int endFile, List<Move> validMoves) {
		if (b.isEmptyBetweenTwoTilesInRow(rank, startFile, endFile)) {
			validMoves.add(new CastlingMove(b, b.getTile(rank, startFile), b.getTile(rank, endFile)));
		}
	}

}
