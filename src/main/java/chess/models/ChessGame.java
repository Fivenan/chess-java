package main.java.chess.models;

import java.util.List;
import java.util.Map;

import main.java.chess.models.enums.Color;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.Move;

public class ChessGame {

	private Map<Color, Player> players;
	private ChessBoard chessBoard;

	private Color turn;

	private Move lastMove;
	private List<Move> moves;
	private boolean whiteCanCastleKingSide = false;
	private boolean whiteCanCastleQueenSide = false;
	private boolean blackCanCastleKingSide = false;
	private boolean blackCanCastleQueenSide = false;
	private Tile enPassantTargetTile;
	private int halfmoveClock = 0; // move since last capture or pawn advance
	private int fullmoveNumber = 1; // move from start, incremented after black's move

	public ChessGame() {
		chessBoard = new OopChessBoard();
	}
}
