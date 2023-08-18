package main.java.chess.models.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NotationValidatorTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
	@ValueSource(strings = { "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R w KQ - 0 1",
			"r5k1/5ppp/p3pnn1/1p6/1P6/P3PP2/B4P1P/1N1R2K1 b - - 0 20",
			"rnbqkbnr/p1p2ppp/4p3/1pPp4/8/P7/1P1PPPPP/RNBQKBNR w KQkq b6 0 2",
			"rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2",
			"rnb1kbnr/pp1q1p1p/2p1p3/2Pp1PpP/8/P7/1P1PP1P1/RNBQKBNR w KQkq g6 0 6",
			"rnb1kbnr/pp1q1p1p/2p1p3/2P2PpP/3pP3/P5P1/1P1P4/RNBQKBNR b KQkq e3 0 7" })
	void testIsValid(String fen) {
		assertTrue(NotationValidator.isValidFEN(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R",
			"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR" })
	void testIsValidPiecePlacement(String fen) {
		assertTrue(NotationValidator.isValidFENPiecePlacement(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R", // length 1
			"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w", // length 2
			"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq", // length 3
			"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -", // length 4
			"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0" }) // length 5
	void testIsValidFENIncompleteComponent(String fen) {
		assertFalse(NotationValidator.isValidFEN(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1 w KQkq - 0 1", // shorter, length is 7
			"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R/PP1Q1PP1 w KQkq - 0 1", // longer, length is 9
			"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1RR w KQkq - 0 1", // last rank has 9 elements
			"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1 w KQkq - 0 1", // last rank only has 7 elements
			"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1X w KQkq - 0 1", // invalid letter (X)
			"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB+R w KQkq - 0 1" }) // invalid character (+)
	void testIsValidFENInvalidPiecePlacement(String fen) {
		assertFalse(NotationValidator.isValidFEN(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { "rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R z KQ - 0 1" }) // invalid turn (z)
	void testIsValidFENIsValidTurn(String fen) {
		assertFalse(NotationValidator.isValidFEN(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { //
			"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1Q b + - 0 1", // invalid notation for castling
			"rnbq1k1r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1Q b K - 0 1", // invalid white kingside casling, no rook
			"rnbqk2r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/Q3KB1R b Q - 0 1", // invalid white queenside casling, no rook
			"rnbqk2r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3BK1R b Q - 0 1", // invalid white queenside casling, no king
			"rnbq1k1q/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R b k - 0 1", // invalid black kingside casling, no rook
			"qnbqk2r/pp3p1p/6pP/2pNP3/2p3n1/5N2/PP1Q1PP1/R3KB1R b q - 0 1" }) // invalid black queenside casling, no
																				// rook
	void testIsValidFENIsValidCastling(String fen) {
		assertFalse(NotationValidator.isValidFEN(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { //
			"rnbqkbnr/p1p2ppp/4p3/1pPp4/8/P7/1P1PPPPP/RNBQKBNR w KQkq b7 0 2", // invalid en passant target notation
			"rnbqkbnr/p1p2ppp/1p2p3/1pPp4/8/P7/1P1PPPPP/RNBQKBNR w KQkq b6 0 2", // there is a piece behind the pawn
			"rnbqkbnr/p1p2ppp/4p3/1RPp4/8/P7/1P1PPPPP/RNBQKBNR w KQkq b6 0 2", // the target is not a pawn
			"rnbqkbnr/p1p2ppp/4p3/1RPp4/8/P7/1P1PPPPP/RNBQKBNR b KQkq b6 0 2", // b6 but it is black's turn
			"rnb1kbnr/pp1q1p1p/2p1p3/2P2PpP/3pP3/P5P1/1P1P4/RNBQKBNR w KQkq e3 0 7" }) // e3 but it is white's turn
	void testIsValidFENIsValidEnPassant(String fen) {
		assertFalse(NotationValidator.isValidFEN(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { //
			"rnb1kbnr/pp1q1p1p/2p1p3/2Pp1PpP/8/P7/1P1PP1P1/RNBQKBNR w KQkq g6 -1 6",
			"rnb1kbnr/pp1q1p1p/2p1p3/2P2PpP/3pP3/P5P1/1P1P4/RNBQKBNR b KQkq e3 a 7" })
	void testIsValidHalfmove(String fen) {
		assertFalse(NotationValidator.isValidFEN(fen));
	}

	@ParameterizedTest
	@ValueSource(strings = { //
			"rnb1kbnr/pp1q1p1p/2p1p3/2Pp1PpP/8/P7/1P1PP1P1/RNBQKBNR w KQkq g6 0 -5",
			"rnb1kbnr/pp1q1p1p/2p1p3/2P2PpP/3pP3/P5P1/1P1P4/RNBQKBNR b KQkq e3 1 b" })
	void testIsValidFullmove(String fen) {
		assertFalse(NotationValidator.isValidFEN(fen));
	}

}
