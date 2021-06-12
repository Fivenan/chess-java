package chess.controllers;

import chess.models.Board;
import chess.views.IView;

public class Game {
	private Board board = new Board();
	private IView viewer;
	
	public Game() {
		
	}
	
	public Game(Board board) {
		setBoard(board);
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setViewer(IView viewer) {
		this.viewer = viewer;
	}
	
	public void printBoard() {
		if (viewer != null) {
			viewer.show();
		}
	}
}
