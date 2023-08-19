package main.java.chess.models;

import main.java.chess.models.oop.Move;

public interface ChessBoard {

	public void setBoard(String fen);

	public void emptyBoard();

	public void restartBoard();

	public void move(String endPosition);

	public void move(Move move);

	public String getMoveNumber();

	public String getFENBoard();

	public String getFEN();

	public String toString();

}
