package main.java.chess.models.oop.moves;

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

}
