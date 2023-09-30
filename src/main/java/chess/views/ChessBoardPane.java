package main.java.chess.views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChessBoardPane extends VBox {

	private TextField display;

	public ChessBoardPane() {
		// Create UI components
		display = new TextField();
		display.setEditable(false);

		Button button1 = new Button("1");
		Button button2 = new Button("2");
		// Add more buttons as needed

		// Set up the layout
		setSpacing(10);
		setPadding(new Insets(10));
		getChildren().addAll(display, button1, button2);
		// Add more buttons to the VBox

		// Set up event handlers for button clicks
		button1.setOnAction(e -> display.appendText("1"));
		button2.setOnAction(e -> display.appendText("2"));
		// Add more event handlers for other buttons
	}
}
