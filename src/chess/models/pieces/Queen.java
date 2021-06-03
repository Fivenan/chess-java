package chess.models.pieces;

public class Queen extends Piece {

	public Queen(boolean isWhite) {
		super(isWhite, isWhite ? 'Q' : 'q', isWhite ? '\u2655' : '\u265b', 9);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isWhite? 5 : 4);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
