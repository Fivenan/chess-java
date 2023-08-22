package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public class Rook extends Piece {

	public static final PieceType pieceType = PieceType.ROOK;

	public Rook(Color color) {
		super(pieceType, color);
	}

	public List<Tile> generateLegalTargetTiles(OopChessBoard b, int rank, int file) {
		List<Tile> validTargets = new ArrayList<>();
		super.generateValidMovesRecursive(b, rank, file, 0, -1, validTargets);
		super.generateValidMovesRecursive(b, rank, file, 0, 1, validTargets);
		super.generateValidMovesRecursive(b, rank, file, 1, 0, validTargets);
		super.generateValidMovesRecursive(b, rank, file, -1, 0, validTargets);
		return validTargets;
	}

}
