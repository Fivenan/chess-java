package chess.pieces;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite,
                isWhite ? 'P' : 'p',
                isWhite ? '\u2659' : '\u265f',
                1);

    }
}
