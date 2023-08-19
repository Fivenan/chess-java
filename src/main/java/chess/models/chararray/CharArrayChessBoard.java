package main.java.chess.models.chararray;

import main.java.chess.models.ChessBoard;
import main.java.chess.models.oop.Move;
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
	public void restartBoard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(String endPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Move move) {
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