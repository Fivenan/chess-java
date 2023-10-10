package main.java.chess.models.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.chess.exceptions.InvalidMoveException;
import main.java.chess.models.ChessBoard;
import main.java.chess.models.Player;
import main.java.chess.models.enums.Color;
import main.java.chess.models.enums.GameOver;
import main.java.chess.models.enums.PieceType;
import main.java.chess.models.oop.moves.CapturingMove;
import main.java.chess.models.oop.moves.CastlingMove;
import main.java.chess.models.oop.moves.Move;
import main.java.chess.models.pieces.Pawn;
import main.java.chess.models.pieces.Piece;
import main.java.chess.models.pieces.PieceFactory;
import main.java.chess.models.util.NotationValidator;

public class OopChessBoard implements ChessBoard {

	private static final Logger LOGGER = Logger.getLogger(OopChessBoard.class.getName());

	private Tile[][] tiles = new Tile[8][8];

	private Color turn = Color.WHITE;

	private Move lastMove;

	private boolean whiteCanCastleKingSide = false;

	private boolean whiteCanCastleQueenSide = false;

	private boolean blackCanCastleKingSide = false;

	private boolean blackCanCastleQueenSide = false;

	private Tile enPassantTargetTile;

	private int halfmoveClock = 0; // move since last capture or pawn advance

	private int fullmoveNumber = 1; // move from start, incremented after black's move

	private Map<Color, Player> players;

	private List<Move> moveHistory = new ArrayList<>();;

	public OopChessBoard() {
		emptyBoard();
	}

	public void setBoard(List<Move> moves) {
		for (Move move : moves) {
			apply(move);
		}
		moveHistory = moves;
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

		importFENBoardToTiles(parts[0]);
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

	private void importFENBoardToTiles(String fenBoard) {
		String[] ranks = fenBoard.split("/", 8);
		emptyBoard();

		for (int rank = 0; rank < 8; rank++) {
			int file = 0;
			for (char c : ranks[rank].toCharArray()) {
				if (Character.isDigit(c)) {
					file += c - '0';
					continue;
				}
				tiles[rank][file++].setPiece(PieceFactory.getPieceFromChar(c));
			}
		}
	}

	@Override
	public void initialize() {
		emptyBoard();
		tiles[0][0] = new Tile(0, 0, PieceFactory.create(PieceType.ROOK, Color.BLACK));
		tiles[0][1] = new Tile(0, 1, PieceFactory.create(PieceType.KNIGHT, Color.BLACK));
		tiles[0][2] = new Tile(0, 2, PieceFactory.create(PieceType.BISHOP, Color.BLACK));
		tiles[0][3] = new Tile(0, 3, PieceFactory.create(PieceType.QUEEN, Color.BLACK));
		tiles[0][4] = new Tile(0, 4, PieceFactory.create(PieceType.KING, Color.BLACK));
		tiles[0][5] = new Tile(0, 5, PieceFactory.create(PieceType.BISHOP, Color.BLACK));
		tiles[0][6] = new Tile(0, 6, PieceFactory.create(PieceType.KNIGHT, Color.BLACK));
		tiles[0][7] = new Tile(0, 7, PieceFactory.create(PieceType.ROOK, Color.BLACK));
		tiles[7][0] = new Tile(7, 0, PieceFactory.create(PieceType.ROOK, Color.WHITE));
		tiles[7][1] = new Tile(7, 1, PieceFactory.create(PieceType.KNIGHT, Color.WHITE));
		tiles[7][2] = new Tile(7, 2, PieceFactory.create(PieceType.BISHOP, Color.WHITE));
		tiles[7][3] = new Tile(7, 3, PieceFactory.create(PieceType.QUEEN, Color.WHITE));
		tiles[7][4] = new Tile(7, 4, PieceFactory.create(PieceType.KING, Color.WHITE));
		tiles[7][5] = new Tile(7, 5, PieceFactory.create(PieceType.BISHOP, Color.WHITE));
		tiles[7][6] = new Tile(7, 6, PieceFactory.create(PieceType.KNIGHT, Color.WHITE));
		tiles[7][7] = new Tile(7, 7, PieceFactory.create(PieceType.ROOK, Color.WHITE));
		for (int file = 0; file < 8; file++) {
			tiles[1][file] = new Tile(1, file, PieceFactory.create(PieceType.PAWN, Color.BLACK));
			tiles[6][file] = new Tile(6, file, PieceFactory.create(PieceType.PAWN, Color.WHITE));
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

	public boolean isGameOver() {
		List<Move> moves = getAllPossibleMoves(turn);
		if (moves.isEmpty()) {
			if (isCheckmate()) {
				return true;
			} else {
				return true;
			}
		}
		if (halfmoveClock >= 100) {
			return true;
		}
		return false;
	}

	private GameOver getGameOverType() {
		List<Move> moves = getAllPossibleMoves(turn);
		if (moves.isEmpty()) {
			if (isCheckmate()) {
				return GameOver.CHECKMATE;
			} else {
				return GameOver.STALEMATE;
			}
		}
		if (halfmoveClock > 49) {
			return GameOver.FIFTY_MOVE_RULE;
		}
		// if ()
		return null;
	}

	private boolean isCheckmate() {
		if (!isBeingChecked()) {
			return false;
		}
		List<Move> moves = getAllPossibleMoves(turn);
		for (Move move : moves) {
			apply(move);
			if (!isBeingChecked()) {
				return false;
			}
		}
		return true;
	}

	private boolean isCheck() {
		boolean capturedIsKing = false;
		boolean colorIsDifferent = false;
		List<Move> possibleMoves = getAllPossibleMoves();
		for (Move move : possibleMoves) {
			if (move instanceof CapturingMove) {
				capturedIsKing = ((CapturingMove) move).getCapturedPiece().pieceType == PieceType.KING;
				colorIsDifferent = ((CapturingMove) move).getStart().getPiece().color != ((CapturingMove) move)
						.getCapturedPiece().color;
			}
		}
		return capturedIsKing & colorIsDifferent;
	}

	private boolean isCheck(Color color) {
		boolean capturedIsKing = false;
		List<Move> possibleMoves = getAllPossibleMoves(color);
		for (Move move : possibleMoves) {
			if (move instanceof CapturingMove) {
				capturedIsKing = ((CapturingMove) move).getCapturedPiece().pieceType == PieceType.KING;
			}
		}
		return capturedIsKing;
	}

	private boolean isBeingChecked() {
		boolean capturedIsKing = false;
		List<Move> possibleMoves = getAllPossibleMoves(getOpponentColor());
		for (Move move : possibleMoves) {
			if (move instanceof CapturingMove) {
				capturedIsKing = ((CapturingMove) move).getCapturedPiece().pieceType == PieceType.KING;
			}
		}
		return capturedIsKing;
	}

	private boolean causesCheck(Move move) {
		OopChessBoard board = cloneBoard();
		board.apply(move);
		return board.isBeingChecked();
	}

	private Color getOpponentColor() {
		return turn == Color.WHITE ? Color.WHITE : Color.BLACK;
	}

	@Override
	public void moveTo(String moveNotation) {
		String endPosition = moveNotation.substring(moveNotation.length() - 2);
		int endRank = getTile(endPosition).rank;
		int endFile = getTile(endPosition).file;
		List<Move> possibleMoves = getAllPossibleMoves();
		Map<String, Move> targetedPossibleMoves = possibleMoves.stream()
				.filter(m -> m.getEnd().file == endFile && m.getEnd().rank == endRank)
				.collect(Collectors.toMap(m -> m.getEnd().getPosition(), Function.identity()));
		if (targetedPossibleMoves.containsKey(moveNotation)) {
			apply(targetedPossibleMoves.get(moveNotation));
		}
//		List<String> possibleMovesString = possibleMoves.stream().map(Move::getNotation).collect(Collectors.toList());

	}

	public void move(String start, String end) throws InvalidMoveException {
		Tile startTile = getTile(start);
		Tile endTile = getTile(end);
		if (startTile.isEmpty()) {
			throw new InvalidMoveException("Tile " + startTile.getPosition() + " is empty.");
		}
		List<Move> moves = startTile.getPiece().generateValidMoves(this, startTile.rank, startTile.file);
		for (Move move : moves) {
			if (move.getEnd().equals(endTile)) {
				apply(move);
				return;
			}
		}
		throw new InvalidMoveException("Move is illegal.");

	}

	@Override
	public void apply(Move move) {
		// check the possible moves
		Piece movingPiece = move.getStart().getPiece();

		List<Move> moves = movingPiece.generateValidMoves(this, move.getStart().rank, move.getStart().file);
		if (!moves.contains(move)) {
			return;
		}
		// apply the move
		if (move instanceof CapturingMove) {
			CapturingMove cm = (CapturingMove) move;
			Tile cdt = cm.getCapturedTile();
//			Piece capturedPiece = cdt.getPiece(); // for storing purposes, e.g. Shogi-like gameplay
			cdt.clear();
			halfmoveClock = 0;
		} else {
			halfmoveClock++;
		}
		if (move instanceof CastlingMove) {
			CastlingMove cm = (CastlingMove) move;
			Tile rt = cm.getRookStartTile();
			if ((rt.file == 0 && rt.rank == 0 && blackCanCastleQueenSide)
					|| (rt.file == 7 && rt.rank == 0 && blackCanCastleKingSide)) {
				apply(cm.getRookMove());
				blackCanCastleQueenSide = false;
				blackCanCastleKingSide = false;
			} else if ((rt.file == 0 && rt.rank == 7 && whiteCanCastleQueenSide)
					|| (rt.file == 7 && rt.rank == 0 && whiteCanCastleKingSide)) {
				apply(cm.getRookMove());
				whiteCanCastleQueenSide = false;
				whiteCanCastleKingSide = false;
			}
		}
		enPassantTargetTile = move.getEnPassantTile();
		move.getEnd().setPiece(movingPiece);
		move.getStart().clear();

		if (turn == Color.BLACK) {
			fullmoveNumber++;
			turn = Color.WHITE;
		} else {
			turn = Color.BLACK;
		}
		if (movingPiece instanceof Pawn) {
			halfmoveClock = 0;
		}
	}

	public void applyMove(Move move) {

		if (!Move.isLegalMove(move, this)) {
			LOGGER.warning("Move is illegal.");
			return;
		}

		Piece moving = tiles[move.getStart().rank][move.getStart().file].getPiece();
		Piece taken = tiles[move.getEnd().rank][move.getEnd().file].getPiece();
		tiles[move.getStart().rank][move.getStart().file].clear();
		tiles[move.getEnd().rank][move.getEnd().file].setPiece(moving);

		moveHistory.add(move);
	}

	public List<Move> getAllPossibleMoves() {
		List<Move> moves = new ArrayList<>();
		for (Tile[] tilesInRank : tiles) {
			for (Tile tile : tilesInRank) {
				if (!tile.isEmpty()) {
					moves.addAll(tile.getPiece().generateValidMoves(this, tile.rank, tile.file));
				}
			}
		}
		while (moves.remove(null))
			;
		return moves;
	}

	public List<Move> getAllPossibleMoves(Color color) {
		List<Move> moves = new ArrayList<>();
		for (Tile[] tilesInRank : tiles) {
			for (Tile tile : tilesInRank) {
				if (!tile.isEmpty() && tile.getPiece().color == color) {
					moves.addAll(tile.getPiece().generateValidMoves(this, tile.rank, tile.file));
				}
			}
		}
		while (moves.remove(null))
			;
		return moves;
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
	 * a --> 0, h --> 7
	 * 1 --> 7, 8 --> 0
	 * 
	 * @param s chess notation a1-h8
	 * @return
	 */
	public Tile getTile(String s) {
		if (!s.matches("[a-h][1-8]")) {
			LOGGER.warning("Invalid tile position: " + s);
			return null;
		}
		System.out.println("Tile: [rank: " + (7 + '1' - s.charAt(1)) + "], [file: " + (s.charAt(0) - 'a') + "]");
		return tiles[7 + '1' - s.charAt(1)][s.charAt(0) - 'a'];
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
				res.append(tiles[rank][file].getNotation());
			}
			delimiter = "\n";
		}
		return res.toString();
	}

	public boolean isEmptyBetweenTwoTilesInRow(Tile t1, Tile t2) throws IllegalArgumentException {
		int fileMax = (t1.file > t2.file) ? t1.file : t2.file;
		int fileMin = (t1.file > t2.file) ? t2.file : t1.file;
		int rank = t1.rank;
		if (rank != t2.rank) {
			throw new IllegalArgumentException(
					"Rank of the tiles " + t1.getPosition() + "and" + t2.getPosition() + " are different!");
		}
		for (int file = fileMin + 1; file < fileMax; file++) {
			if (!getTile(rank, file).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmptyBetweenTwoTilesInRow(int rank, int file1, int file2) {
		int fileMax = (file1 > file2) ? file1 : file2;
		int fileMin = (file1 > file2) ? file2 : file1;
		for (int file = fileMin + 1; file < fileMax; file++) {
			if (!getTile(rank, file).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	private OopChessBoard cloneBoard() {
		OopChessBoard board = (new OopChessBoard());
		board.setBoard(getFEN());
		return board;
	}

	public void incrementFullmoveNumber() {
		fullmoveNumber++;
	}

	public void incrementHalfmoveClock() {
		halfmoveClock++;
	}

	public void resetHalfmoveClock() {
		halfmoveClock = 0;
	}

	public void hasCastled() {
		whiteCanCastleKingSide = false;
		whiteCanCastleQueenSide = false;
		blackCanCastleKingSide = false;
		blackCanCastleQueenSide = false;
	}

	public List<Piece> getAllPiecesOnBoard() {
		return Stream.of(tiles).flatMap(Stream::of).filter(t -> !t.isEmpty()).map(Tile::getPiece)
				.collect(Collectors.toList());
	}

	/*
	 *************************************************************************************************************************************************
	 *
	 * GETTER, SETTER, DEFAULT METHODS
	 *
	 ************************************************************************************************************************************************* 
	 */

	public Color getTurn() {
		return turn;
	}

	public void setTurn(Color turn) {
		this.turn = turn;
	}

	public Move getLastMove() {
		return lastMove;
	}

	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}

	public boolean isWhiteCanCastleKingSide() {
		return whiteCanCastleKingSide;
	}

	public void setWhiteCanCastleKingSide(boolean whiteCanCastleKingSide) {
		this.whiteCanCastleKingSide = whiteCanCastleKingSide;
	}

	public boolean isWhiteCanCastleQueenSide() {
		return whiteCanCastleQueenSide;
	}

	public void setWhiteCanCastleQueenSide(boolean whiteCanCastleQueenSide) {
		this.whiteCanCastleQueenSide = whiteCanCastleQueenSide;
	}

	public boolean isBlackCanCastleKingSide() {
		return blackCanCastleKingSide;
	}

	public void setBlackCanCastleKingSide(boolean blackCanCastleKingSide) {
		this.blackCanCastleKingSide = blackCanCastleKingSide;
	}

	public boolean isBlackCanCastleQueenSide() {
		return blackCanCastleQueenSide;
	}

	public void setBlackCanCastleQueenSide(boolean blackCanCastleQueenSide) {
		this.blackCanCastleQueenSide = blackCanCastleQueenSide;
	}

	public Tile getEnPassantTargetTile() {
		return enPassantTargetTile;
	}

	public void setEnPassantTargetTile(Tile enPassantTargetTile) {
		this.enPassantTargetTile = enPassantTargetTile;
	}

	public int getHalfmoveClock() {
		return halfmoveClock;
	}

	public void setHalfmoveClock(int halfmoveClock) {
		this.halfmoveClock = halfmoveClock;
	}

	public int getFullmoveNumber() {
		return fullmoveNumber;
	}

	public void setFullmoveNumber(int fullmoveNumber) {
		this.fullmoveNumber = fullmoveNumber;
	}

	public Map<Color, Player> getPlayers() {
		return players;
	}

	public void setPlayers(Map<Color, Player> players) {
		this.players = players;
	}

	public List<Move> getMoveHistory() {
		return moveHistory;
	}

	public void setMoveHistory(List<Move> moveHistory) {
		this.moveHistory = moveHistory;
	}

}
