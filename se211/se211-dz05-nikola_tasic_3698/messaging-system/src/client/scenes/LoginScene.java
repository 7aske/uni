package client.scenes;


import client.Client;
import com.sun.org.apache.bcel.internal.generic.LADD;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class LoginScene extends Scene {
	public VBox login;
	public HBox serverPicker;
	public FlowPane top;

	public TextField tfUsername;
	public PasswordField tfPassword;
	public TextField tfAddress;
	public TextField tfPort;

	public Label lblTitle;
	public Label lblUsername;
	public Label lblPassword;
	public Label lblAddress;
	public Label lblPort;

	public Button btnLogin;
	public Label lblRegister;
	public Button btnRegister;


	public LoginScene(Parent root) {
		super(root);
	}

	public LoginScene(Parent root, double width, double height) {
		super(root, width, height);
		// TITLE START
		this.top = new FlowPane();
		this.top.setAlignment(Pos.CENTER);

		this.lblTitle = new Label("Client Login");
		this.lblTitle.setFont(new Font(44));


		this.top.getChildren().add(lblTitle);

		// TITLE END

		//SERVER PICKER START

		this.serverPicker = new HBox();

		this.serverPicker.setPadding(new Insets(5));

		this.lblAddress = new Label("IP Address: ");
		this.lblPort = new Label("Port: ");


		this.lblAddress.setPadding(new Insets(5));

		this.lblPort.setPadding(new Insets(5));

		this.tfAddress = new TextField();
		this.tfPort = new TextField();

		this.tfAddress.setMaxWidth(100);
		this.tfAddress.setMinWidth(100);

		this.tfPort.setMaxWidth(100);
		this.tfPort.setMinWidth(100);
		this.tfAddress.setText("127.0.0.1");
		this.tfPort.setText("8000");

		Client.state.setServer(this.tfAddress.getText());
		Client.state.setPort(Integer.parseInt(this.tfPort.getText()));

		this.serverPicker.getChildren().addAll(lblAddress, tfAddress, lblPort, tfPort);

		//SERVER PICKER END

		// LOGIN START
		this.login = new VBox();

		this.login.setPadding(new Insets(100, 300, 50, 300));
		this.login.setSpacing(10);

		this.lblUsername = new Label("Username");
		this.lblPassword = new Label("Password");

		this.tfUsername = new TextField();
		this.tfPassword = new PasswordField();


		this.btnLogin = new Button("Login");
		this.lblRegister = new Label("Not a member?");
		this.btnRegister = new Button("Register");

		this.login.getChildren().addAll(this.lblUsername, this.tfUsername, this.lblPassword, this.tfPassword, this.btnLogin, this.lblRegister, this.btnRegister);
		//LOGIN END


		((BorderPane) this.getRoot()).setTop(top);
		((BorderPane) this.getRoot()).setCenter(login);
		((BorderPane) this.getRoot()).setBottom(serverPicker);

	}
}
