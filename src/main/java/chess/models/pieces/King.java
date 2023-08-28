package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.moves.Move;

public class King extends Piece {

	public static final PieceType PIECE_TYPE = PieceType.KING;

	public King(Color color) {
		super(PIECE_TYPE, color);
	}

	public List<Move> generateValidMoves(OopChessBoard b, int rank, int file) {
		List<Move> validMoves = new ArrayList<>();
		super.generateValidMovesRecursive(b, rank, file, -1, -1, validMoves, false);
		super.generateValidMovesRecursive(b, rank, file, -1, 1, validMoves, false);
		super.generateValidMovesRecursive(b, rank, file, 1, -1, validMoves, false);
		super.generateValidMovesRecursive(b, rank, file, 1, 1, validMoves, false);
		super.generateValidMovesRecursive(b, rank, file, 0, -1, validMoves, false);
		super.generateValidMovesRecursive(b, rank, file, 0, 1, validMoves, false);
		super.generateValidMovesRecursive(b, rank, file, 1, 0, validMoves, false);
		super.generateValidMovesRecursive(b, rank, file, -1, 0, validMoves, false);
		return validMoves;
	}


}
