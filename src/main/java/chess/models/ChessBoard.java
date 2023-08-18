package main.java.chess.models;

public interface ChessBoard {

	public void setBoard(String fen);

	public void emptyBoard();

	public void restartBoard();

	public void move(String endPosition);

	public void move(Move move);

	public String getTurn();

	public String getMoveNumber();

	public String getFEN();

	public String toString();

}
