package main.java.chess.models.oop.moves;

import java.util.Objects;

import main.java.chess.models.enums.Color;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Piece;

public class PawnTwoStepMove extends Move {

	Tile enPassantTile;

	public PawnTwoStepMove(OopChessBoard b, Tile start, Tile end) {
		super(start, end);
		enPassantTile = b.getTile(start.rank + movingDirection(start), start.file);
	}

	private int movingDirection(Tile t) {
		return getMovingPiece(t).color == Color.WHITE ? -1 : 1;
	}

	private Piece getMovingPiece(Tile start) {
		return start.getPiece();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PawnTwoStepMove other = (PawnTwoStepMove) obj;
		return Objects.equals(enPassantTile, other.enPassantTile);
	}

	@Override
	public String getNotation() {
		return String.valueOf(getEnd().getNotation());
	}

}
