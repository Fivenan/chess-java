package chess.models;

import chess.models.pieces.Piece;

public class Tile {
    public final int x;
    public final int y;
    public Piece piece;


    public Tile(int x, int y) {
        this.x = x + 1;
        this.y = y + 1;
    }

    public Tile(int x, int y, Piece p) {
        this(x, y);
        this.piece = p;
    }

    public void place(Piece p) {
        this.piece = p;
    }

    public void take() {
        this.piece = null;
    }

    public char symbol() {
        return piece == null ?
                (x + y) % 2 == 0 ? '\u2b1b' : '\u2b1c' :
                piece.symbol;
    }


}
