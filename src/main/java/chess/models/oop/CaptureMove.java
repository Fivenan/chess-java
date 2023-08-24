package main.java.chess.models.oop;

import java.util.Objects;

import main.java.chess.models.pieces.Piece;

public class CaptureMove extends Move {

	private Piece movingPiece;
	private Piece capturedPiece;
	private Tile capturedTile;

	public CaptureMove(Tile start, Tile end) {
		super(start, end);
		this.movingPiece = start.getPiece();
		this.capturedPiece = end.getPiece();
		this.capturedTile = end;
	}

	public Piece getMovingPiece() {
		return movingPiece;
	}

	public void setMovingPiece(Piece movingPiece) {
		this.movingPiece = movingPiece;
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
		return Objects.hash(capturedPiece, capturedTile, movingPiece);
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
		CaptureMove other = (CaptureMove) obj;
		return Objects.equals(capturedPiece, other.capturedPiece) && Objects.equals(capturedTile, other.capturedTile)
				&& Objects.equals(movingPiece, other.movingPiece);
	}

}
