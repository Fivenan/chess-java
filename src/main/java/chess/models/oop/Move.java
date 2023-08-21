package main.java.chess.models.oop;

public class Move {
	private Tile start;
	private Tile end;

	public Move(Tile start, Tile end) {
		this.start = start;
		this.end = end;
	}

	public Tile getStart() {
		return start;
	}

	public void setStart(Tile start) {
		this.start = start;
	}

	public Tile getEnd() {
		return end;
	}

	public void setEnd(Tile end) {
		this.end = end;
	}

	public String toString() {
		return "[" + start.getPosition() + ", " + end.getPosition() + "]";
	}

	public String toString(int moveNumber) {
		return "" + moveNumber + ". " + start.getPosition() + " " + end.getPosition();
	}
}
