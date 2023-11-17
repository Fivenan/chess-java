package main.java.chess.models.oop.moves;

import java.util.List;
import java.util.Objects;

import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.Piece;

public class CapturingMove extends OopMove {

	private Piece capturedPiece;
	private Tile capturedTile;

	public CapturingMove(Tile start, Tile end) {
		super(start, end);
		this.capturedPiece = end.getPiece();
		this.capturedTile = end;
	}

	@Override
	public void apply(OopChessBoard b) {
		capturedTile.clear();
		getEnd().setPiece(getMovingPiece());
		getStart().clear();
		b.resetHalfmoveClock();
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public void setCapturedPiece(Piece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}

	public Tile getCapturedTile() {
		return capturedTile;
	}

	public void setCapturedTile(Tile capturedTile) {
		this.capturedTile = capturedTile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capturedPiece, capturedTile, getMovingPiece());
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
		CapturingMove other = (CapturingMove) obj;
		return Objects.equals(capturedPiece, other.capturedPiece) && Objects.equals(capturedTile, other.capturedTile)
				&& Objects.equals(getMovingPiece(), other.getMovingPiece());
	}

	@Override
	public String getNotation(List<OopMove> otherMovesWithSameTarget) { // Nexg5 Ng4x5
		StringBuilder res = new StringBuilder();
		Piece piece = getStart().getPiece();
		if (!(piece instanceof Pawn)) {
			res.append(piece.getNotation());
		}
		res.append('x');
		res.append(getEnd().getNotation());
		return res.toString();
	}

}
