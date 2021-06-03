package chess.controllers;

import chess.models.Board;

public interface IChessProcessor {

	//update board information with some AI processor
	public Board processMove(Board currentBoard);
	
}
