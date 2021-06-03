package chess.models;

import chess.pieces.Pawn;
import chess.pieces.Piece;

public class Board {
	
	public static enum Player {
		Player1,	//always move first
		Player2
	}
	
	private Player currentMove = Player.Player1;	
	private Piece[] player1Pieces = new Piece[16];
	private Piece[] player2Pieces = new Piece[16];
	
	public Board() {	
		initBoard();
	}
	
	private void initBoard() {
		currentMove = Player.Player1;
		for (int i = 0; i < 16; i++) {
			player1Pieces[i] = new Pawn(true);
		}
	}
	
	public void resetBoard() {
		currentMove = Player.Player1;
		for (int i = 0; i < 16; i++) {
			player1Pieces[i].resetLocation();
			player2Pieces[i].resetLocation();
		}
	}
	
	//change current move to other player
	public void done() {
		if (currentMove == Player.Player1) {
			currentMove = Player.Player2;
		} else {
			currentMove = Player.Player1;
		}
	}
}
