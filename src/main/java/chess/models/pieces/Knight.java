package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.CapturingMove;
import main.java.chess.models.oop.moves.OopMove;
import main.java.chess.models.oop.moves.NormalMove;

public class Knight extends Piece {

	public static final PieceType PIECE_TYPE = PieceType.KNIGHT;

	public Knight(Color color) {
		super(PIECE_TYPE, color);

	}

	public List<OopMove> generateValidMoves(OopChessBoard b, int rank, int file) {
		List<OopMove> validMoves = new ArrayList<>();
		validMoves.add(getMove(b, rank, file, rank + 2, file + 1));
		validMoves.add(getMove(b, rank, file, rank + 2, file - 1));
		validMoves.add(getMove(b, rank, file, rank - 2, file + 1));
		validMoves.add(getMove(b, rank, file, rank - 2, file - 1));
		validMoves.add(getMove(b, rank, file, rank + 1, file + 2));
		validMoves.add(getMove(b, rank, file, rank + 1, file - 2));
		validMoves.add(getMove(b, rank, file, rank - 1, file + 2));
		validMoves.add(getMove(b, rank, file, rank - 1, file - 2));
		return validMoves;
	}

	private boolean targetTileInBound(int rank, int file) {
		boolean fileInBound = file >= 0 && file < 8;
		boolean rankInBound = rank >= 0 && rank < 8;
		return fileInBound && rankInBound;
	}

	private OopMove getMove(OopChessBoard b, int rank, int file, int toRank, int toFile) {
		if (!targetTileInBound(toRank, toFile)) {
			return null;
		}
		Tile start = b.getTile(rank, file);
		Tile end = b.getTile(toRank, toFile);
		if (end.isEmpty()) {
			return new NormalMove(start, end);
		} else if (end.getPiece().color != super.color) {
			return new CapturingMove(start, end);
		} else {
			return null;
		}

	}

//	private boolean causesDiscoveredCheck(OopChessBoard b, int rank, int file);

}
