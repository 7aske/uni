package E00;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class E00 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root);
        Circle circle = new Circle(200, Color.PURPLE);
        Rectangle rect = new Rectangle(50, 50, Color.PURPLE);

        rect.setFill(Color.YELLOW);

        FadeTransition fadeAnim = new FadeTransition(Duration.millis(5000), circle);
        fadeAnim.setCycleCount(-1);
        fadeAnim.setFromValue(0);
        fadeAnim.setToValue(1);
        fadeAnim.setAutoReverse(true);

        PathTransition pathAnim = new PathTransition(Duration.millis(5000), circle, rect);
        pathAnim.setCycleCount(-1);
        pathAnim.setAutoReverse(false);
        pathAnim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        circle.centerXProperty().bind(root.widthProperty().divide(2));
        circle.centerYProperty().bind(root.heightProperty().divide(2));
        root.setMinSize(600, 600);

        root.getChildren().addAll(circle, rect);

        fadeAnim.play();
        pathAnim.play();

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
