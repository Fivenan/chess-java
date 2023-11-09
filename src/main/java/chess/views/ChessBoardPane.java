package main.java.chess.views;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.chess.models.ChessGame;
import main.java.chess.models.Player;
import main.java.chess.models.oop.OopChessBoard;

public class ChessBoardPane extends GridPane {

	private TextField display;

	private ChessGame chessGame;
	private OopChessBoard oopChessBoard;

	private Player activePlayer;

	public ChessBoardPane(ChessGame chessGame) {

		this.chessGame = chessGame;
		oopChessBoard = new OopChessBoard();
		oopChessBoard.setBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		Button[][] buttons = new Button[8][8];

		double windowWidth = getWidth();
		double windowHeight = getHeight();

		double tileWidth = windowWidth > windowHeight ? windowHeight / 8 : windowWidth / 8;
		tileWidth = 50;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Button button = new Button();
				button.setMinSize(tileWidth, tileWidth);
				final Integer row = Integer.valueOf(i);
				final Integer col = Integer.valueOf(j);
				button.setText("" + row + "" + col);
				button.setText("" + oopChessBoard.getTile(row, col).getNotation());
//				if ((row + col) % 2 == 0) {
//					button.setStyle("-fx-background-color: " + Color.WHITE.getHex() + "; ");
//				} else {
//					button.setStyle("-fx-background-color: " + Color.BLACK.getHex() + "; ");
//				}
//				if (row == 0 && col == 0) {
//					button.setText("");
//				}
				buttons[row][col] = button;

				// Set up event handler for button clicks
				button.setOnAction(e -> handleButtonClick(e, row, col));
			}
		}

		// Add buttons to the grid
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				add(buttons[row][col], col, row);
			}
		}

//		// Create UI components
//		display = new TextField();
//		display.setEditable(false);
//
//		Button button1 = new Button("1");
//		Button button2 = new Button("2");
//		// Add more buttons as needed
//
//		// Set up the layout
//		setSpacing(10);
//		setPadding(new Insets(10));
//		getChildren().addAll(display, button1, button2);
//		// Add more buttons to the VBox
//
//		// Set up event handlers for button clicks
//		button1.setOnAction(e -> display.appendText("1"));
//		button2.setOnAction(e -> display.appendText("2"));
//		// Add more event handlers for other buttons
	}

	private Object handleButtonClick(ActionEvent e, int row, int col) {
		chessGame.availableTargets(activePlayer, row, col);
		return null;
	}
}
