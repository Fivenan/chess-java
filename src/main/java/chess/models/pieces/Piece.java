package main.java.chess.models.pieces;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.CapturingMove;
import main.java.chess.models.oop.moves.OopMove;
import main.java.chess.models.oop.moves.NormalMove;

public abstract class Piece {

	public final PieceType pieceType;
	public final Color color;

	public Piece(PieceType pieceType, Color color) {
		this.pieceType = pieceType;
		this.color = color;
	}

	public char getNotation() {
		return pieceType.getNotation(color);
	}

	public char getSymbol() {
		return pieceType.getSymbol(color);
	}

	public int getValue() {
		return pieceType.getValue();
	}

	public abstract List<OopMove> generateValidMoves(OopChessBoard b, int rank, int file);

	public List<Tile> generateValidTargetTiles(OopChessBoard b, int rank, int file) {
		return generateValidMoves(b, rank, file).stream() //
				.map(OopMove::getEnd) //
				.collect(Collectors.toList());
	}

	void generateValidMovesRecursive(OopChessBoard b, int rank, int file, int dr, int df,
			List<OopMove> validMoves, boolean recurse) {
		OopMove tmp;
		int toRank = rank + dr;
		int toFile = file + df;
		if (!isInTheBoard(toRank, toFile)) {
			return;
		}
		if (pieceExists(b, toRank, toFile)) {
			if (pieceHasDifferentColor(b, toRank, toFile)) {
				tmp = new CapturingMove(b.getTile(rank, file), b.getTile(toRank, toFile));
				validMoves.add(tmp);
			}
			return;
		}
		// TODO creates open check
		// TODO checks the opponent's king

		tmp = new NormalMove(b.getTile(rank, file), b.getTile(toRank, toFile));
		validMoves.add(tmp);
		if (recurse) {
			generateValidMovesRecursive(b, toRank, toFile, dr, df, validMoves, recurse);
		}
	}

	private boolean isInTheBoard(int rank, int file) {
		return rank >= 0 //
				&& rank <= 7 //
				&& file >= 0 //
				&& file <= 7;
	}

	private boolean pieceExists(OopChessBoard b, int rank, int file) {
		return b.getTile(rank, file).getPiece() != null;
	}

	private boolean pieceHasDifferentColor(OopChessBoard b, int rank, int file) {
		return b.getTile(rank, file).getPiece().color != color;
	}

	public boolean canPromote(int rank) {
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, pieceType);
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
		Piece other = (Piece) obj;
		return color == other.color && pieceType == other.pieceType;
	}

}
