package main.java.chess.controllers;

import main.java.chess.models.ChessBoardInterface;

public interface IChessProcessor {

	//update board information with some AI processor
	public ChessBoardInterface processMove(ChessBoardInterface currentBoard);
	
}
