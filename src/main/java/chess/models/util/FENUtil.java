package main.java.chess.models.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FENUtil {

	public static final String STARTING_POSITION_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	public static final String DEFAULT_FEN_EMBELLISH_ARGUMENTS = " w - - 0 1";

	private FENUtil() {
	}

	/**
	 * If a FEN has a valid 1st argument (piece placement), but one or more other
	 * arguments are invalid, the default FEN argument will be returned.
	 * PiecePlacement w - - 0 1
	 * 
	 * @param fen
	 * @return
	 */
	public static String refreshFEN(String fen) {
		String[] ranks = fen.split(" ");
		if (!NotationValidator.isValidFENPiecePlacement(ranks[0])) {
			return STARTING_POSITION_FEN;
		} else if (!NotationValidator.isValidFEN(fen)) {
			return ranks[0] + DEFAULT_FEN_EMBELLISH_ARGUMENTS;
		} else {
			return fen;
		}
	}

	public static char[][] fenToCharArray2d(String piecePlacement) {
		char[][] result = new char[8][8];

		String[] ranks = piecePlacement.split("/");

		int i = 0, j = 0;
		for (String rank : ranks) {
			for (char c : rank.toCharArray()) {
				if (Character.isDigit(c)) {
					for (int k = 0; k < (c - '0'); k++) {
						result[i][j++] = '*';
					}
				} else {
					result[i][j++] = c;
				}
			}
			i++;
			j = 0;
		}
		return result;
	}

	public static String charArray2dToString(char[][] charArray2D) {
		return Arrays.stream(charArray2D).map(row -> new String(row))
				.collect(Collectors.joining(System.lineSeparator()));
	}
}
