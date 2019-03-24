package zadatak08;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class Zadatak08 extends Application {
	private double x = 0;
	private double y = 0;

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root);

		FlowPane top = new FlowPane();
		Pane center = new Pane();

		Slider lineWidth = new Slider();

		Canvas canvas = new Canvas(600, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(Color.web("#000000"));
		colorPicker.setOnAction(e -> {
			gc.setStroke(colorPicker.getValue());
			gc.stroke();
		});
		lineWidth.setTooltip(new Tooltip("Line width"));
		lineWidth.setMin(1);
		lineWidth.setMax(8);

		gc.setLineWidth(1);

		top.getChildren().addAll(lineWidth, colorPicker);
		top.setAlignment(Pos.CENTER);
		top.setHgap(20);

		this.y = canvas.getHeight() / 2;
		this.x = canvas.getWidth() / 2;
		gc.moveTo(x, y);
		lineWidth.setOnMouseReleased(e -> {
			System.out.println(lineWidth.getValue());
			gc.setLineWidth(lineWidth.getValue());
			gc.stroke();
		});

		// meni stvarno treba objasnjenje zasto promenljive u lambda izrazu moraju da budu final
		// toliko toga se komplikuje zbog gluposti koja je onda poenta lambda izraza vise ufff
		canvas.setOnMousePressed(e -> canvas.requestFocus());
		canvas.setOnKeyPressed(e -> {
			e.consume();
			switch (e.getCode()) {
				case LEFT:
					x -= 2;
					break;
				case RIGHT:
					x += 2;
					break;
				case UP:
					y -= 2;
					break;
				case DOWN:
					y += 2;
					break;
			}
			System.out.println(e.getCode());
			gc.moveTo(x, y);
			gc.lineTo(x, y);
			gc.stroke();
		});
		canvas.setOnKeyReleased(e -> {
			System.out.println(e.getCode());
//			gc.moveTo(e.getX(), e.getY());
		});

		center.setMinSize(600, 600);
		center.getChildren().add(canvas);

		root.setTop(top);
		root.setCenter(center);


		primaryStage.setScene(scene);
		primaryStage.setTitle("Drawing");
		primaryStage.show();
		canvas.requestFocus();
	}

}
