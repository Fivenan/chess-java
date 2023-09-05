package main.java.chess.models.oop.moves;

import java.util.List;

import main.java.chess.models.oop.Tile;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.Piece;

public class NormalMove extends Move {

	public NormalMove(Tile start, Tile end) {
		super(start, end);
	}

	@Override
	public String getNotation(List<Move> otherMovesWithSameTarget) {
		StringBuilder res = new StringBuilder();
		Piece piece = getStart().getPiece();
		if (!(piece instanceof Pawn)) {
			res.append(piece.getNotation());
		}
		res.append(getSpecifyingTile(otherMovesWithSameTarget));
		res.append(getEnd().getPosition());
		return res.toString();
	}

	public String getSpecifyingTile(List<Move> otherMovesWithSameTarget) {
		String specificFile = "";
		String specificRank = "";
		for (Move otherMove : otherMovesWithSameTarget) {
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
}

