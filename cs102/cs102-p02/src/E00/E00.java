package E00;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

public class E00 extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        HBox content = new HBox();
        HBox controls = new HBox();
        Scene scene = new Scene(root);
        Circle circle = new Circle(100);
        circle.setFill(Color.web(randomHexColor()));
        Button btn0 = new Button("+");
        Button btn1 = new Button("-");

        btn0.setMinWidth(75);
        btn1.setMinWidth(75);
        btn0.setMinHeight(25);
        btn1.setMinHeight(25);

        controls.setSpacing(30);

        controls.getChildren().add(btn0);
        controls.getChildren().add(btn1);

        content.getChildren().add(circle);

        controls.setPadding(new Insets(10));
        content.setPadding(new Insets(10));

        controls.setAlignment(Pos.BOTTOM_CENTER);
        content.setAlignment(Pos.CENTER);

        root.setCenter(content);
        root.setBottom(controls);

        btn0.setOnAction(event -> circle.setRadius(circle.getRadius() + 5));
        btn1.setOnAction(event -> circle.setRadius(circle.getRadius() - 5));
        btn0.setOnKeyPressed(e -> {
            switch (e.getCode().toString()) {
                case "ADD":
                    circle.setRadius(circle.getRadius() - - 5);
                    break;
                case "SUBTRACT":
                    circle.setRadius(circle.getRadius() - 5);
                    break;
            }
            circle.setFill(Color.web(randomHexColor()));
        });
        circle.setOnMouseClicked(e -> circle.setFill(Color.web(randomHexColor())));

        root.setMinSize(500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String randomHexColor() {
        return String.format("#%06x", new Random().nextInt(0xffffff + 1));
    }
}
