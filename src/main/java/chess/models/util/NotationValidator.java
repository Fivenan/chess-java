package main.java.chess.models.util;

import java.util.logging.Logger;

import main.java.chess.models.enums.Color;

public class NotationValidator {

	private static final Logger LOGGER = Logger.getLogger(NotationValidator.class.getName());

	private NotationValidator() {
	}

	/**
	 * Validate the FEN rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
	 * rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1
	 * rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2
	 * rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2
	 * 
	 * @param fen
	 * @return
	 */
	public static boolean isValidFEN(String fen) {

		String[] parts = fen.split(" ", 6);

		if (parts.length != 6) {
			LOGGER.warning("Invalid number of components in FEN: " + fen);
			return false;
		}

		if (!isValidFENPiecePlacement(parts[0])) {
			LOGGER.warning("Invalid piece placement in FEN: " + fen);
			return false;
		}

		if (!isValidTurn(parts[1])) {
			LOGGER.warning("Invalid active color in FEN: " + fen);
			return false;
		}

		if (!isValidCastling(parts[2], parts[0])) {
			LOGGER.warning("Invalid castling availability in FEN: " + fen);
			return false;
		}

		if (!isValidEnPassant(parts[3], parts[0], parts[1])) {
			LOGGER.warning("Invalid en passant target in FEN: " + fen);
			return false;
		}

		if (!isValidHalfmove(parts[4])) {
			LOGGER.warning("Invalid halfmove value in FEN: " + fen);
			return false;
		}

		if (!isValidFullmove(parts[5])) {
			LOGGER.warning("Invalid fullmove value in FEN: " + fen);
			return false;
		}

		return true;
	}

	public static boolean isValidFENPiecePlacement(String piecePlacement) {
		String[] ranks = piecePlacement.split("/");
		if (ranks.length != 8) {
			LOGGER.warning("Invalid number of ranks: " + ranks.length);
			return false;
		}

		for (String rank : ranks) {
			if (!isValidRank(rank)) {
				LOGGER.warning("Invalid piece placement: " + piecePlacement);
				return false;
			}
		}

		return true;
	}

	private static boolean isValidRank(String rank) {
		// Implement logic to validate a single rank
		// Check if the rank consists of valid piece characters and digits

		int file = 0;
		for (char c : rank.toCharArray()) {
			if (Character.isDigit(c)) {
				file += Character.getNumericValue(c);
			} else if (Character.isLetter(c)) {
				// Check if it's a valid piece character
				if ("KQRBNPkqrbnp".indexOf(c) == -1) {
					return false;
				}
				file++;
			} else {
				return false; // Invalid character
			}
		}
		return file == 8;
	}

	private static boolean isValidTurn(String turn) {
		return turn.equals("w") || turn.equals("b");
	}

	private static boolean isValidCastling(String castling, String piecePlacement) {
		if (!castling.equals("-") && !castling.matches("[KQkq]+")) {
			return false;
		}

		// Check that kings and rooks are in the correct positions for castling
		if (castling.contains("K") && !isValidKingsideCastling(piecePlacement, Color.WHITE)) {
			return false;
		}
		if (castling.contains("Q") && !isValidQueensideCastling(piecePlacement, Color.WHITE)) {
			return false;
		}
		if (castling.contains("k") && !isValidKingsideCastling(piecePlacement, Color.BLACK)) {
			return false;
		}
		if (castling.contains("q") && !isValidQueensideCastling(piecePlacement, Color.BLACK)) {
			return false;
		}

		return true;
	}

	private static boolean isValidKingsideCastling(String piecePlacement, Color color) {
		char king = color == Color.WHITE ? 'K' : 'k';
		char rook = color == Color.WHITE ? 'R' : 'r';
		int rank = color == Color.WHITE ? 63 : 0;
		String filledPlacement = replaceDigitWithCharacters(piecePlacement);
		return filledPlacement.charAt(4 + rank) == king && filledPlacement.charAt(7 + rank) == rook;
	}

	private static boolean isValidQueensideCastling(String piecePlacement, Color color) {
		char king = color == Color.WHITE ? 'K' : 'k';
		char rook = color == Color.WHITE ? 'R' : 'r';
		int rank = color == Color.WHITE ? 63 : 0;
		String filledPlacement = replaceDigitWithCharacters(piecePlacement);
		return filledPlacement.charAt(4 + rank) == king && filledPlacement.charAt(0 + rank) == rook;
	}

	private static String replaceDigitWithCharacters(String input) {
		StringBuilder result = new StringBuilder();
		for (char c : input.toCharArray()) {
			if (Character.isDigit(c)) {
				for (int i = 0; i < (c - '0'); i++) {
					result.append(i);
				}
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}

	private static boolean isValidEnPassant(String enPassant, String piecePlacement, String turn) {
		if (enPassant.equals("-")) {
			return true; // No en passant target square specified, which is valid
		}

		// Check that the en passant target square is a valid algebraic notation
		if (!enPassant.matches("[a-h][36]")) {
			return false;
		}

		// Convert algebraic notation to array indices
		int targetFile = enPassant.charAt(0) - 'a';
		int targetRank = enPassant.charAt(1) - '1';
		int targetRankInArray = 7 - targetRank;

		// Determine the color of the current player based on FEN
		// We check the pawn that just moved, not the one moving
		Color color = turn.equals("w") ? Color.WHITE : Color.BLACK;
		char pawnWithColor = color != Color.WHITE ? 'P' : 'p';

		// Check if turn corresponds to the rank
		if ((targetRank == 5 && color != Color.WHITE) || //
				(targetRank == 2 && color != Color.BLACK)) {
			return false;
		}

		// Check for a pawn of the correct color in front of the en passant target
		// If it is white's turn, then black's pawn just moved two squares
		int pawnRankOffset = color == Color.WHITE ? -1 : 1;
		int pawnRank = targetRank + pawnRankOffset;
		int pawnRankInArray = 7 - pawnRank;

		char[][] fen2d = BoardUtil.fenToCharArray2d(piecePlacement);

		// Check if the tile behind the to-be-captured pawn is not empty
		if (fen2d[targetRankInArray][targetFile] != '*') {
			return false;
		}

		// Check if the tile is not occupied by the right pawn
		if (fen2d[pawnRankInArray][targetFile] != pawnWithColor) {
			return false;
		}

		return true;
	}

	private static boolean isValidHalfmove(String halfmove) {
		try {
			int halfmoveValue = Integer.parseInt(halfmove);
			return halfmoveValue >= 0; // Halfmove value should be non-negative
		} catch (NumberFormatException e) {
			return false; // Invalid halfmove value
		}
	}

	private static boolean isValidFullmove(String fullmove) {
		try {
			int fullmoveValue = Integer.parseInt(fullmove);
			return fullmoveValue > 0; // Fullmove value should be positive
		} catch (NumberFormatException e) {
			return false; // Invalid fullmove value
		}
	}

}
