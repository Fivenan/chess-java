package main.java.chess.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ChessGameInfoPane extends GridPane {
	public ChessGameInfoPane() {

		Label firstNameLabel = new Label("From");
		TextField firstNameField = new TextField();
		Label lastNameLabel = new Label("To");
		TextField lastNameField = new TextField();

		// Add elements to the grid pane
		add(firstNameLabel, 0, 0);
		add(firstNameField, 0, 1);
		add(lastNameLabel, 1, 0);
		add(lastNameField, 1, 1);

		Button button = new Button("Ok");
		add(button, 0, 2);
	}

	private void handleButtonClick(int row, int col) {
		// Handle the button click (implement game logic here)
		System.out.println("Button clicked: " + row + ", " + col);
	}
}
