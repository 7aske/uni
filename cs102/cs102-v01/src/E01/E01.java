package E01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.util.ArrayList;

public class E01 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        GridPane numbers = new GridPane();
        GridPane operations = new GridPane();
        TextField output = new TextField();

        Button btn0 = new Button("0");
        Button btn1 = new Button("1");
        Button btn2 = new Button("2");
        Button btn3 = new Button("3");
        Button btn4 = new Button("4");
        Button btn5 = new Button("5");
        Button btn6 = new Button("6");
        Button btn7 = new Button("7");
        Button btn8 = new Button("8");
        Button btn9 = new Button("9");
        Button btn10 = new Button(".");
        Button btn11 = new Button("=");

        numbers.add(btn1,0,0);
        numbers.add(btn2,1,0);
        numbers.add(btn3,2,0);
        numbers.add(btn4,0,1);
        numbers.add(btn5,1,1);
        numbers.add(btn6,2,1);
        numbers.add(btn7,0,2);
        numbers.add(btn8,1,2);
        numbers.add(btn9,2,2);
        numbers.add(btn10,0,3);
        numbers.add(btn0,1,3);
        numbers.add(btn11,2,3);

        numbers.setHgap(10);
        numbers.setVgap(10);

        numbers.setPadding(new Insets(10));

        Button btnPlus = new Button("+");
        Button btnMin = new Button("-");
        Button btnMul = new Button("*");
        Button btnDiv = new Button("/");

        operations.add(btnPlus,0,0);
        operations.add(btnMin,0,1);
        operations.add(btnMul,0,2);
        operations.add(btnDiv,0,3);

        operations.setHgap(10);
        operations.setVgap(10);

        operations.setPadding(new Insets(10));

        root.setCenter(numbers);
        root.setRight(operations);
        root.setTop(output);

        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
