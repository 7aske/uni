package zadatak08;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Zadatak08 extends Application {
	private Pane root = new Pane();
	private Scene scene = new Scene(root);
	private Ellipse sunOrbit = new Ellipse(300, 250 , 250,150);
	private Ellipse moonOrbit = new Ellipse(300, 200,40,20);
	private Circle sun = new Circle(25);
	private Circle earth = new Circle(300, 250, 100);
	private Circle moon = new Circle(20);

	private Image sunImg = new Image("file://" + System.getProperty("user.dir") + "/sun.png");
	private Image earthImg = new Image("file://" + System.getProperty("user.dir") + "/earth.png");
	private Image moonImg = new Image("file://" + System.getProperty("user.dir") + "/moon.png");
	private Image bgImg = new Image("file://" + System.getProperty("user.dir") + "/bg_stars.jpg");

	@Override
	public void start(Stage primaryStage) {
		root.setMinSize(600, 500);

		sun.setFill(new ImagePattern(sunImg));
		earth.setFill(new ImagePattern(earthImg));
		moon.setFill(new ImagePattern(moonImg));
		scene.setFill(new ImagePattern(bgImg));

		moonOrbit.setStroke(Color.WHITESMOKE);
		moonOrbit.setFill(Color.TRANSPARENT);

		sunOrbit.setStroke(Color.WHITESMOKE);
		sunOrbit.setFill(Color.TRANSPARENT);

		root.getChildren().addAll(sunOrbit, moonOrbit, sun, moon, earth);

		PathTransition sunPt = new PathTransition(Duration.seconds(10), sunOrbit, sun);
		sunPt.setCycleCount(-1);
		sunPt.setInterpolator(Interpolator.LINEAR);
		sunPt.play();

		PathTransition moonPt = new PathTransition(Duration.seconds(3), moonOrbit, moon);
		moonPt.setCycleCount(-1);
		moonPt.setInterpolator(Interpolator.LINEAR);
		moonPt.play();

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
