package chess.views;

import chess.controllers.Game;

public interface IView {

	public void setGame(Game game);
	public void show();
}
