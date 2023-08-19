package main.java.chess.models.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardUtilTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
	@MethodSource("provideParametersForRefreshFEN")
	void testIsValid(String fen, String expected) {
		assertEquals(expected, BoardUtil.refreshFEN(fen));
	}

	private static Stream<Arguments> provideParametersForRefreshFEN() {
		return Stream.of(
				Arguments.of("rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1 w KQkq - 0 1",
						"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"),
				Arguments.of("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2",
						"rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"),
				Arguments.of("rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1Q b K - 0 1",
						"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1Q w - - 0 1"),
				Arguments.of("rnbqkbnr/p1p2ppp/1p2p3/1pPp4/8/P7/1P1PPPPP/RNBQKBNR w KQkq b6 0 2",
						"rnbqkbnr/p1p2ppp/1p2p3/1pPp4/8/P7/1P1PPPPP/RNBQKBNR w - - 0 1"),
				Arguments.of("rnb1kbnr/pp1q1p1p/2p1p3/2Pp1PpP/8/P7/1P1PP1P1/RNBQKBNR w KQkq g6 -1 6",
						"rnb1kbnr/pp1q1p1p/2p1p3/2Pp1PpP/8/P7/1P1PP1P1/RNBQKBNR w - - 0 1"),
				Arguments.of("rnb1kbnr/pp1q1p1p/2p1p3/2Pp1PpP/8/P7/1P1PP1P1/RNBQKBNR w KQkq g6 0 -5",
						"rnb1kbnr/pp1q1p1p/2p1p3/2Pp1PpP/8/P7/1P1PP1P1/RNBQKBNR w - - 0 1"));
	}

	@Test
	void testFenToCharArray2d() {
		String fen = "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R";
		char[][] result = BoardUtil.fenToCharArray2d(fen);
		char[][] fenArray = { //
				{ 'r', 'n', 'b', 'q', '*', 'k', '*', 'r' }, //
				{ 'p', 'p', '*', '*', '*', 'p', '*', 'p' }, //
				{ '*', '*', '*', '*', '*', '*', 'p', 'P' }, //
				{ '*', '*', 'p', 'N', 'P', '*', '*', '*' }, //
				{ '*', '*', 'p', '*', '*', '*', 'n', '*' }, //
				{ '*', '*', '*', '*', '*', 'N', '*', '*' }, //
				{ 'P', 'P', '*', 'Q', '*', 'P', 'P', '*' }, //
				{ 'R', '*', '*', '*', 'K', 'B', '*', 'R' } //
		};
		assertArrayEquals(result, fenArray);
	}

	@Test
	void testCharArray2dToString() {
		char[][] fenArray = { //
				{ 'r', 'n', 'b', 'q', '*', 'k', '*', 'r' }, //
				{ 'p', 'p', '*', '*', '*', 'p', '*', 'p' }, //
				{ '*', '*', '*', '*', '*', '*', 'p', 'P' }, //
				{ '*', '*', 'p', 'N', 'P', '*', '*', '*' }, //
				{ '*', '*', 'p', '*', '*', '*', 'n', '*' }, //
				{ '*', '*', '*', '*', '*', 'N', '*', '*' }, //
				{ 'P', 'P', '*', 'Q', '*', 'P', 'P', '*' }, //
				{ 'R', '*', '*', '*', 'K', 'B', '*', 'R' } //
		};
		String str = "rnbq*k*r" + System.lineSeparator() //
				+ "pp***p*p" + System.lineSeparator() //
				+ "******pP" + System.lineSeparator() //
				+ "**pNP***" + System.lineSeparator() //
				+ "**p***n*" + System.lineSeparator() //
				+ "*****N**" + System.lineSeparator() //
				+ "PP*Q*PP*" + System.lineSeparator() //
				+ "R***KB*R";
		assertEquals(BoardUtil.charArray2dToString(fenArray), str);
	}

}
