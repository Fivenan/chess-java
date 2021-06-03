package chess.models.pieces;

import chess.models.Board;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite,
                isWhite ? 'N' : 'n',
                isWhite ? '\u2658' : '\u265e',
                3);

	}

	public List<Integer[]> movesFrom(Board b, int x, int y) {
//        boolean filledWithSameColor = b.tiles[x][y].piece.isWhite != super.isWhite;
		List<Integer[]> res = new ArrayList<>();
		if (y < 7 && x < 6 && (b.tiles[x + 2][y + 1].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x + 2, y + 1});
		if (y > 0 && x < 6 && (b.tiles[x + 2][y - 1].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x + 2, y - 1});
		if (y < 7 && x > 1 && (b.tiles[x - 2][y + 1].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x - 2, y + 1});
		if (y > 0 && x > 1 && (b.tiles[x - 2][y - 1].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x - 2, y - 1});
		if (y < 6 && x < 7 && (b.tiles[x + 1][y + 2].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x + 1, y + 2});
		if (y > 1 && x < 7 && (b.tiles[x + 1][y - 2].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x + 1, y - 2});
		if (y < 6 && x > 0 && (b.tiles[x - 1][y + 2].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x - 1, y + 2});
		if (y > 1 && x > 0 && (b.tiles[x - 1][y - 2].piece.isWhite != super.isWhite))
			res.add(new Integer[]{x - 1, y - 2});

		return res;
	}

//	@Override
//	public void resetLocation() {
//		pieceLocation.setColumnLoc(isLeftItem ? 2 : 7);
//		pieceLocation.setRowLoc(isWhite? 1 : 8);
//	}
}
