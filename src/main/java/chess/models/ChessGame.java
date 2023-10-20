package main.java.chess.models;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.chess.exceptions.IllegalTurnException;
import main.java.chess.exceptions.InvalidMoveException;
import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.GameOver;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.Move;

public class ChessGame {

	private Map<Color, Player> players;
	private Map<Color, Long> time;

	private OopChessBoard chessBoard;

	private Color turn;
	private List<Move> moves;

	private Color winner;
	private GameOver gameOverType;

	public ChessGame() {
		chessBoard = new OopChessBoard();
	}

	public ChessGame(Player whitePlayer, Player blackPlayer) {
		players = new EnumMap<>(Color.class);
		players.put(Color.WHITE, whitePlayer);
		players.put(Color.BLACK, blackPlayer);
		chessBoard = new OopChessBoard();
	}

	public List<Tile> availableTargets(Color color, String start) {
		return chessBoard.getAllPossibleMoves(color).stream().filter(m -> m.getStart().getPosition().equals(start))
				.map(m -> m.getEnd()).collect(Collectors.toList());
	}

	public void play(Color color, String start, String end) throws IllegalTurnException, InvalidMoveException {
		if (color != turn) {
			throw new IllegalTurnException("It's not your turn!");
		}
		moves.add(chessBoard.move(start, end));
	}

	public void play(Color color, Move move) throws IllegalTurnException {
		if (color != turn) {
			throw new IllegalTurnException("It's not your turn!");
		}
		chessBoard.apply(move);

	}

	public void resignation() {
		// Set the game over flag to true
		gameOverType = GameOver.RESIGNATION;
		// Set the winner to the other player
		winner = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
		// Print a message indicating the resignation
		System.out.println(winner + " wins by resignation.");
	}

	public void agreedDraw() {
		// Set the game over flag to true
		gameOverType = GameOver.AGREED_DRAW;
		// Set the winner to null (indicating a draw)
		winner = null;
		// Print a message indicating the agreed draw
		System.out.println("Game ends in a draw by agreement.");
	}

	public void timeForfeit() {
		// Set the game over flag to true
		gameOverType = GameOver.TIME_FORFEIT;
		// Set the winner to the other player
		winner = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
		// Print a message indicating the time forfeit
		System.out.println(winner + " wins on time forfeit.");
	}

	public void insufficientMaterial() {
		// Set the game over flag to true
		gameOverType = GameOver.INSUFFICIENT_MATERIAL;
		// Set the winner to null (indicating a draw)
		winner = null;
		// Print a message indicating the insufficient material
		System.out.println("Game ends in a draw due to insufficient material.");
	}
}
