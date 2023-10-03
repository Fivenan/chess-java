package main.java.chess.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ChessBoardPane extends GridPane {

	private TextField display;

	public ChessBoardPane() {
		Button[][] buttons = new Button[8][8];

		double windowWidth = getWidth();
		double windowHeight = getHeight();

		double tileWidth = windowWidth > windowHeight ? windowHeight / 8 : windowWidth / 8;
		tileWidth = 50;

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Button button = new Button();
				button.setMinSize(tileWidth, tileWidth);
				button.setText("" + row + "" + col);
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
//                button.setOnAction(e -> handleButtonClick(row, col));
			}
		}

		// Add buttons to the grid
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				add(buttons[row][col], col, row);
			}
		}

		Label firstNameLabel = new Label("From");
		TextField firstNameField = new TextField();
		Label lastNameLabel = new Label("To");
		TextField lastNameField = new TextField();

		// Add elements to the grid pane
		add(firstNameLabel, 8, 0);
		add(firstNameField, 9, 0);
		add(lastNameLabel, 8, 1);
		add(lastNameField, 9, 1);

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
}
