package E01;


import java.lang.Math;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;

public class E01 extends Application {
    private ArrayList<Entity> entities = new ArrayList<>();
    private RenderThread renderThread;

    @Override
    public void start(Stage primaryStage) {
        this.renderThread = new RenderThread("Render thread", entities);
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        for (int i = 0; i < 6; i++) {
            addEntity(root);
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("My FX App");
        primaryStage.show();
        for (Node e : this.entities) {
            double x = Math.random() * (root.getWidth() - 200) + 100;
            double y = Math.random() * (root.getHeight() - 200) + 100;
            e.relocate(x, y);
        }
        this.renderThread.start();

    }

    private static Color randomColor() {
        return Color.web(randomHexColor());
    }

    private static String randomHexColor() {
        String chars = "1234567890abcdef";
        StringBuilder out = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            out.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return out.toString();
    }

    private void addEntity(Pane root) {
        Entity polygon = new Entity();
        entities.add(polygon);
        root.getChildren().add(polygon);

    }
}

class Entity extends Polygon {
    private Vector vec = new Vector();

    Entity() {
        this.vec = this.vec.normalize();
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
        ObservableList<Double> list = this.getPoints();
        final double size = Math.random() * 100 + 100;
        double centerX = size / 2, centerY = size / 2;
        double radius = size * 0.4;
        for (int i = 0; i < 6; i++) {
            list.add(centerX + radius * Math.cos(2 * i * Math.PI / 6));
            list.add(centerY - radius * Math.sin(2 * i * Math.PI / 6));
        }
    }

    void draw() {
        System.out.printf("%f %f\n", vec.dX, vec.dY);
        this.relocate(this.getLayoutX() + vec.dX, this.getLayoutY() + vec.dY);
    }
}

class RenderThread implements Runnable {
    private String threadName;
    private ArrayList<Entity> entities;
    private Thread t;

    RenderThread(String name, ArrayList<Entity> e) {
        this.threadName = name;
        this.entities = e;
    }

    void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    @Override
    public void run() {
        for (; ; ) {
            for (Entity entity : this.entities) {
                entity.draw();
            }
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException ignored) {

            }
        }
    }
}

class Vector {
    double dX;
    double dY;

    Vector() {
        this.dX = Math.random() * 2 - 1;
        this.dY = Math.random() * 2 - 1;
    }

    private Vector(double dX, double dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public String toString() {
        return "Vector(" + dX + ", " + dY + ")";
    }

    public double length() {
        return Math.sqrt(dX * dX + dY * dY);
    }

    public Vector add(Vector v1) {
        Vector v2 = new Vector(this.dX + v1.dX, this.dY + v1.dY);
        return v2;
    }

    public Vector sub(Vector v1) {
        Vector v2 = new Vector(this.dX - v1.dX, this.dY - v1.dY);
        return v2;
    }

    public Vector scale(double scaleFactor) {
        Vector v2 = new Vector(this.dX * scaleFactor, this.dY * scaleFactor);
        return v2;
    }

    Vector normalize() {
        Vector v2 = new Vector();
        double length = Math.sqrt(this.dX * this.dX + this.dY * this.dY);
        if (length != 0) {
            v2.dX = this.dX / length;
            v2.dY = this.dY / length;
        }
        return v2;
    }

    public double dotProduct(Vector v1) {
        return this.dX * v1.dX + this.dY * v1.dY;
    }
}