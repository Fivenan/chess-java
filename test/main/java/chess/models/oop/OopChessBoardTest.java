/**
 * 
 */
package main.java.chess.models.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.java.chess.models.ChessBoard;

/**
 * @param <ChessBoard>
 * 
 */
class OopChessBoardTest {

	ChessBoard chessBoard;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		chessBoard = new OopChessBoard();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#initialize(java.lang.String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R",
			"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR" })
	void testSetBoard(String fen) {
		chessBoard.setBoard(fen);
		assertEquals(chessBoard.getFEN(), fen);
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#emptyBoard()}.
	 */
	@Test
	void testEmptyBoard() {
		String fen = "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R";
		chessBoard.setBoard(fen);
		chessBoard.emptyBoard();
		assertEquals(chessBoard.getFEN(), "8/8/8/8/8/8/8/8");
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#restartBoard()}.
	 */
	@Test
	void testRestartBoard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#move(java.lang.String)}.
	 */
	@Test
	void testMoveString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#move(main.java.chess.models.Move)}.
	 */
	@Test
	void testMoveMove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.java.chess.models.oop.OopChessBoard#getTurn()}.
	 */
	@Test
	void testGetTurn() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#getMoveNumber()}.
	 */
	@Test
	void testGetMoveNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.java.chess.models.oop.OopChessBoard#getFEN()}.
	 */
	@Test
	void testGetFEN() {
		fail("Not yet implemented");
	}

}
