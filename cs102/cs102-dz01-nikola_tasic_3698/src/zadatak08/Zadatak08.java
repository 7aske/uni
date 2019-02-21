package zadatak08;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Zadatak08 extends Application {
    @Override
    public void start(Stage primaryStage) {
        double buttonSize = 50;
        double rowGap = 10;
        double panePadding = 10;

        double screenHeight = 200;
        double screenWidth = 350;

        BorderPane root = new BorderPane();
        GridPane numbers = new GridPane();
        GridPane operations = new GridPane();
        TextField output = new TextField();
        Scene scene = new Scene(root, screenWidth, screenHeight);

        Button[] numberButtonsArray = new Button[]{
                new Button("0"),
                new Button("1"),
                new Button("2"),
                new Button("3"),
                new Button("4"),
                new Button("5"),
                new Button("6"),
                new Button("7"),
                new Button("8"),
                new Button("9"),
                new Button(".")};

        for (Button button : numberButtonsArray) {
            button.setMinWidth(buttonSize);
        }

        numbers.add(numberButtonsArray[0], 0, 3, 2, 1);
        numbers.add(numberButtonsArray[1], 0, 2);
        numbers.add(numberButtonsArray[2], 1, 2);
        numbers.add(numberButtonsArray[3], 2, 2);
        numbers.add(numberButtonsArray[4], 0, 1);
        numbers.add(numberButtonsArray[5], 1, 1);
        numbers.add(numberButtonsArray[6], 2, 1);
        numbers.add(numberButtonsArray[7], 0, 0);
        numbers.add(numberButtonsArray[8], 1, 0);
        numbers.add(numberButtonsArray[9], 2, 0);
        numbers.add(numberButtonsArray[10], 2, 3);
        numberButtonsArray[0].setMinWidth(buttonSize * 2 + rowGap);

        Button btnOp1 = new Button("+");
        Button btnOp2 = new Button("-");
        Button btnOp3 = new Button("*");
        Button btnOp4 = new Button("/");

        Button btnMore1 = new Button("C");
        Button btnMore2 = new Button("√");
        Button btnMore3 = new Button("+/-");
        Button btnMore4 = new Button("=");


        operations.add(btnOp1, 0, 0);
        operations.add(btnOp2, 0, 1);
        operations.add(btnOp3, 0, 2);
        operations.add(btnOp4, 0, 3);

        operations.add(btnMore1, 1, 0);
        operations.add(btnMore2, 1, 1);
        operations.add(btnMore3, 1, 2);
        operations.add(btnMore4, 1, 3);

        for (Node node : operations.getChildren()) {
            if (GridPane.getColumnIndex(node) == 1) {
                ((Button) node).setMinWidth(buttonSize * 1.5);
            } else {
                ((Button) node).setMinWidth(buttonSize);
            }
        }

        numbers.setHgap(rowGap);
        numbers.setVgap(rowGap);

        numbers.setPadding(new Insets(panePadding, 5, panePadding, panePadding));

        operations.setHgap(rowGap);
        operations.setVgap(rowGap);

        operations.setPadding(new Insets(panePadding, panePadding, panePadding, 5));

        root.setCenter(numbers);
        root.setRight(operations);
        root.setTop(output);

        root.setPadding(new Insets(panePadding));

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }
}
