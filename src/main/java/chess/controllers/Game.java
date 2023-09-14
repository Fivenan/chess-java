package main.java.chess.controllers;

import main.java.chess.models.ChessBoard;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.views.IView;

public class Game {
	private ChessBoard chessBoard = new OopChessBoard();
	private IView viewer;

	public Game() {

	}

	public Game(ChessBoard board) {
		setBoard(board);
		while (!board.isGameOver()) {

		}
	}

	public void setBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public void setViewer(IView viewer) {
		this.viewer = viewer;
		if (viewer != null) {
			viewer.setGame(this);
		}
	}

	public void printBoard() {
		if (viewer != null) {
			viewer.show();
		}
	}
}
