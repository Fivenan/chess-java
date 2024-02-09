package main.java.chess.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import main.java.chess.exceptions.IllegalTurnException;
import main.java.chess.exceptions.InvalidMoveException;
import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.GameOver;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.OopMove;

public class ChessGame {

	private static final Logger logger = Logger.getLogger(ChessGame.class.getName());

	private long id;

	private Map<Player, Color> players;
	private Map<Color, List<Long>> time;
	private List<Long> timeStamp;

	private OopChessBoard chessBoard;

	private Color turn;
	private List<OopMove> moves;
	private Map<Integer, OopMove> moveMap;
	private Map<Integer, Long> moveTimeMap;

	private Color winner;
	private GameOver gameOverType;

	public ChessGame() {
		chessBoard = new OopChessBoard();
	}

	public ChessGame(Player whitePlayer, Player blackPlayer) {
		players = new HashMap<>();
		players.put(whitePlayer, Color.WHITE);
		players.put(blackPlayer, Color.BLACK);
		chessBoard = new OopChessBoard();
	}

	public void join(Player player, Color color) {
		players.put(player, color);
	}

	public List<Tile> availableTargets(Player player, int row, int col) {
		Color color = players.get(player);
		if (color != chessBoard.getTile(row, col).getPiece().color) {
			return new ArrayList<Tile>();
		}
		return availableTargets(color, row, col);
	}

	public List<Tile> availableTargets(Player player, String start) {
		Color color = players.get(player);
		if (color != chessBoard.getTile(start).getPiece().color) {
			return new ArrayList<Tile>();
		}
		return availableTargets(color, start);
	}

	public List<Tile> availableTargets(Color color, String start) {
		return chessBoard.getAllPossibleMoves(color).stream() //
				.filter(m -> m.getStart().getPosition().equals(start)) //
				.map(OopMove::getEnd).collect(Collectors.toList());
	}

	public List<Tile> availableTargets(Color color, int row, int col) {
		return chessBoard.getAllPossibleMoves(color).stream() //
				.filter(m -> m.getStart().isInPosition(row, col)) //
				.map(OopMove::getEnd).collect(Collectors.toList());
	}

	public OopMove getMove(Color color, Tile endTile) {
		return chessBoard.getAllPossibleMoves(color).stream() //
				.filter(m -> m.getEnd().equals(endTile)) //
				.findFirst() //
				.orElse(null);
	}

	public void play(Color color, String start, String end) throws IllegalTurnException, InvalidMoveException {
		if (color != turn) {
			throw new IllegalTurnException("It's not your turn!");
		}
		moves.add(chessBoard.move(start, end));
	}

	public void play(Color color, OopMove move) throws IllegalTurnException {
		if (color != turn) {
			throw new IllegalTurnException("It's not your turn!");
		}
		chessBoard.apply(move);
		turn = color == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	public void resignation() {
		// Set the game over flag to true
		gameOverType = GameOver.RESIGNATION;
		// Set the winner to the other player
		winner = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
		// Print a message indicating the resignation
		logger.info(winner + " wins by resignation.");
	}

	public void agreedDraw() {
		// Set the game over flag to true
		gameOverType = GameOver.AGREED_DRAW;
		// Set the winner to null (indicating a draw)
		winner = null;
		// Print a message indicating the agreed draw
		logger.info("Game ends in a draw by agreement.");
	}

	public void timeForfeit() {
		// Set the game over flag to true
		gameOverType = GameOver.TIME_FORFEIT;
		// Set the winner to the other player
		winner = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
		// Print a message indicating the time forfeit
		logger.info(winner + " wins on time forfeit.");
	}

	public void insufficientMaterial() {
		// Set the game over flag to true
		gameOverType = GameOver.INSUFFICIENT_MATERIAL;
		// Set the winner to null (indicating a draw)
		winner = null;
		// Print a message indicating the insufficient material
		logger.info("Game ends in a draw due to insufficient material.");
	}

	public Color getTurn() {
		return turn;
	}

}
