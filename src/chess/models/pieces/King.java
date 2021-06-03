package chess.models.pieces;

public class King extends Piece {
	
	public King(boolean isWhite) {
		super(isWhite, isWhite ? 'K' : 'k', isWhite ? '\u2654' : '\u265a', 999);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isWhite? 4 : 5);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
