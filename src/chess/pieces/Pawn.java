package chess.pieces;

public class Pawn extends Piece {

	private boolean isLeftItem = false;
	
	public Pawn(boolean isWhite) {
		super(isWhite, isWhite ? 'P' : 'p', isWhite ? '\u2659' : '\u265f', 1);

	}

	@Override
	public void resetLocation() {
		if (isWhite) {

		} else {

		}
	}
}
