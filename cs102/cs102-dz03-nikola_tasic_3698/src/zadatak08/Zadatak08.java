package zadatak08;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Zadatak08 extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);

		FlowPane top = new FlowPane();
		Pane center = new Pane();

		Slider lineWidth = new Slider();
		ColorPicker colorPicker = new ColorPicker();

		Canvas canvas = new Canvas(600,600);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		lineWidth.setTooltip(new Tooltip("Line width"));
		lineWidth.setMin(1);
		lineWidth.setMax(8);

		colorPicker.setValue(Color.web("#000000"));

		gc.setLineWidth(1);

		top.getChildren().addAll(lineWidth, colorPicker);
		top.setAlignment(Pos.CENTER);
		top.setHgap(20);

		center.setMinSize(600,600);
		center.getChildren().add(canvas);

		root.setTop(top);
		root.setCenter(center);

		lineWidth.setOnMouseReleased(e -> {
			System.out.println(lineWidth.getValue());
			gc.setLineWidth(lineWidth.getValue());
			gc.stroke();
		});

		colorPicker.setOnAction(e -> {
			gc.setStroke(colorPicker.getValue());
			gc.stroke();
		});

		canvas.setOnMousePressed(e -> {
			gc.moveTo(e.getX(), e.getY());

		});
		canvas.setOnMouseDragged(e -> {
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();
		});
		canvas.setOnMouseReleased(e -> {
			gc.moveTo(e.getX(), e.getY());
		});

		primaryStage.setScene(scene);
		primaryStage.setTitle("Drawing");
		primaryStage.show();
	}
}
