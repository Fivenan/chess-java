package main.java.chess.models.pieces;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.java.chess.models.oop.Move;
import main.java.chess.models.oop.OopChessBoard;

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
		List<Move> validMoves = board.getTile(4, 3).getPiece().generateLegalMoves(board, 4, 3);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = { "8/8/8/8/8/1B6/2P5/8 w - - 0 1", //
			"8/8/8/8/6k1/1B6/8/8 w - - 0 1" }) //
	void testGenerateLegalMoves2(String fen) {
		board.setBoard(fen);
		List<Move> validMoves = board.getTile(5, 1).getPiece().generateLegalMoves(board, 5, 1);
		validMoves.stream().map(Move::toString).forEach(System.out::println);
	}

}
