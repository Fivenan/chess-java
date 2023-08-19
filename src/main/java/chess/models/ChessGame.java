package main.java.chess.models;

import main.java.chess.controllers.Game;
import main.java.chess.models.oop.OopChessBoard;
import main.java.chess.views.IView;
import main.java.chess.views.Terminal;

public class ChessGame {

    public static void main(String[] args) {
//        Board board1 = new Board();
//        System.out.println(board1);

        String fen1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String fen2 = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        String fen3 = "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2";
//        Board board2 = new Board(fen2);
//        System.out.println(board2);
//        Board board3 = new Board(fen3);
//        System.out.println(board3);
//        System.out.println(board3.toFEN());
        
        IView terminalView = new Terminal();
        Game game = new Game();        
        game.setViewer(terminalView); 
        
		ChessBoard board2 = new OopChessBoard();
		board2.setBoard(fen2);
		game.setBoard(board2);
        game.printBoard();
        
		ChessBoard board3 = new OopChessBoard();
		board2.setBoard(fen3);
        game.setBoard(board3);
        game.printBoard();
        
		System.out.println(board3.getFEN());
    }

}