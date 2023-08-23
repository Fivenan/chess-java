package main.java.chess.models.pieces;

import java.util.List;
import java.util.stream.Collectors;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.Move;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public abstract class Piece {

	public final PieceType pieceType;
	public final Color color;

	public Piece(PieceType pieceType, Color color) {
		this.pieceType = pieceType;
		this.color = color;
	}

	public char getNotation() {
		return pieceType.getNotation(color);
	}

	public char getSymbol() {
		return pieceType.getSymbol(color);
	}

	public int getValue() {
		return pieceType.getValue();
	}

	public List<Move> generateLegalMoves(OopChessBoard b, int rank, int file) {
		Tile start = b.getTile(rank, file);
		return generateLegalTargetTiles(b, rank, file).stream() //
				.map(end -> new Move(start, end)) //
				.collect(Collectors.toList());
	}

	public abstract List<Tile> generateLegalTargetTiles(OopChessBoard b, int rank, int file);

	void generateValidMovesRecursive(OopChessBoard b, int rank, int file, int dr, int df,
			List<Tile> validMoves) {
		int toRank = rank + dr;
		int toFile = file + df;
		if (!isInTheBoard(toRank, toFile)) {
			return;
		}
		if (pieceExists(b, toRank, toFile)) {
			if (pieceHasDifferentColor(b, toRank, toFile)) {
				validMoves.add(b.getTile(toRank, toFile));
			}
			return;
		}
		// TODO creates open check
		// TODO checks the opponent's king

		validMoves.add(b.getTile(toRank, toFile));
		generateValidMovesRecursive(b, toRank, toFile, dr, df, validMoves);
	}

	private boolean isInTheBoard(int rank, int file) {
		return rank >= 0 //
				&& rank <= 7 //
				&& file >= 0 //
				&& file <= 7;
	}

	private boolean pieceExists(OopChessBoard b, int rank, int file) {
		return b.getTile(rank, file).getPiece() != null;
	}

	private boolean pieceHasDifferentColor(OopChessBoard b, int rank, int file) {
		return b.getTile(rank, file).getPiece().color != color;
	}

	public boolean canPromote(int rank) {
		return false;
	}

}
