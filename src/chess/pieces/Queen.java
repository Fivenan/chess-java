package chess.pieces;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite,
                isWhite ? 'Q' : 'q',
                isWhite ? '\u2655' :'\u265b',
                9);

    }
}
