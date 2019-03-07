package e00;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);

		HBox top = new HBox();
		HBox bottom = new HBox();
		VBox left = new VBox();
		VBox right = new VBox();
		Pane center = new Pane();

		TextField textField = new TextField();
		Button textFieldButton = new Button("Apply");

		Label text = new Label("TEXTTEXTTEXT");

		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton option0 = new RadioButton();
		RadioButton option1 = new RadioButton();
		RadioButton option2 = new RadioButton();

		CheckBox styleBold = new CheckBox();
		CheckBox styleItalic = new CheckBox();

		Button controlUp = new Button("Up");
		Button controlDown = new Button("Down");
		Button controlLeft = new Button("Left");
		Button controlRight = new Button("Right");

		controlUp.setOnMouseClicked(e -> text.setLayoutY(text.getLayoutY() - 10));
		controlDown.setOnMouseClicked(e -> text.setLayoutY(text.getLayoutY() + 10));
		controlLeft.setOnMouseClicked(e -> text.setLayoutX(text.getLayoutX() - 10));
		controlRight.setOnMouseClicked(e -> text.setLayoutX(text.getLayoutX() + 10));

		option0.setText("Option1");
		option1.setText("Option2");
		option2.setText("Option3");

		styleBold.setText("Checkbox1");
		styleItalic.setText("Checkbox2");

		top.setAlignment(Pos.CENTER);
		bottom.setAlignment(Pos.CENTER);
		left.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);

		top.setPadding(new Insets(20));
		bottom.setPadding(new Insets(20));
		left.setPadding(new Insets(20));
		right.setPadding(new Insets(20));

		top.setSpacing(20);
		bottom.setSpacing(20);
		left.setSpacing(40);
		right.setSpacing(40);

		top.getChildren().addAll(textField, textFieldButton);
		bottom.getChildren().addAll(controlUp, controlDown, controlLeft, controlRight);
		left.getChildren().addAll(option0, option1, option2);
		right.getChildren().addAll(styleBold, styleItalic);
		center.getChildren().add(text);

		for (Node btn : bottom.getChildren()) {

		}
		root.setTop(top);
		root.setBottom(bottom);
		root.setLeft(left);
		root.setRight(right);
		root.setCenter(center);
		root.setMinSize(800, 600);

		primaryStage.setTitle("Hello World");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(800);
		primaryStage.show();
	}
}
