package main.java.chess.exceptions;

import main.java.chess.models.oop.Tile;

public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidMoveException() {
		super("Invalid move: The move does not comply with chess rules.");
	}

	public InvalidMoveException(String message) {
		super(message);
	}

	public InvalidMoveException(Tile startTile) {
		super("Invalid move: The move from tile " + startTile.getPosition() + " does not comply with chess rules.");
	}

	public InvalidMoveException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidMoveException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidMoveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
