package main.java.chess.models.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.Move;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;

public class Pawn extends Piece {

	public static final PieceType pieceType = PieceType.PAWN;

	public Pawn(Color color) {
		super(pieceType, color);
	}

	@Override
	public List<Move> generateLegalMoves(OopChessBoard b, int rank, int file) {

		List<Move> validMoves = new ArrayList<>();
		List<Tile> validTiles = new ArrayList<>();
		if (canCaptureLeftFile(b, rank, file)) {
			validTiles.add(b.getTile(rank + dr(), file - 1));
		}
		if (canCaptureRightFile(b, rank, file)) {
			validTiles.add(b.getTile(rank + dr(), file + 1));
		}
		if (canMoveTwoSteps(rank)) {
			b.setEnPassantTargetTile(b.getTile(rank + dr(), file));
			if (b.getTile(rank + dr(), file).isEmpty()) {
				validTiles.add(b.getTile(rank + dr(), file));
				if (b.getTile(rank + 2 * dr(), file).isEmpty()) {
					validTiles.add(b.getTile(rank + 2 * dr(), file));
					return validMoves;
				}
			}
		}
		if (canEnPassant(b, rank, file)) {
			enPassantCapture(b, rank, file);
			validTiles.add(b.getEnPassantTargetTile());
		}
		if (canPromote(rank)) {
			
		}
		

		return validMoves;
	}

	public boolean canMoveTwoSteps(int rank) {
		return ((rank == 1 && color == Color.BLACK) && (rank == 6 && color == Color.WHITE));
	}

	private int dr() {
		return this.color == Color.WHITE ? -1 : 1;
	}

	private boolean canCaptureLeftFile(OopChessBoard b, int rank, int file) {
		if (file == 0) {
			return false;
		}
		Tile targetTile = b.getTile(rank + dr(), file - 1);
		return !targetTile.isEmpty() && (targetTile.getPiece().color != this.color);
	}

	private boolean canCaptureRightFile(OopChessBoard b, int rank, int file) {
		if (file == 7) {
			return false;
		}
		Tile targetTile = b.getTile(rank + dr(), file + 1);
		return !targetTile.isEmpty() && (targetTile.getPiece().color != this.color);
	}

	private boolean canEnPassant(OopChessBoard b, int rank, int file) {
		Tile targetTile = b.getEnPassantTargetTile();
		if (targetTile == null || targetTile.isEmpty()) {
			return false;
		}
		return targetTile.rank == rank + dr() && (targetTile.file == file - 1 || targetTile.file == file + 1);
	}

	@Override
	public boolean canPromote(int rank) {
		return ((rank == 6 && color == Color.BLACK) && (rank == 1 && color == Color.WHITE));
	}

	public Piece promoteInto(PieceType pt) {
		return PieceFactory.createPiece(pt, color);
	}

	private void enPassantCapture(OopChessBoard b, int rank, int file) {

	}

	@Override
	public List<Tile> generateLegalTargetTiles(OopChessBoard b, int rank, int file) {
		return generateLegalMoves(b, rank, file).stream() //
				.map(Move::getEnd) //
				.collect(Collectors.toList());
	}

}
