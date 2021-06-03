package chess.models.pieces;

public class Bishop extends Piece {

	private boolean isLeftItem = false;
	
	public Bishop(boolean isWhite) {
		super(isWhite, isWhite ? 'B' : 'b', isWhite ? '\u2657' : '\u265d', 3);

	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isLeftItem ? 3 : 6);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
