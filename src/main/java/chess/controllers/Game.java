package main.java.chess.controllers;

import main.java.chess.models.ChessBoardInterface;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.views.IView;

public class Game {
	private ChessBoardInterface chessBoard = new OopChessBoard();
	private IView viewer;

	public Game() {

	}

//	public static void main(String[] args) {
//
//	}

	public Game(ChessBoardInterface board) {
		setBoard(board);
		while (!board.isGameOver()) {

		}
	}

	public void setBoard(ChessBoardInterface chessBoard) {
		this.chessBoard = chessBoard;
	}

	public ChessBoardInterface getChessBoard() {
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
