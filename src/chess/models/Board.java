package chess.models;

import chess.models.Move;
import chess.models.Tile;
import chess.models.pieces.*;

public class Board {
	public Tile[][] tiles = new Tile[8][8];
	public boolean whiteTurn = true;
	public boolean blackTurn = false;
	public boolean blackCanCastleKingSide = true;
	public boolean blackCanCastleQueenSide = true;
	public boolean whiteCanCastleKingSide = true;
	public boolean whiteCanCastleQueenSide = true;

	public Board() {
		for (int y = 2; y < 6; y++) {
			for (int x = 0; x < 8; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}
		tiles[0][0] = new Tile(0, 0, new Rook(true));
		tiles[1][0] = new Tile(1, 0, new Knight(true));
		tiles[2][0] = new Tile(2, 0, new Bishop(true));
		tiles[3][0] = new Tile(3, 0, new Queen(true));
		tiles[4][0] = new Tile(4, 0, new King(true));
		tiles[5][0] = new Tile(5, 0, new Bishop(true));
		tiles[6][0] = new Tile(6, 0, new Knight(true));
		tiles[7][0] = new Tile(7, 0, new Rook(true));
		tiles[0][7] = new Tile(0, 7, new Rook(false));
		tiles[1][7] = new Tile(1, 7, new Knight(false));
		tiles[2][7] = new Tile(2, 7, new Bishop(false));
		tiles[3][7] = new Tile(3, 7, new Queen(false));
		tiles[4][7] = new Tile(4, 7, new King(false));
		tiles[5][7] = new Tile(5, 7, new Bishop(false));
		tiles[6][7] = new Tile(6, 7, new Knight(false));
		tiles[7][7] = new Tile(7, 7, new Rook(false));
		for (int x = 0; x < 8; x++) {
			tiles[x][1] = new Tile(x, 1, new Pawn(true));
			tiles[x][6] = new Tile(x, 6, new Pawn(false));
		}
	}

	// load position directly using Forsyth-Edwards Notation
	public Board(String init) {
		String[] parts = init.split(" ", 6);
		String[] rows = parts[0].split("/", 8);
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				tiles[x][y] = new Tile(x, y);
			}
		}
		for (int y = 0; y < 8; y++) {
			int x = 0;
			for (char c : rows[y].toCharArray()) {
				if (Character.isDigit(c)) {
					x += c - 48; // char 1 is \49
					continue;
				}
				tiles[x++][7 - y].place(Piece.getPieceFromChar(c));
			}
		}
	}

	// load position from set of moves
//	public Board(Move[] moves) {
//
//	}

	public void move(Move m) {

	}

//	public void move(String s, boolean isWhite) {
//		Piece p;
//		if (s.length() == 2) {
//			p = new Pawn(isWhite);
//		} else if (s.length() == 3){
//			char pchar;
//			if (isWhite) {
//				pchar = Character.toUpperCase(s.charAt(0));
//			} else {
//				pchar = Character.toLowerCase(s.charAt(0));
//			}
//			p = Piece.getPieceFromChar(pchar);
//		} else {
//			System.out.println("try again");
//			return;
//		}
//		int possiblePieces = 0;
//		Move m;
//		for (int y = 0; y < 8; y++) {
//			for (int x = 0; x < 8; x++) {
//				if (tiles[x][y].piece instanceof p.getClass() && p.movesFrom(this, x, y).contains(tileOf(s))){
//					possiblePieces++;
//					m = new Move(p, tiles[x][y], tileOf(s.substring(1,3)));
//				}
//			}
//		}
//		if (possiblePieces > 1) {
//			System.out.println("try again");
//			return;
//		}
////        applyMove(m);
//	}

	public String toString() {
		StringBuilder res = new StringBuilder();
//        Board.values().stream().forEachOrdered(k -> System.out.println(k));
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				res.append(tiles[x][y].symbol());
			}
			res.append("\n");
		}
		return res.toString();
	}

	public String toFEN() {
		StringBuilder res = new StringBuilder();
		for (int y = 0; y < 8; y++) {
			int tmp = 0;
			for (int x = 0; x < 8; x++) {

				if (tiles[x][7 - y].piece == null) {
					res.append(x == 7 ? ++tmp : "");
					tmp++;
				} else {
					if (tmp != 0)
						res.append(tmp);
					res.append(tiles[x][7 - y].piece.notation);
					tmp = 0;
				}
			}
			res.append('/');
		}
		return res.deleteCharAt(res.length() - 1).toString();
	}

	public Tile tileOf(String s) {
		assert s.length() == 2;
		assert s.charAt(0) >= 'a' && s.charAt(0) <= 'h';
		assert Character.getNumericValue(s.charAt(1)) >= 1
				&& Character.getNumericValue(s.charAt(1)) <= 8;
		return tiles[s.charAt(0)-96][Character.getNumericValue(s.charAt(1))];
	}

	public static enum Player {
		Player1,	//always move first
		Player2
	}

	private Board.Player currentMove = Board.Player.Player1;
	private Piece[] player1Pieces = new Piece[16];
	private Piece[] player2Pieces = new Piece[16];

//    public Board() {
//        initBoard();
//    }

	private void initBoard() {
		currentMove = Board.Player.Player1;
		for (int i = 0; i < 16; i++) {
			player1Pieces[i] = new Pawn(true);
		}
	}

	public void resetBoard() {
		currentMove = Board.Player.Player1;
		for (int i = 0; i < 16; i++) {
//			player1Pieces[i].resetLocation();
//			player2Pieces[i].resetLocation();
		}
	}

	//change current move to other player
	public void done() {
		if (currentMove == Board.Player.Player1) {
			currentMove = Board.Player.Player2;
		} else {
			currentMove = Board.Player.Player1;
		}
	}

}
