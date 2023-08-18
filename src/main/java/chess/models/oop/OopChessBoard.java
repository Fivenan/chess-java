package main.java.chess.models.oop;

import java.util.logging.Logger;

import main.java.chess.models.ChessBoard;
import main.java.chess.models.Move;
import main.java.chess.models.enums.Color;
import main.java.chess.models.pieces.Bishop;
import main.java.chess.models.pieces.King;
import main.java.chess.models.pieces.Knight;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.Piece;
import main.java.chess.models.pieces.Queen;
import main.java.chess.models.pieces.Rook;
import main.java.chess.models.util.NotationValidator;

public class OopChessBoard implements ChessBoard {

	private static final Logger LOGGER = Logger.getLogger(OopChessBoard.class.getName());

	private Tile[][] tiles = new Tile[8][8];
	private Color turn = Color.WHITE;
	private Move lastMove;
	private Tile enPassantTargetTile;
	private int halfmoveClock = 0; // move since last capture or pawn advance
	private int fullmoveNumber = 1; // move from start, incremented after black's move

	private boolean blackCanCastleKingSide = true;
	private boolean blackCanCastleQueenSide = true;
	private boolean whiteCanCastleKingSide = true;
	private boolean whiteCanCastleQueenSide = true;

	public OopChessBoard() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}
	}

	/**
	 * load position directly using Forsyth-Edwards Notation. E.g.
	 * rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
	 * rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1
	 * rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2
	 * rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2
	 */
	@Override
	public void setBoard(String fen) {
		String[] parts = fen.split(" ", 6);

		// Validate FEN
		if (!NotationValidator.isValidFEN(fen)) {
			LOGGER.warning(fen + " is not a valid FEN.");
			return;
		}

		LOGGER.info("Setting board to " + fen);

		String[] rows = parts[0].split("/", 8);
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}
		for (int y = 0; y < 8; y++) {
			int x = 0;
			for (char c : rows[y].toCharArray()) {
				if (Character.isDigit(c)) {
					x += c - 48; // char 1 is \49
					continue;
				}
				tiles[x++][7 - y].place(Piece.getPieceFromChar(c));
			}
		}

	}

	@Override
	public void emptyBoard() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}

	}

	@Override
	public void restartBoard() {
		for (int y = 2; y < 6; y++) {
			for (int x = 0; x < 8; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}
		tiles[0][0] = new Tile(0, 0, new Rook(Color.WHITE));
		tiles[1][0] = new Tile(1, 0, new Knight(Color.WHITE));
		tiles[2][0] = new Tile(2, 0, new Bishop(Color.WHITE));
		tiles[3][0] = new Tile(3, 0, new Queen(Color.WHITE));
		tiles[4][0] = new Tile(4, 0, new King(Color.WHITE));
		tiles[5][0] = new Tile(5, 0, new Bishop(Color.WHITE));
		tiles[6][0] = new Tile(6, 0, new Knight(Color.WHITE));
		tiles[7][0] = new Tile(7, 0, new Rook(Color.WHITE));
		tiles[0][7] = new Tile(0, 7, new Rook(Color.BLACK));
		tiles[1][7] = new Tile(1, 7, new Knight(Color.BLACK));
		tiles[2][7] = new Tile(2, 7, new Bishop(Color.BLACK));
		tiles[3][7] = new Tile(3, 7, new Queen(Color.BLACK));
		tiles[4][7] = new Tile(4, 7, new King(Color.BLACK));
		tiles[5][7] = new Tile(5, 7, new Bishop(Color.BLACK));
		tiles[6][7] = new Tile(6, 7, new Knight(Color.BLACK));
		tiles[7][7] = new Tile(7, 7, new Rook(Color.BLACK));
		for (int x = 0; x < 8; x++) {
			tiles[x][1] = new Tile(x, 1, new Pawn(Color.WHITE));
			tiles[x][6] = new Tile(x, 6, new Pawn(Color.BLACK));
		}

	}

	@Override
	public void move(String endPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Move move) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMoveNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFEN() {
		StringBuilder res = new StringBuilder();
		for (int y = 0; y < 8; y++) {
			int tmp = 0;
			for (int x = 0; x < 8; x++) {

				if (tiles[x][7 - y].piece == null) {
					res.append(x == 7 ? ++tmp : "");
					tmp++;
				} else {
					if (tmp != 0)
						res.append(tmp);
					res.append(tiles[x][7 - y].piece.notation);
					tmp = 0;
				}
			}
			res.append('/');
		}
		return res.deleteCharAt(res.length() - 1).toString();
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
//        Board.values().stream().forEachOrdered(k -> System.out.println(k));
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				res.append(tiles[x][y].symbol());
			}
			res.append("\n");
		}
		return res.toString();
	}

}
