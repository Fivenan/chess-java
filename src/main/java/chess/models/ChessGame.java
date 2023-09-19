package main.java.chess.models;

import java.util.List;
import java.util.Map;

import main.java.chess.exceptions.IllegalTurnException;
import main.java.chess.models.enums.Color;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.Move;

public class ChessGame {

	private Map<Player, Color> players;
	private Map<Color, Long> time;
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

	public void play(Player player, Move move) throws IllegalTurnException {
		play(players.get(player), move);
	}

	public void play(Color color, Move move) throws IllegalTurnException {
		if (color != turn) {
			throw new IllegalTurnException("It's not your turn!");
		}
		chessBoard.apply(move);
	}
}
