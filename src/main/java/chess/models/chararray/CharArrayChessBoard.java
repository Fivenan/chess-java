package main.java.chess.models.chararray;

import java.util.logging.Logger;

import main.java.chess.exceptions.InvalidMoveException;
import main.java.chess.models.ChessBoardInterface;
import main.java.chess.models.oop.moves.Move;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	public boolean isGameOver() {
		return false;
	}

	@Override
	public void moveTo(String endPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void apply(Move move) {
		// TODO Auto-generated method stub

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
	public Move move(String start, String end) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return null;
	}

	private char getTile(String s) {
		if (!s.matches("[a-h][1-8]")) {
			logger.warning("Invalid tile position: " + s);
			return 0;
		}
		System.out.println("Tile: [rank: " + (7 + '1' - s.charAt(1)) + "], [file: " + (s.charAt(0) - 'a') + "]");
		return tiles[7 + '1' - s.charAt(1)][s.charAt(0) - 'a'];
	}

}
