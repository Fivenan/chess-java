package main.java.chess.views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CalculatorPane extends Application {
	@Override
	public void start(Stage primaryStage) {
		FlowPane pane = new FlowPane();
		pane.setHgap(2);
		pane.setAlignment(Pos.CENTER);
		TextField tfNumber1 = new TextField();
		TextField tfNumber2 = new TextField();
		TextField tfResult = new TextField();
		tfResult.setEditable(false);
		tfNumber1.setPrefColumnCount(3);
		tfNumber2.setPrefColumnCount(3);
		tfResult.setPrefColumnCount(3);

		pane.getChildren().addAll(new Label("Number 1: "), tfNumber1, new Label("Number 2: "), tfNumber2,
				new Label("Result: "), tfResult);

		// Create four buttons
		HBox hBox = new HBox(5);
		Button btAdd = new Button("Add");
		Button btSubtract = new Button("Subtract");
		Button btMultiply = new Button("Multiply");
		Button btDivide = new Button("Divide");
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(hBox);
		BorderPane.setAlignment(hBox, Pos.TOP_CENTER);

		Scene scene = new Scene(borderPane, 250, 150);
		primaryStage.setTitle("java2s.com");
		primaryStage.setScene(scene);
		primaryStage.show();

		btAdd.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNumber1.getText()) + Double.parseDouble(tfNumber2.getText()) + "");
		});

		btSubtract.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNumber1.getText()) - Double.parseDouble(tfNumber2.getText()) + "");
		});

		btMultiply.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNumber1.getText()) * Double.parseDouble(tfNumber2.getText()) + "");
		});

		btDivide.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNumber1.getText()) / Double.parseDouble(tfNumber2.getText()) + "");
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}