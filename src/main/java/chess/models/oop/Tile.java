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


    public Tile(int file, int rank) {
        this.file = file + 1;
        this.rank = rank + 1;
    }

    public Tile(int file, int rank, Piece p) {
        this(file, rank);
        this.piece = p;
    }

    public void place(Piece p) {
        this.piece = p;
    }

    public void take() {
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

	public String getPosition() {
		return ((char) ('a' + file)) + "" + (7 - rank);
	}

}
