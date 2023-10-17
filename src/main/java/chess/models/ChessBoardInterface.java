package main.java.chess.models;

import main.java.chess.exceptions.InvalidMoveException;
import main.java.chess.models.oop.moves.Move;

public interface ChessBoardInterface {

	public void setBoard(String fen);

	public void emptyBoard();

	public void initialize();

	public boolean isGameOver();

	public void moveTo(String endPosition);

	public void apply(Move move);

	public String getFENBoard();

	public String getFEN();

	public String toString();

	public Move move(String start, String end) throws InvalidMoveException;

}
