package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.moves.OopMove;

public class Rook extends Piece {

	public static final PieceType PIECE_TYPE = PieceType.ROOK;

	public Rook(Color color) {
		super(PIECE_TYPE, color);
	}

	public List<OopMove> generateValidMoves(OopChessBoard b, int rank, int file) {
		List<OopMove> validMoves = new ArrayList<>();
		super.generateValidMovesRecursive(b, rank, file, 0, -1, validMoves, true);
		super.generateValidMovesRecursive(b, rank, file, 0, 1, validMoves, true);
		super.generateValidMovesRecursive(b, rank, file, 1, 0, validMoves, true);
		super.generateValidMovesRecursive(b, rank, file, -1, 0, validMoves, true);
		return validMoves;
	}

}
