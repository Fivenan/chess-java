package main.java.chess.models.oop.moves;

import main.java.chess.models.enums.Color;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public class EnPassantMove extends CapturingMove {

	public EnPassantMove(OopChessBoard b, Tile start, Tile end) {
		super(start, end);
		super.setCapturedTile(b.getTile(end.rank - dr(), end.file));
		super.setCapturedPiece(getCapturedPiece());
	}

	private int dr() {
		return getMovingPiece().color == Color.WHITE ? -1 : 1;
	}
}
