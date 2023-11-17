package main.java.chess.models.pieces;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.models.oop.moves.OopMove;

class BishopTest {

	OopChessBoard board = new OopChessBoard();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/3B4/8/8/8 w - - 0 1", //
			"8/8/5R2/8/3B4/8/8/8 w - - 0 1", //
			"8/8/5r2/8/3B4/8/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves(String fen) {
		board.setBoard(fen);
		List<OopMove> validMoves = board.getTile(4, 3).getPiece().generateValidMoves(board, 4, 3);
		validMoves.stream().map(OopMove::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/1B6/2P5/8 w - - 0 1", //
			"8/8/8/8/6k1/1B6/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves2(String fen) {
		board.setBoard(fen);
		List<OopMove> validMoves = board.getTile(5, 1).getPiece().generateValidMoves(board, 5, 1);
		validMoves.stream().map(OopMove::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "B7/8/8/8/8/8/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves3(String fen) {
		board.setBoard(fen);
		List<OopMove> validMoves = board.getTile(0, 0).getPiece().generateValidMoves(board, 0, 0);
		validMoves.stream().map(OopMove::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "7B/8/8/8/8/8/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves4(String fen) {
		board.setBoard(fen);
		List<OopMove> validMoves = board.getTile(0, 7).getPiece().generateValidMoves(board, 0, 7);
		validMoves.stream().map(OopMove::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/8/8/B7 w - - 0 1" }) //
	void testGenerateLegalMoves5(String fen) {
		board.setBoard(fen);
		List<OopMove> validMoves = board.getTile(7, 0).getPiece().generateValidMoves(board, 7, 0);
		validMoves.stream().map(OopMove::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/8/8/7B w - - 0 1" }) //
	void testGenerateLegalMoves6(String fen) {
		board.setBoard(fen);
		List<OopMove> validMoves = board.getTile(7, 7).getPiece().generateValidMoves(board, 7, 7);
		validMoves.stream().map(OopMove::toString).forEach(System.out::println);
	}

}
