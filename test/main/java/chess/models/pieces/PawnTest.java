package main.java.chess.models.pieces;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.Tile;
import main.java.chess.models.oop.moves.OopMove;

class PawnTest {

	OopChessBoard board = new OopChessBoard();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	private void printResult(int rank, int file, List<OopMove> validMoves) {
		String res = "Starting position: " + board.getTile(rank, file).getPosition() + ". End positions: " + validMoves
				.stream().map(OopMove::getEnd).map(Tile::getPosition).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(res);
	}

	/**
	 * Test 2 steps for black pawn
	 */
	@ParameterizedTest
	@ValueSource(strings = { "8/p7/8/8/8/8/8/8 b - - 0 1", //
			"8/p7/P7/8/8/8/8/8 b - - 0 1", //
			"8/p7/8/P7/8/8/8/8 b - - 0 1" }) //
	void testGenerateLegalMoves1(String fen) {
		board.setBoard(fen);
		int rank = 1;
		int file = 0;
		List<OopMove> validMoves = board.getTile(rank, file).getPiece().generateValidMoves(board, rank, file);
		printResult(rank, file, validMoves);
	}
	/**
	 * Test 2 steps for white pawn
	 */
	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/8/P7/8 w - - 0 1", //
			"8/8/8/8/p7/8/P7/8 w - - 0 1", //
			"8/8/8/8/8/p7/P7/8 w - - 0 1" }) //
	void testGenerateLegalMoves2(String fen) {
		board.setBoard(fen);
		int rank = 6;
		int file = 0;
		List<OopMove> validMoves = board.getTile(rank, file).getPiece().generateValidMoves(board, rank, file);
		printResult(rank, file, validMoves);
	}

	/**
	 * Test en passant for black pawn
	 */
	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/2pP4/8/8/8 b - d3 0 1", //
			"8/8/8/8/2pP4/8/8/8 b - - 0 1", //
			"8/8/8/8/1PpP4/8/8/8 b - b3 0 1", //
			"8/8/8/8/1Pp5/8/8/8 b - b3 0 1", //
			"8/8/8/8/1Pp5/8/8/8 b - - 0 1" }) //
	void testGenerateLegalMoves3(String fen) {
		board.setBoard(fen);
		int rank = 4;
		int file = 2;
		List<OopMove> validMoves = board.getTile(rank, file).getPiece().generateValidMoves(board, rank, file);
		printResult(rank, file, validMoves);
	}

	/**
	 * Test en passant for white pawn
	 */
	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/4Pp2/8/8/8/8 w - f6 0 1", //
			"8/8/8/4Pp2/8/8/8/8 w - - 0 1", //
			"8/8/8/3pPp2/8/8/8/8 w - d6 0 1", //
			"8/8/8/3pPp2/8/8/8/8 w - f6 0 1", //
			"8/8/8/3pP3/8/8/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves4(String fen) {
		board.setBoard(fen);
		int rank = 3;
		int file = 4;
		List<OopMove> validMoves = board.getTile(rank, file).getPiece().generateValidMoves(board, rank, file);
		printResult(rank, file, validMoves);
	}

	/**
	 * Test promotion for black pawn
	 */
	@ParameterizedTest
	@ValueSource(chars = { 'q', 'b', 'n', 'r' }) //
	void testGenerateLegalMoves5(char c) {
		String fen = "8/8/8/8/8/8/3p4/8 b - - 0 1";
		board.setBoard(fen);
		int rank = 6;
		int file = 3;
		List<OopMove> validMoves = board.getTile(rank, file).getPiece().generateValidMoves(board, rank, file);
		printResult(rank, file, validMoves);
	}

	/**
	 * Test promotion for black pawn
	 */
	@ParameterizedTest
	@ValueSource(chars = { 'Q', 'B', 'N', 'R' }) //
	void testGenerateLegalMoves6(char c) {
		String fen = "8/3P4/8/8/8/8/8/8 w - - 0 1";
		board.setBoard(fen);
		int rank = 1;
		int file = 3;
		List<OopMove> validMoves = board.getTile(rank, file).getPiece().generateValidMoves(board, rank, file);
		printResult(rank, file, validMoves);
	}

}
