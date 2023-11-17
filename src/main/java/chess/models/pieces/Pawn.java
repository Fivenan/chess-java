package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.CapturingMove;
import main.java.chess.models.oop.moves.EnPassantMove;
import main.java.chess.models.oop.moves.OopMove;
import main.java.chess.models.oop.moves.NormalMove;
import main.java.chess.models.oop.moves.PawnTwoStepMove;
import main.java.chess.models.oop.moves.PromotionCapturingMove;
import main.java.chess.models.oop.moves.PromotionMove;

public class Pawn extends Piece {

	public static final PieceType PIECE_TYPE = PieceType.PAWN;

	public Pawn(Color color) {
		super(PIECE_TYPE, color);
	}

	@Override
	public List<OopMove> generateValidMoves(OopChessBoard b, int rank, int file) {

		List<OopMove> validMoves = new ArrayList<>();
		OopMove tmp;
		if (canCaptureWestFile(b, rank, file)) {
			tmp = new CapturingMove(b.getTile(rank, file), b.getTile(rank + movingDirection(), file - 1));
			if (canPromote(rank)) {
				// TODO consider the promotion while capturing case
				tmp = new PromotionCapturingMove(b.getTile(rank, file), b.getTile(rank + movingDirection(), file + 1));
			}
			validMoves.add(tmp);
		}
		if (canCaptureEastFile(b, rank, file)) {
			tmp = new CapturingMove(b.getTile(rank, file), b.getTile(rank + movingDirection(), file + 1));
			if (canPromote(rank)) {
				tmp = new PromotionCapturingMove(b.getTile(rank, file), b.getTile(rank + movingDirection(), file + 1));
			}
			validMoves.add(tmp);
		}
		if (b.getTile(rank + movingDirection(), file).isEmpty()) {
			tmp = new NormalMove(b.getTile(rank, file), b.getTile(rank + movingDirection(), file));
			if (canPromote(rank)) {
				tmp = new PromotionMove(b.getTile(rank, file), b.getTile(rank + movingDirection(), file + 1));
			}
			validMoves.add(tmp);
			if (isInInitialPosition(rank) && b.getTile(rank + 2 * movingDirection(), file).isEmpty()) {
				tmp = new PawnTwoStepMove(b, b.getTile(rank, file), b.getTile(rank + 2 * movingDirection(), file));
				validMoves.add(tmp);
			}
		}
		if (canEnPassant(b, rank, file)) {
			tmp = new EnPassantMove(b, b.getTile(rank, file), b.getEnPassantTargetTile());
			validMoves.add(tmp);
		}
		

		return validMoves;
	}

	public boolean isInInitialPosition(int rank) {
		return ((rank == 1 && color == Color.BLACK) || (rank == 6 && color == Color.WHITE));
	}

	private int movingDirection() {
		return this.color == Color.WHITE ? -1 : 1;
	}

	private boolean canCaptureWestFile(OopChessBoard b, int rank, int file) {
		if (file == 0) {
			return false;
		}
		Tile targetTile = b.getTile(rank + movingDirection(), file - 1);
		return !targetTile.isEmpty() && (targetTile.getPiece().color != this.color);
	}

	private boolean canCaptureEastFile(OopChessBoard b, int rank, int file) {
		if (file == 7) {
			return false;
		}
		Tile targetTile = b.getTile(rank + movingDirection(), file + 1);
		return !targetTile.isEmpty() && (targetTile.getPiece().color != this.color);
	}

	private boolean canEnPassant(OopChessBoard b, int rank, int file) {
		Tile targetTile = b.getEnPassantTargetTile();
		if (targetTile == null || !targetTile.isEmpty()) {
			return false;
		}
		return targetTile.rank == rank + movingDirection() && (targetTile.file == file - 1 || targetTile.file == file + 1);
	}

	@Override
	public boolean canPromote(int rank) {
		return ((rank == 6 && color == Color.BLACK) && (rank == 1 && color == Color.WHITE));
	}

	public Piece promoteInto(PieceType pt) {
		return PieceFactory.create(pt, color);
	}


}
