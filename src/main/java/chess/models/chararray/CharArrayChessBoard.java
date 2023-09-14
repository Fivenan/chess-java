package main.java.chess.models.chararray;

import main.java.chess.models.ChessBoard;
import main.java.chess.models.oop.moves.Move;
import main.java.chess.models.util.BoardUtil;

public class CharArrayChessBoard implements ChessBoard {

	private char[][] tiles;

	public CharArrayChessBoard() {

	}

	public CharArrayChessBoard(String fen) {
		String refreshedFEN = BoardUtil.refreshFEN(fen);
		tiles = BoardUtil.fenToCharArray2d(refreshedFEN);
	}

	@Override
	public void setBoard(String fen) {
		// TODO Auto-generated method stub

	}

	@Override
	public void emptyBoard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	public boolean isGameOver() {
		return false;
	}

	@Override
	public void moveTo(String endPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void apply(Move move) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getMoveNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFEN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFENBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
