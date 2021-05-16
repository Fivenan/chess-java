package chess.pieces;

public abstract class Piece {

    public final boolean isWhite;
    public final char notation;
    public final char symbol;
    public final int value;

    public Piece(boolean isWhite, char notation, char symbol, int value) {
        this.isWhite = isWhite;
        this.notation = notation;
        this.symbol = symbol;
        this.value = value;
    }

    public static Piece getPiecefromChar(char c) {
        Piece res = null;
        switch (c) {
            case 'P' -> res = new Pawn(true);
            case 'p' -> res = new Pawn(false);
            case 'R' -> res = new Rook(true);
            case 'r' -> res = new Rook(false);
            case 'N' -> res = new Knight(true);
            case 'n' -> res = new Knight(false);
            case 'B' -> res = new Bishop(true);
            case 'b' -> res = new Bishop(false);
            case 'Q' -> res = new Queen(true);
            case 'q' -> res = new Queen(false);
            case 'K' -> res = new King(true);
            case 'k' -> res = new King(false);
            default -> System.out.println("unrecognizable char");
        }
        return res;
    }

}
