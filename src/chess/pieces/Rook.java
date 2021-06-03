package chess.pieces;

public class Rook extends Piece {

	private boolean isLeftItem = false;
	
	public Rook(boolean isWhite) {
		super(isWhite, isWhite ? 'R' : 'r', isWhite ? '\u2656' : '\u265c', 5);

	}

	@Override
	public void resetLocation() {
		pieceLocation.setColumnLoc(isLeftItem ? 1 : 8);
		pieceLocation.setRowLoc(isWhite? 1 : 8);
	}
}
