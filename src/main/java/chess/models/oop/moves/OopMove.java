package main.java.chess.models.oop.moves;

import java.util.List;
import java.util.Objects;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.Piece;

public abstract class OopMove {

	private Piece movingPiece;
	private Tile start;
	private Tile end;

	public OopMove(Tile start, Tile end) {
		this.movingPiece = start.getPiece();
		this.start = start;
		this.end = end;
	}

	public abstract String getNotation(List<OopMove> otherMovesWithSameTarget);

	public String getSpecifyingTile(List<OopMove> otherMovesWithSameTarget) {
		String specificFile = "";
		String specificRank = "";
		for (OopMove otherMove : otherMovesWithSameTarget) {
			if (this.getStart().getPiece().pieceType == otherMove.getStart().getPiece().pieceType) {
				if (otherMove.getStart().file == this.getStart().file) {
					specificFile = this.getStart().getFile();
					continue;
				}
				if (otherMove.getStart().rank == this.getStart().rank) {
					specificRank = this.getStart().getRank();
				}
			}
		}
		return specificFile + specificRank;
	}

	public static String print(OopChessBoard b, OopMove m) {
		Piece p = b.getTile(m.getStart().rank, m.getStart().file).getPiece();
		if (p instanceof Pawn) {
			if ((m.getStart().rank == 6 && p.color == Color.BLACK)
					|| (m.getStart().rank == 1 && p.color == Color.WHITE)) {

			}
		}

		switch (p.pieceType) {
		case ROOK:
		case KNIGHT:
		case BISHOP:
		case QUEEN:
		default:
			;
		}
		return null;
	}

	public abstract void apply(OopChessBoard b);

	public String getMoveString() {
		if (this instanceof CastlingMove) {
			// TODO
		}
		if (this instanceof PromotionMove) {
			// TODO
		}
		StringBuilder res = new StringBuilder();
		if (start.getPiece().pieceType != PieceType.PAWN) {
			res.append(start.getPiece().getNotation());
		}
		if (this instanceof CapturingMove) {
			// TODO
		}
		res.append(end.getPosition());
		return res.toString();
	}

	public Tile getEnPassantTile() {
		return null;
	}

	public static boolean isLegalMove(OopMove move, OopChessBoard board) {
		Tile start = board.getTile(move.getStart().getPosition());
		List<OopMove> validMoves = board.getTile(move.getStart().rank, move.getStart().file).getPiece()
				.generateValidMoves(board, start.rank, start.file);
		return validMoves.contains(move);
	}

	/*
	 *************************************************************************************************************************************************
	 *
	 * GETTER, SETTER, DEFAULT METHODS
	 *
	 ************************************************************************************************************************************************* 
	 */

	public String toString() {
		return "[" + start.getPiece().color.name() + " " + start.getPiece().pieceType.name() + " " + start.getPosition()
				+ ", " + end.getPosition() + "]";
	}

	public String toString(int moveNumber) {
		return "" + moveNumber + ". " + start.getPosition() + " " + end.getPosition();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OopMove other = (OopMove) obj;
		return Objects.equals(end, other.end) && Objects.equals(start, other.start);
	}

	public Piece getMovingPiece() {
		return movingPiece;
	}

	public void setMovingPiece(Piece movingPiece) {
		this.movingPiece = movingPiece;
	}

	public Tile getStart() {
		return start;
	}

	public void setStart(Tile start) {
		this.start = start;
	}

	public Tile getEnd() {
		return end;
	}

	public void setEnd(Tile end) {
		this.end = end;
	}

}
