package chess.pieces;

public class Knight extends Piece {
	
	private boolean isLeftItem = false;
	
	public Knight(boolean isWhite) {
		super(isWhite, isWhite ? 'N' : 'n', isWhite ? '\u2658' : '\u265e', 3);

	}

	@Override
	public void resetLocation() {
		pieceLocation.setColumnLoc(isLeftItem ? 2 : 7);
		pieceLocation.setRowLoc(isWhite? 1 : 8);
	}
}
