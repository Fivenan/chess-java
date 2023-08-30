package main.java.chess.models.oop;

import lombok.Getter;
import lombok.Setter;
import main.java.chess.models.pieces.Piece;

public class Tile {

	public static final char EMPTY_TILE_NOTATION = 'x';
	public static final char EMPTY_BLACK_TILE_SYMBOL = '\u2b1b';
	public static final char EMPTY_WHITE_TILE_SYMBOL = '\u2b1c';

	public final int file; // column, x
	public final int rank; // row, y

	@Getter
	@Setter
	private Piece piece;


	public Tile(int rank, int file) {
		this.rank = rank;
		this.file = file;
    }

	public Tile(int rank, int file, Piece p) {
		this(rank, file);
        this.piece = p;
    }

    public void place(Piece p) {
        this.piece = p;
    }

	public boolean isEmpty() {
		return piece == null;
	}

	public void clear() {
        this.piece = null;
    }

	public char getSymbol() {
        return piece == null ?
				(file + rank) % 2 == 0 ? EMPTY_BLACK_TILE_SYMBOL : EMPTY_WHITE_TILE_SYMBOL
				:
				piece.getSymbol();
    }

	public char getNotation() {
		return piece == null ? EMPTY_TILE_NOTATION : piece.getNotation();
	}

	/**
	 * Returns the tile on the board
	 * a --> 0, h --> 7
	 * 1 --> 7, 8 --> 0
	 */
	public String getPosition() {
//		System.out.println("Tile: [rank: " + rank + "], [file: " + file + "]");
		return ((char) ('a' + file)) + "" + (8 - rank);
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
		return file == other.file && rank == other.rank;
	}

}
