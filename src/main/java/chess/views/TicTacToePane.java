package main.java.chess.views;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TicTacToePane extends GridPane {
	public TicTacToePane() {
		// Create the Tic-Tac-Toe game board
		Button[][] buttons = new Button[3][3];

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				Button button = new Button();
				button.setMinSize(100, 100);
				buttons[row][col] = button;

				// Set up event handler for button clicks
//                button.setOnAction(e -> handleButtonClick(row, col));
			}
		}

		// Add buttons to the grid
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				add(buttons[row][col], col, row);
			}
		}
	}

	private void handleButtonClick(int row, int col) {
		// Handle the button click (implement game logic here)
		System.out.println("Button clicked: " + row + ", " + col);
	}
}
