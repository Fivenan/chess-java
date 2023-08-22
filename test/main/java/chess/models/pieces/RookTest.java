package main.java.chess.models.pieces;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.java.chess.models.oop.Move;
import main.java.chess.models.oop.OopChessBoard;

class RookTest {

	OopChessBoard board = new OopChessBoard();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/5R2/8/8/8 w - - 0 1", //
			"8/8/5B2/8/5R2/8/8/8 w - - 0 1", //
			"8/8/5B2/8/5r2/8/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves(String fen) {
		board.setBoard(fen);
		List<Move> validMoves = board.getTile(4, 5).getPiece().generateLegalMoves(board, 4, 5);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/1R6/1P6/8 w - - 0 1", //
			"8/8/8/8/1k6/1R6/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves2(String fen) {
		board.setBoard(fen);
		List<Move> validMoves = board.getTile(5, 1).getPiece().generateLegalMoves(board, 5, 1);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "R7/8/8/8/8/8/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves3(String fen) {
		board.setBoard(fen);
		List<Move> validMoves = board.getTile(0, 0).getPiece().generateLegalMoves(board, 0, 0);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "7R/8/8/8/8/8/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves4(String fen) {
		board.setBoard(fen);
		List<Move> validMoves = board.getTile(0, 7).getPiece().generateLegalMoves(board, 0, 7);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/8/8/R7 w - - 0 1" }) //
	void testGenerateLegalMoves5(String fen) {
		board.setBoard(fen);
		List<Move> validMoves = board.getTile(7, 0).getPiece().generateLegalMoves(board, 7, 0);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/8/8/7R w - - 0 1" }) //
	void testGenerateLegalMoves6(String fen) {
		board.setBoard(fen);
		List<Move> validMoves = board.getTile(7, 7).getPiece().generateLegalMoves(board, 7, 7);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

}
