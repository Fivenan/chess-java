package chess.pieces;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite,
                isWhite ? 'B' : 'b',
                isWhite ? '\u2657' : '\u265d',
                3);

    }
}
