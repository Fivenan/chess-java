package main.java.chess.models.oop;

import main.java.chess.models.enums.Color;

public class EnPassantMove extends CaptureMove {

	public EnPassantMove(Tile start, Tile end, OopChessBoard b) {
		super(start, end);
		super.setCapturedTile(b.getTile(end.rank + dr(), end.file));
		super.setCapturedPiece(getCapturedPiece());
	}

	private int dr() {
		return getMovingPiece().color == Color.WHITE ? 1 : -1;
	}

}
