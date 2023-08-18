package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.Board;
import main.java.chess.models.enums.Color;

public class Knight extends Piece {
	public Knight(Color color) {
		super(color, color == Color.WHITE ? 'N' : 'n', color == Color.WHITE ? '\u2658' : '\u265e', 3);

	}

	public List<Integer[]> movesFrom(Board b, int x, int y) {
//        boolean filledWithSameColor = b.tiles[x][y].piece.isWhite != super.isWhite;
		List<Integer[]> res = new ArrayList<>();
		if (y < 7 && x < 6 && (b.tiles[x + 2][y + 1].piece.color != super.color))
			res.add(new Integer[] { x + 2, y + 1 });
		if (y > 0 && x < 6 && (b.tiles[x + 2][y - 1].piece.color != super.color))
			res.add(new Integer[] { x + 2, y - 1 });
		if (y < 7 && x > 1 && (b.tiles[x - 2][y + 1].piece.color != super.color))
			res.add(new Integer[] { x - 2, y + 1 });
		if (y > 0 && x > 1 && (b.tiles[x - 2][y - 1].piece.color != super.color))
			res.add(new Integer[] { x - 2, y - 1 });
		if (y < 6 && x < 7 && (b.tiles[x + 1][y + 2].piece.color != super.color))
			res.add(new Integer[] { x + 1, y + 2 });
		if (y > 1 && x < 7 && (b.tiles[x + 1][y - 2].piece.color != super.color))
			res.add(new Integer[] { x + 1, y - 2 });
		if (y < 6 && x > 0 && (b.tiles[x - 1][y + 2].piece.color != super.color))
			res.add(new Integer[] { x - 1, y + 2 });
		if (y > 1 && x > 0 && (b.tiles[x - 1][y - 2].piece.color != super.color))
			res.add(new Integer[] { x - 1, y - 2 });

		return res;
	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isLeftItem ? 2 : 7);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
