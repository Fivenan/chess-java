package main.java.chess.models.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FENUtil {

	private FENUtil() {
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
