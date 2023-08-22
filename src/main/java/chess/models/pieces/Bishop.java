package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public class Bishop extends Piece {

	private static final Logger LOGGER = Logger.getLogger(Bishop.class.getName());

	public static final PieceType pieceType = PieceType.BISHOP;

	private boolean isOnBlack = false;

	public Bishop(Color color) {
		super(pieceType, color);

	}

	public List<Tile> generateLegalTargetTiles(OopChessBoard b, int rank, int file) {
		List<Tile> validTargets = new ArrayList<>();
		super.generateValidMovesRecursive(b, rank, file, -1, -1, validTargets);
		super.generateValidMovesRecursive(b, rank, file, -1, 1, validTargets);
		super.generateValidMovesRecursive(b, rank, file, 1, -1, validTargets);
		super.generateValidMovesRecursive(b, rank, file, 1, 1, validTargets);
		return validTargets;
	}


}
