package main.java.chess.models.oop;

import java.util.Objects;

import main.java.chess.models.pieces.Piece;

public class Tile {

	public static final char EMPTY_TILE_NOTATION = 'x';
	public static final char EMPTY_BLACK_TILE_SYMBOL = '\u2b1b';
	public static final char EMPTY_WHITE_TILE_SYMBOL = '\u2b1c';

	public final int file; // column, x
	public final int rank; // row, y

	private Piece piece;

	public Tile(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}

	public Tile(int rank, int file, Piece p) {
		this(rank, file);
		this.piece = p;
	}

	public char getNotation() {
		return piece == null ? EMPTY_TILE_NOTATION : piece.getNotation();
	}

	public char getSymbol() {
		return piece == null ? getSymbolIfEmpty() : piece.getSymbol();
	}

	private char getSymbolIfEmpty() {
		return (file + rank) % 2 == 0 ? EMPTY_BLACK_TILE_SYMBOL : EMPTY_WHITE_TILE_SYMBOL;
	}

	public boolean isInPosition(int rank, int file) {
		return (rank == this.rank) && (file == this.file);
	}

	/**
	 * Returns the tile on the board
	 * a --> 0, h --> 7
	 * 1 --> 7, 8 --> 0
	 */
	public String getPosition() {
		return ((char) ('a' + file)) + "" + (8 - rank);
	}

	public boolean isEmpty() {
		return piece == null;
	}

	public void clear() {
		this.piece = null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tile other = (Tile) obj;
		return file == other.file && Objects.equals(piece, other.piece) && rank == other.rank;
	}

	public String getFile() {
		return "" + (char) ('a' + file);
	}

	public String getRank() {
		return "" + (8 - rank);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, piece, rank);
	}

}
