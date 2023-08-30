package main.java.chess.models.oop.moves;

import java.util.List;
import java.util.Objects;

import main.java.chess.models.enums.Color;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.Piece;

public abstract class Move {

	private Tile start;
	private Tile end;

	public Move(Tile start, Tile end) {
		this.start = start;
		this.end = end;
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

	public static String print(OopChessBoard b, Move m) {
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

	public String toString() {
		return "[" + start.getPosition() + ", " + end.getPosition() + "]";
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
		Move other = (Move) obj;
		return Objects.equals(end, other.end)
				&& Objects.equals(start, other.start);
	}

	public static String printMoveInTurn(int moveNumber, Move white, Move black) {
		return null;
	}

	public static boolean isLegalMove(Move move, OopChessBoard board) {
		Tile start = board.getTile(move.getStart().getPosition());
		List<Move> validMoves = board.getTile(move.getStart().rank, move.getStart().file).getPiece()
				.generateValidMoves(board, start.rank, start.file);
		return validMoves.contains(move);
	}


}
