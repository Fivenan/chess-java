package chess;

import chess.pieces.*;

import java.util.Map;
import java.util.HashMap;

public class Board {
    Tile[][] tiles = new Tile[8][8];
    public boolean blackCanCastleKingSide;
    public boolean blackCanCastleQueenSide;
    public boolean whiteCanCastleKingSide;
    public boolean whiteCanCastleQueenSide;

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
                tiles[x++][7 - y].place(Piece.getPiecefromChar(c));
            }
        }
    }

    // load position from set of moves
    public Board(Move[] moves) {

    }

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

}
