package main.java.chess.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class NewWindow extends Application {

//	@Override
	public void start2(final Stage primaryStage) {

		Button button = new Button();
		button.setText("Open a New Window");

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Label secondLabel = new Label("I'm a Label on new Window");

				StackPane secondaryLayout = new StackPane();
				secondaryLayout.getChildren().add(secondLabel);

				Scene secondScene = new Scene(secondaryLayout, 230, 100);

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setTitle("Second Stage");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(primaryStage.getX() + 200);
				newWindow.setY(primaryStage.getY() + 100);

				newWindow.show();
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(button);

		Scene scene = new Scene(root, 450, 250);

		primaryStage.setTitle("JavaFX Open a new Window (o7planning.org)");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override /* from w w w .jav a 2 s . com */
	public void start(Stage primaryStage) {
		// Create a GridPane
		GridPane pane = new GridPane();

		// Create 64 rectangles and add to pane
		int count = 0;
		double s = 100; // side of rectangle
		for (int i = 0; i < 8; i++) {
			count++;
			for (int j = 0; j < 8; j++) {
				Rectangle r = new Rectangle(s, s, s, s);
				if (count % 2 == 0)
					r.setFill(Color.WHITE);
				pane.add(r, j, i);
				count++;
			}
		}

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("de.fivenan.java.chess");
		primaryStage.setScene(scene);
		primaryStage.show();
		;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
