package main.java.chess.views;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.java.chess.models.ChessGame;
import main.java.chess.models.enums.Color;
import main.java.chess.models.oop.Tile;

public class ChessGameInfoPane extends GridPane {

	private static final Logger logger = Logger.getLogger(ChessGameInfoPane.class.getName());

	private ChessGame chessGame;

	Label startTileLabel;
	Label endTileLabel;
	TextField startTileField;
	TextField endTileField;

	public ChessGameInfoPane(ChessGame chessGame) {

		this.chessGame = chessGame;

		startTileLabel = new Label("From");
		endTileLabel = new Label("To");
		startTileField = new TextField();
		endTileField = new TextField();

		// Add elements to the grid pane
		add(startTileLabel, 0, 0);
		add(startTileField, 0, 1);
		add(endTileLabel, 1, 0);
		add(endTileField, 1, 1);

		Button button = new Button("Ok");
		button.setOnAction(e -> handleMove(startTileField.getText(), endTileField.getText()));
		add(button, 0, 2);
		Button getPossibleMovesButton = new Button("Get Possible Moves");
		button.setOnAction(e -> handleGetPossibleMoves(startTileField.getText()));
		add(getPossibleMovesButton, 1, 2);

	}

	private void handleMove(String startTile, String endTile) {
		logger.info("Moving from " + startTile + " to " + endTile);
	}

	private void handleGetPossibleMoves(String startTile) {
		List<Tile> availableTargets = chessGame.availableTargets(Color.WHITE, startTile);
		endTileField.setText(availableTargets.stream().map(t -> t.getPosition()).collect(Collectors.joining(", ")));
	}
}
