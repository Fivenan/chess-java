package main.java.chess.views;

import main.java.chess.controllers.Game;
import main.java.chess.models.ChessBoard;

public class Terminal implements IView {

	private Game game = null;
	
	public Terminal() {		
	}

	@Override
	public void show() {
		ChessBoard chessBoard = null;
		if (game != null && (chessBoard = game.getChessBoard()) != null) {
			System.out.println(chessBoard);
		}
	}

	@Override
	public void setGame(Game game) {		
		this.game = game;
	}
}
