package main.java.chess.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ChessGameInfoPane extends GridPane {
	public ChessGameInfoPane() {

		Label startTileLabel = new Label("From");
		TextField startTileField = new TextField();
		Label endTileLabel = new Label("To");
		TextField endTileField = new TextField();

		// Add elements to the grid pane
		add(startTileLabel, 0, 0);
		add(startTileField, 0, 1);
		add(endTileLabel, 1, 0);
		add(endTileField, 1, 1);

		Button button = new Button("Ok");
		button.setOnAction(e -> handleMove(startTileField.getText(), endTileField.getText()));
		add(button, 0, 2);
	}

	private void handleMove(String startTile, String endTile) {
		System.out.println("Moving from " + startTile + " to " + endTile);
	}
}
