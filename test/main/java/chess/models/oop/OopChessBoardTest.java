/**
 * 
 */
package main.java.chess.models.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.java.chess.models.oop.moves.Move;
import main.java.chess.models.oop.moves.NormalMove;

/**
 * @param <ChessBoard>
 * 
 */
class OopChessBoardTest {

	private static final Logger LOGGER = Logger.getLogger(OopChessBoardTest.class.getName());

	OopChessBoard chessBoard;

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
	@ValueSource(strings = { "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
			"rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1" })
	void testSetBoard(String fen) {
		LOGGER.info("Testing set board method from the starting FEN" + fen);
		chessBoard.setBoard(fen);
		System.out.println(chessBoard.toString());
		System.out.println(chessBoard.getFENBoard());
		System.out.println(fen);
		assertEquals(fen, chessBoard.getFEN());
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#emptyBoard()}.
	 */
	@Test
	void testEmptyBoard() {
		LOGGER.info("Testing empty board method from a random starting FEN");
		String fen = "rnbqk2r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R w KQkq - 0 1";
		chessBoard.setBoard(fen);
		chessBoard.emptyBoard();
		System.out.println('\u2654');
		char whiteKingSymbol = '\u2654';
		System.out.println("White King: " + whiteKingSymbol);
		System.out.println(chessBoard.toString());
		System.out.println(chessBoard.getFENBoard());
		System.out.println(chessBoard.getFEN());
		assertEquals("8/8/8/8/8/8/8/8", chessBoard.getFENBoard());
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
	 * {@link main.java.chess.models.oop.OopChessBoard#moveTo(java.lang.String)}.
	 */
	@Test
	void testMoveToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#apply(main.java.chess.models.Move)}.
	 */
	@Test
	void testApplyMoveCheckBoard() {
		String startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String endFen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR";
		chessBoard.setBoard(startFen);
		Move move = new NormalMove(chessBoard.getTile(6, 4), chessBoard.getTile(4, 4));
		chessBoard.apply(move);
		assertEquals(endFen, chessBoard.getFENBoard());
	}

	/**
	 * Test method for
	 * {@link main.java.chess.models.oop.OopChessBoard#apply(main.java.chess.models.Move)}.
	 */
	@Test
	void testApplyMoveCompleteFEN() {
		String startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String endFen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
		chessBoard.setBoard(startFen);
		Move move = new NormalMove(chessBoard.getTile(6, 4), chessBoard.getTile(4, 4));
		chessBoard.apply(move);
		assertEquals(endFen, chessBoard.getFENBoard());
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
