package chess.pieces;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite,
                isWhite ? 'R' : 'r',
                isWhite ? '\u2656' : '\u265c',
                5);

    }
}
