package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.Move;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public class Bishop extends Piece {

	private static final Logger LOGGER = Logger.getLogger(Bishop.class.getName());

	public static final PieceType pieceType = PieceType.BISHOP;

	private boolean isOnBlack = false;

	public Bishop(Color color) {
		super(pieceType, color);

	}

	public List<Move> generateLegalMoves(OopChessBoard b, int rank, int file) {
		List<Tile> validTargets = new ArrayList<>();
		generateValidMovesRecursive(b, rank, file, -1, -1, validTargets);
		generateValidMovesRecursive(b, rank, file, -1, 1, validTargets);
		generateValidMovesRecursive(b, rank, file, 1, -1, validTargets);
		generateValidMovesRecursive(b, rank, file, 1, 1, validTargets);
		Tile start = b.getTile(rank, file);
		return validTargets.stream().map(end -> new Move(start, end)).collect(Collectors.toList());
	}

	private void generateValidMovesRecursive(OopChessBoard b, int rank, int file, int dr, int df,
			List<Tile> validMoves) {
		int toRank = rank + dr;
		int toFile = file + df;
		if (!isInTheBoard(toRank, toFile) || pieceExistsAndSameColor(b, toRank, toFile)) {
			return;
		}
		if (pieceExistAndDifferentColor(b, toRank, toFile)) {
			validMoves.add(b.getTile(toRank, toFile));
			return;
		}
		// TODO creates open check
		// TODO checks the opponent's king

		validMoves.add(b.getTile(toRank, toFile));
		generateValidMovesRecursive(b, toRank, toFile, dr, df, validMoves);
	}

	private boolean isInTheBoard(int rank, int file) {
		return rank >= 0 && rank <= 7 && file >= 0 && file <= 7;
	}

	private boolean pieceExistsAndSameColor(OopChessBoard b, int rank, int file) {
		if (b.getTile(rank, file).getPiece() == null) {
			return false;
		}
		return b.getTile(rank, file).getPiece().color == super.color;
	}

	private boolean pieceExistAndDifferentColor(OopChessBoard b, int rank, int file) {
		if (b.getTile(rank, file).getPiece() == null) {
			return false;
		}
		return b.getTile(rank, file).getPiece().color != super.color;
	}

}
