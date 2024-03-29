package main.java.chess.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

		// Add Chess Panel
		ChessGameInfoPane chessGameInfoPane = new ChessGameInfoPane(chessGame);
		gridPane.add(chessGameInfoPane, 1, 0);

		// Create the main scene
		Scene scene = new Scene(gridPane, 1300, 800);

		// Set up the stage

		Image icon = new Image("main/resources/icons/icon 0.jpg");

		primaryStage.setTitle("Surreal Checkmate: Realm of Infinite Moves");
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
