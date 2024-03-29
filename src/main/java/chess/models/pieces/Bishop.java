package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.moves.OopMove;

public class Bishop extends Piece {

	private static final Logger LOGGER = Logger.getLogger(Bishop.class.getName());

	public static final PieceType PIECE_TYPE = PieceType.BISHOP;

	private boolean isOnBlack = false;

	public Bishop(Color color) {
		super(PIECE_TYPE, color);

	}

	public List<OopMove> generateValidMoves(OopChessBoard b, int rank, int file) {
		List<OopMove> validMoves = new ArrayList<>();
		super.generateValidMovesRecursive(b, rank, file, -1, -1, validMoves, true);
		super.generateValidMovesRecursive(b, rank, file, -1, 1, validMoves, true);
		super.generateValidMovesRecursive(b, rank, file, 1, -1, validMoves, true);
		super.generateValidMovesRecursive(b, rank, file, 1, 1, validMoves, true);
		return validMoves;
	}


}
