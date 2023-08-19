package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public class Knight extends Piece {

	public static final PieceType pieceType = PieceType.KNIGHT;

	public Knight(Color color) {
		super(pieceType, color);

	}

	public List<Integer[]> movesFrom(OopChessBoard b, int rank, int file) {
//        boolean filledWithSameColor = b.tiles[x][y].piece.isWhite != super.isWhite;
		List<Integer[]> res = new ArrayList<>();
		if (targetIsValid(b, file + 2, rank + 1))
			res.add(new Integer[] { rank + 2, file + 1 });
		if (targetIsValid(b, file + 2, rank - 1))
			res.add(new Integer[] { rank + 2, file - 1 });
		if (targetIsValid(b, file - 2, rank + 1))
			res.add(new Integer[] { rank - 2, file + 1 });
		if (targetIsValid(b, file - 2, rank - 1))
			res.add(new Integer[] { rank - 2, file - 1 });
		if (targetIsValid(b, file + 1, rank + 2))
			res.add(new Integer[] { rank + 1, file + 2 });
		if (targetIsValid(b, file + 1, rank - 2))
			res.add(new Integer[] { rank + 1, file - 2 });
		if (targetIsValid(b, file - 1, rank + 2))
			res.add(new Integer[] { rank - 1, file + 2 });
		if (targetIsValid(b, file - 1, rank - 2))
			res.add(new Integer[] { rank - 1, file - 2 });

		return res;
	}

	private boolean targetIsValid(OopChessBoard b, int rank, int file) {
		return targetTileInBound(rank, file) && targetTileNotSameColor(b, rank, file);
	}

	private boolean targetTileInBound(int rank, int file) {
		boolean fileInBound = file >= 0 && file < 8;
		boolean rankInBound = rank >= 0 && rank < 8;
		return fileInBound && rankInBound;
	}

	private boolean targetTileNotSameColor(OopChessBoard b, int rank, int file) {
		Tile targetTile = b.getTile(rank, file);
		if (targetTile.getPiece() == null) {
			return true;
		}
		return b.getTile(rank, file).getPiece().color != super.color;
	}

//	private boolean causesDiscoveredCheck(OopChessBoard b, int rank, int file);

}
