package main.java.chess.views;

import main.java.chess.controllers.Game;
import main.java.chess.models.Board;

public class Terminal implements IView {

	private Game game = null;
	
	public Terminal() {		
	}

	@Override
	public void show() {
		Board board = null;
		if (game != null && (board = game.getBoard()) != null) {
			System.out.println(board);
		}
	}

	@Override
	public void setGame(Game game) {		
		this.game = game;
	}
}
