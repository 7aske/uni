package E01;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class E01 extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        Shape movable = null;// = new Circle(250, 250, 25, Color.MEDIUMPURPLE);
        Path path = new Path();
        path.setStroke(Color.BLACK);
        Button btnPlay = new Button("PLAY");
        Button btnPause = new Button("PAUSE");
        Button btnStop = new Button("STOP");

        ColorPicker colorPicker = new ColorPicker();
        Slider slider = new Slider();
        slider.setMax(10);
        slider.setMin(1);
//        slider.setMajorTickUnit(1);
//        slider.setMinorTickCount(10);
        ComboBox<String> shapePicker = new ComboBox<String>(FXCollections.observableArrayList("Rectangle", "Circle"));

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);

        FlowPane controls = new FlowPane(btnPlay, btnPause, btnStop);
        FlowPane colorControls = new FlowPane(colorPicker, slider, shapePicker);

        Pane drawing = new Pane();
        PathTransition pt = new PathTransition(Duration.millis(5000), null, null);
        pt.setPath(path);
        pt.setNode(movable);

        drawing.getChildren().add(path);
        drawing.setOnMousePressed(e -> path.getElements().add(new MoveTo(e.getX(), e.getY())));
        drawing.setOnMouseDragged(e -> path.getElements().add(new LineTo(e.getX(), e.getY())));
        scene.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ESCAPE"))
                path.getElements().clear();
        });
        controls.setAlignment(Pos.CENTER);
        controls.setVgap(20);
        controls.setHgap(20);
        controls.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        colorControls.setAlignment(Pos.CENTER);
        colorControls.setVgap(20);
        colorControls.setHgap(20);

        colorPicker.setOnAction(e -> {
            if (e.getEventType().toString().equals("ACTION")) {
                movable.setFill(colorPicker.getValue());
            }
        });
        slider.setOnMouseDragged(e -> {
            path.setStrokeWidth(slider.getValue());
        });

        drawing.setMinSize(500, 500);
        btnPlay.setOnMouseClicked(e -> {
            if (!drawing.getChildren().contains(movable)) {
                drawing.getChildren().add(movable);
            }
            pt.play();
        });
        btnPause.setOnMouseClicked(e -> pt.pause());
        btnStop.setOnMouseClicked(e -> {
            if (drawing.getChildren().contains(movable)) {
                drawing.getChildren().remove(movable);
            }
            pt.stop();
        });
        shapePicker.setOnAction(e -> {
            if (e.getEventType().toString().equals("ACTION")) {
                switch (shapePicker.getValue()) {
                    case "Rectangle":
                        movable = new Rectangle(250, 250, 50, 50);
                        movable.setFill(Color.PURPLE);
                        break;
                    case "Circle":
                        movable = new Circle(250, 250, 25, Color.MEDIUMPURPLE);
                        break;
                }
            }
        });


        root.setCenter(drawing);
        root.setBottom(controls);
        root.setTop(colorControls);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
