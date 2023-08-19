package main.java.chess.models.oop;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import lombok.Getter;
import lombok.Setter;
import main.java.chess.models.ChessBoard;
import main.java.chess.models.Player;
import main.java.chess.models.enums.Color;
import main.java.chess.models.pieces.Bishop;
import main.java.chess.models.pieces.King;
import main.java.chess.models.pieces.Knight;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.PieceFactory;
import main.java.chess.models.pieces.Queen;
import main.java.chess.models.pieces.Rook;
import main.java.chess.models.util.NotationValidator;

public class OopChessBoard implements ChessBoard {

	private static final Logger LOGGER = Logger.getLogger(OopChessBoard.class.getName());

	private Tile[][] tiles = new Tile[8][8];

	@Getter
	@Setter
	private Color turn = Color.WHITE;

	@Getter
	@Setter
	private Move lastMove;

	@Getter
	@Setter
	private boolean whiteCanCastleKingSide = false;

	@Getter
	@Setter
	private boolean whiteCanCastleQueenSide = false;

	@Getter
	@Setter
	private boolean blackCanCastleKingSide = false;

	@Getter
	@Setter
	private boolean blackCanCastleQueenSide = false;

	@Getter
	@Setter
	private Tile enPassantTargetTile;

	@Getter
	@Setter
	private int halfmoveClock = 0; // move since last capture or pawn advance

	@Getter
	@Setter
	private int fullmoveNumber = 1; // move from start, incremented after black's move

	@Getter
	@Setter
	private Map<Color, Player> players;

	public OopChessBoard() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}
	}

	public OopChessBoard(List<Move> moves) {

	}

	/**
	 * load position directly using Forsyth-Edwards Notation. E.g.,
	 * rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
	 * rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1
	 * rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2
	 * rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2
	 */
	@Override
	public void setBoard(String fen) {

		// Validate FEN
		if (!NotationValidator.isValidFEN(fen)) {
			LOGGER.warning(fen + " is not a valid FEN.");
			return;
		}

		LOGGER.info("Setting board to " + fen);

		String[] parts = fen.split(" ", 6);
		String[] ranks = parts[0].split("/", 8);
		for (int rank = 0; rank < 8; rank++) {
			for (int file = 0; file < 8; file++) {
				tiles[file][rank] = new Tile(file, rank);
			}
		}
		for (int rank = 0; rank < 8; rank++) {
			int file = 0;
			for (char c : ranks[rank].toCharArray()) {
				if (Character.isDigit(c)) {
					file += c - '0';
					continue;
				}
				tiles[rank][file++].place(PieceFactory.getPieceFromChar(c));
			}
		}

		turn = parts[1].equals("w") ? Color.WHITE : Color.BLACK;

		whiteCanCastleKingSide = parts[2].contains("K");
		whiteCanCastleQueenSide = parts[2].contains("Q");
		blackCanCastleKingSide = parts[2].contains("k");
		blackCanCastleQueenSide = parts[2].contains("q");

		if (!parts[3].equals("-")) {
			enPassantTargetTile = getTile(parts[3]);
		} else {
			enPassantTargetTile = null;
		}

		halfmoveClock = Integer.valueOf(parts[4]);
		fullmoveNumber = Integer.valueOf(parts[5]);

	}

	@Override
	public void emptyBoard() {
		for (int rank = 0; rank < 8; rank++) {
			for (int file = 0; file < 8; file++) {
				tiles[rank][file] = new Tile(rank, file);
			}
		}

	}

	@Override
	public void restartBoard() {
		for (int rank = 2; rank < 6; rank++) {
			for (int file = 0; file < 8; file++) {
				tiles[rank][file] = new Tile(rank, file);
			}
		}
		tiles[0][0] = new Tile(0, 0, new Rook(Color.WHITE));
		tiles[0][1] = new Tile(0, 1, new Knight(Color.WHITE));
		tiles[0][2] = new Tile(0, 2, new Bishop(Color.WHITE));
		tiles[0][3] = new Tile(0, 3, new Queen(Color.WHITE));
		tiles[0][4] = new Tile(0, 4, new King(Color.WHITE));
		tiles[0][5] = new Tile(0, 5, new Bishop(Color.WHITE));
		tiles[0][6] = new Tile(0, 6, new Knight(Color.WHITE));
		tiles[0][7] = new Tile(0, 7, new Rook(Color.WHITE));
		tiles[7][0] = new Tile(7, 0, new Rook(Color.BLACK));
		tiles[7][1] = new Tile(7, 1, new Knight(Color.BLACK));
		tiles[7][2] = new Tile(7, 2, new Bishop(Color.BLACK));
		tiles[7][3] = new Tile(7, 3, new Queen(Color.BLACK));
		tiles[7][4] = new Tile(7, 4, new King(Color.BLACK));
		tiles[7][5] = new Tile(7, 5, new Bishop(Color.BLACK));
		tiles[7][6] = new Tile(7, 6, new Knight(Color.BLACK));
		tiles[7][7] = new Tile(7, 7, new Rook(Color.BLACK));
		for (int file = 0; file < 8; file++) {
			tiles[1][file] = new Tile(1, file, new Pawn(Color.WHITE));
			tiles[6][file] = new Tile(6, file, new Pawn(Color.BLACK));
		}

		turn = Color.WHITE;

		whiteCanCastleKingSide = true;
		whiteCanCastleQueenSide = true;
		blackCanCastleKingSide = true;
		blackCanCastleQueenSide = true;

		enPassantTargetTile = null;

		halfmoveClock = 0;
		fullmoveNumber = 1;

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
	public String getMoveNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFENBoard() {
		StringBuilder result = new StringBuilder();
		String delimiter = "";

		for (Tile[] rank : tiles) {
			result.append(delimiter);
			for (Tile tile : rank) {
				result.append(tile.getNotation());
			}
			delimiter = "/";
		}
		return cascadeXs(result.toString());
	}

	@Override
	public String getFEN() {
		StringBuilder fen = new StringBuilder();
		fen.append(getFENBoard());
		fen.append(" ");
		fen.append(turn == Color.WHITE ? "w" : "b");
		fen.append(" ");
		String castling = (whiteCanCastleKingSide ? "K" : "") + (whiteCanCastleQueenSide ? "Q" : "")
						+ (blackCanCastleKingSide ? "k" : "") + (blackCanCastleQueenSide ? "q" : "");
		fen.append(!castling.isEmpty() ? castling : "-");
		fen.append(" ");
		fen.append(enPassantTargetTile != null ? enPassantTargetTile.getPosition() : "-");
		fen.append(" ");
		fen.append(halfmoveClock);
		fen.append(" ");
		fen.append(fullmoveNumber);
		return fen.toString();
	}

	private String cascadeXs(String s) {
		StringBuilder result = new StringBuilder();
		int count = 0;

		for (char c : s.toCharArray()) {
			if (c == 'x') {
				count++;
			} else {
				if (count > 0) {
					result.append(count);
					count = 0;
				}
				result.append(c);
			}
		}

		if (count > 0) {
			result.append(count);
		}

		return result.toString();
	}

	/**
	 * Returns the tile on the board
	 * 
	 * @param s chess notation a1-h8
	 * @return
	 */
	public Tile getTile(String s) {
		if (!s.matches("[a-h][1-8]")) {
			LOGGER.warning("Invalid tile position: " + s);
			return null;
		}
		return tiles[7 + '0' - s.charAt(1)][s.charAt(0) - 'a'];
	}

	public Tile getTile(int rank, int file) {
		return tiles[rank][file];
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		String delimiter = "";
		for (int rank = 0; rank < 8; rank++) { // rank is y, row
			res.append(delimiter);
			for (int file = 0; file < 8; file++) { // file is x, column
				res.append(tiles[rank][file].getSymbol());
			}
			delimiter = "\n";
		}
		return res.toString();
	}

}
