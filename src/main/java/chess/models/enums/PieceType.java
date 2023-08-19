package main.java.chess.models.enums;

public enum PieceType {
	KING('K', 'k', '\u2654', '\u265a', 1000), //
	QUEEN('Q', 'q', '\u2655', '\u265b', 9), //
	ROOK('R', 'r', '\u2656', '\u265c', 5), //
	BISHOP('B', 'b', '\u2657', '\u265d', 3), //
	KNIGHT('N', 'n', '\u2658', '\u265e', 3), //
	PAWN('P', 'p', '\u2659', '\u265f', 1);

	private final char whiteNotation;
	private final char blackNotation;
	private final char whiteSymbol;
	private final char blackSymbol;
	private final int value;

	PieceType(char whiteNotation, char blackNotation, char whiteSymbol, char blackSymbol, int value) {
		this.whiteNotation = whiteNotation;
		this.blackNotation = blackNotation;
		this.whiteSymbol = whiteSymbol;
		this.blackSymbol = blackSymbol;
		this.value = value;
	}

	public char getNotation(Color color) {
		return (color == Color.WHITE) ? whiteNotation : blackNotation;
	}

	public char getSymbol(Color color) {
		return (color == Color.WHITE) ? whiteSymbol : blackSymbol;
	}

	public int getValue() {
		return value;
	}
}