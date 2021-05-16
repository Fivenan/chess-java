package chess.pieces;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite,
                isWhite ? 'K' : 'k',
                isWhite ? '\u2654' : '\u265a',
                999);

    }
}
