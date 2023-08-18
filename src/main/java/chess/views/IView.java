package main.java.chess.views;

import main.java.chess.controllers.Game;

public interface IView {

	public void setGame(Game game);
	public void show();
}
