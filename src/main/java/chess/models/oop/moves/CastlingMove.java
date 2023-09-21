package main.java.chess.models.oop.moves;

import java.util.List;

import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public class CastlingMove extends Move {

	Tile rookStartTile;
	Tile rookEndTile;

	Move rookMove;

	public CastlingMove(OopChessBoard b, Tile start, Tile end) {
		super(start, end);
		if (end.file - start.file > 0) {
			rookStartTile = b.getTile(end.rank, 0);
			rookEndTile = b.getTile(end.rank, end.file + 1);
		} else {
			rookStartTile = b.getTile(end.rank, 7);
			rookEndTile = b.getTile(end.rank, end.file - 1);
		}
		rookMove = new NormalMove(rookStartTile, rookEndTile);
	}

	@Override
	public void apply(OopChessBoard b) {
		getEnd().setPiece(getMovingPiece());
		getStart().clear();
		rookEndTile.setPiece(rookStartTile.getPiece());
		rookStartTile.clear();
		b.hasCastled();
	}

	public Tile getRookStartTile() {
		return rookStartTile;
	}

	public Move getRookMove() {
		return rookMove;
	}

	@Override
	public String getNotation(List<Move> otherMovesWithSameTarget) {
		return (rookStartTile.file == 0) ? "O-O-O" : "O-O";
	}

}
