package main.java.chess.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.java.chess.models.ChessGame;

public class ChessApp extends Application {

	ChessGame chessGame;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Create a GridPane to divide the window into two parts
		GridPane gridPane = new GridPane();

		// Add calculator on the left
		ChessBoardPane chessBoardPane = new ChessBoardPane(chessGame);
		gridPane.add(chessBoardPane, 0, 0);

		// Add Tic-Tac-Toe game on the right
		ChessGameInfoPane chessGameInfoPane = new ChessGameInfoPane(chessGame);
		gridPane.add(chessGameInfoPane, 1, 0);

		// Create the main scene
		Scene scene = new Scene(gridPane, 1300, 800);

		// Set up the stage
		primaryStage.setTitle("Surreal Checkmate: Realm of Infinite Moves");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
