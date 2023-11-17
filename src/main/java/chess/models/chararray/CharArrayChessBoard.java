package main.java.chess.models.chararray;

import java.util.logging.Logger;

import main.java.chess.exceptions.InvalidMoveException;
import main.java.chess.models.ChessBoardInterface;
import main.java.chess.models.oop.moves.OopMove;
import main.java.chess.models.util.BoardUtil;
import main.java.chess.models.util.NotationValidator;

public class CharArrayChessBoard implements ChessBoardInterface {

	private static final Logger logger = Logger.getLogger(CharArrayChessBoard.class.getName());

	private char[][] tiles;

	private boolean isWhiteTurn = true;
	private String castling = "KQkq";
	private String enPassantTargetTile;

	private int halfmoveClock;
	private int fullmoveNumber;

	public CharArrayChessBoard() {

	}

	public CharArrayChessBoard(String fen) {
		String refreshedFEN = BoardUtil.refreshFEN(fen);
		tiles = BoardUtil.fenToCharArray2d(refreshedFEN);
	}

	@Override
	public void setBoard(String fen) {
		// Validate FEN
		if (!NotationValidator.isValidFEN(fen)) {
			logger.warning(fen + " is not a valid FEN.");
			return;
		}

		logger.info("Setting board to " + fen);

		String[] parts = fen.split(" ", 6);

		importFENBoardToTiles(parts[0]);
		isWhiteTurn = parts[1].equals("w");
		castling = parts[2];

		if (!parts[3].equals("-")) {
			enPassantTargetTile = parts[3];
		} else {
			enPassantTargetTile = null;
		}

		halfmoveClock = Integer.valueOf(parts[4]);
		fullmoveNumber = Integer.valueOf(parts[5]);

	}

	private void importFENBoardToTiles(String fenBoard) {
		String[] ranks = fenBoard.split("/", 8);
		emptyBoard();

		for (int rank = 0; rank < 8; rank++) {
			int file = 0;
			for (char c : ranks[rank].toCharArray()) {
				if (Character.isDigit(c)) {
					file += c - '0';
					tiles[rank][file++] = 'x';
				} else {
					tiles[rank][file++] = c;
				}
			}
		}
	}

	@Override
	public void emptyBoard() {
		for (int rank = 0; rank < 8; rank++) {
			for (int file = 0; file < 8; file++) {
				tiles[rank][file] = 'x';
			}
		}

	}

	@Override
	public void initialize() {
		setBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	public boolean isGameOver() {
		return false;
	}

	@Override
	public void moveTo(String endPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void apply(OopMove move) {
		tiles[move.getEnd().rank][move.getEnd().file] = getTile(move.getStart().getPosition());
		tiles[move.getStart().rank][move.getStart().file] = 'x';

	}

	@Override
	public String getFEN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFENBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OopMove move(String start, String end) throws InvalidMoveException {
		char tmp = getTile(end);
		tiles[getX(end)][getY(end)] = getTile(start);
		return null;
	}

	private char getTile(String s) {
		if (!s.matches("[a-h][1-8]")) {
			logger.warning("Invalid tile position: " + s);
			return 0;
		}
		logger.info("Tile: [rank: " + (7 + '1' - s.charAt(1)) + "], [file: " + (s.charAt(0) - 'a') + "]");
		return tiles[7 + '1' - s.charAt(1)][s.charAt(0) - 'a'];
	}

	private int getX(String pos) {
		return 7 + '1' - pos.charAt(1);
	}

	private int getY(String pos) {
		return pos.charAt(0) - 'a';
	}

}
