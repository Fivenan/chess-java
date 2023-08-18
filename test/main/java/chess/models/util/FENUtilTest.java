package main.java.chess.models.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FENUtilTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFenToCharArray2d() {
		String fen = "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R";
		char[][] result = FENUtil.fenToCharArray2d(fen);
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
		assertEquals(FENUtil.charArray2dToString(fenArray), str);
	}

}
