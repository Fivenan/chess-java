package chess.pieces;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite,
                isWhite ? 'N' : 'n',
                isWhite ? '\u2658' : '\u265e',
                3);

    }
}
