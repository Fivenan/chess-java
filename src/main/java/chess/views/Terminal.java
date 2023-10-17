package main.java.chess.views;

import main.java.chess.controllers.Game;
import main.java.chess.models.ChessBoardInterface;

public class Terminal implements IView {

	private Game game = null;
	
	public Terminal() {		
	}

	@Override
	public void show() {
		ChessBoardInterface chessBoard = null;
		if (game != null && (chessBoard = game.getChessBoard()) != null) {
			System.out.println(chessBoard);
		}
	}

	@Override
	public void setGame(Game game) {		
		this.game = game;
	}
}
