package E02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class E02 extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Scene scene = new Scene(root, 400, 600);
        FlowPane bottom = new FlowPane();
        VBox inputs = new VBox();

        root.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));

        Image logo = new Image("file://" + System.getProperty("user.dir") + "/logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(scene.getWidth());
        logoView.setFitHeight(scene.getWidth());

        TextField userInput = new TextField();
        TextField passInput = new TextField();

        Border border = new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
                CornerRadii.EMPTY, new BorderWidths(4), Insets.EMPTY));

        userInput.setStyle("-fx-focus-color: rgba(0,0,0,0);");
        passInput.setStyle("-fx-focus-color: rgba(0,0,0,0);");
        userInput.setBorder(border);
        passInput.setBorder(border);
        userInput.setText("Username");
        userInput.setPromptText("Username");
        passInput.setText("Password");
        passInput.setPromptText("Password");

        userInput.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        passInput.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));


        Button login = new Button("Login");
        login.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
        login.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(4), Insets.EMPTY)));
        bottom.getChildren().add(login);

        login.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        inputs.setSpacing(10);
        inputs.setPadding(new Insets(20));
        bottom.setPadding(new Insets(0, 20, 10, 20));
        bottom.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().add(logoView);
        inputs.getChildren().add(userInput);
        inputs.getChildren().add(passInput);
        root.getChildren().add(inputs);
        root.getChildren().add(bottom);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
