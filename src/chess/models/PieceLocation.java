package chess.models;

import java.util.Objects;

public class PieceLocation {
	
	/*
	 *  location 0 column, row
	 *  ---------------------------------
	 *  | 			Player 1			|
	 *  ---------------------------------
	 *  |1-1|2-1|3-1|4-1|5-1|6-1|7-1|8-1|
	 *  |1-2|2-2|3-2|4-2|5-2|6-2|7-2|8-2|
	 *  |1-3|2-3|3-3|4-3|5-3|6-3|7-3|8-3|
	 *  |1-4|2-4|3-4|4-4|5-4|6-4|7-4|8-4|
	 *  |1-5|2-5|3-5|4-5|5-5|6-5|7-5|8-5|
	 *  |1-6|2-6|3-6|4-6|5-6|6-6|7-6|8-6|
	 *  |1-7|2-7|3-7|4-7|5-7|6-7|7-7|8-7|
	 *  |1-8|2-8|3-8|4-8|5-8|6-8|7-8|8-8|
	 *  ---------------------------------
	 *  | 			Player 2			|
	 *  ---------------------------------
	 * 
	 * */

	private int columnLoc = 0;
	private int rowLoc = 0;
	
	public PieceLocation() {		
	}
	
	public PieceLocation(int columnLoc, int rowLoc) {
		this.columnLoc = columnLoc;
		this.rowLoc = rowLoc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columnLoc, rowLoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PieceLocation other = (PieceLocation) obj;
		return columnLoc == other.columnLoc && rowLoc == other.rowLoc;
	}

	public int getColumnLoc() {
		return columnLoc;
	}

	public void setColumnLoc(int columnLoc) {
		this.columnLoc = columnLoc;
	}

	public int getRowLoc() {
		return rowLoc;
	}

	public void setRowLoc(int rowLoc) {
		this.rowLoc = rowLoc;
	}
	
	
}
