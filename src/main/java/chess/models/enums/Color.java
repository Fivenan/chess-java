package main.java.chess.models.enums;

public enum Color {
	WHITE("#f9f7f1"), BLACK("#636260");

	private String hex;

	private Color(String hex) {
		this.hex = hex;
	}

	public String getHex() {
		return hex;
	}
}
