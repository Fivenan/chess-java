package main.java.chess.controllers;

import main.java.chess.models.ChessBoard;

public interface IChessProcessor {

	//update board information with some AI processor
	public ChessBoard processMove(ChessBoard currentBoard);
	
}
