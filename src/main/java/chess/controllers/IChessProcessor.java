package main.java.chess.controllers;

import main.java.chess.models.Board;

public interface IChessProcessor {

	//update board information with some AI processor
	public Board processMove(Board currentBoard);
	
}
