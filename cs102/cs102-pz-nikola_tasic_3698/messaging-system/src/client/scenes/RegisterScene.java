package client.scenes;

import client.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


public class RegisterScene extends Scene {
	public HBox serverPicker;
	public FlowPane top;
	public GridPane center;

	public Label lblTitle;

	public Label lblUsername;
	public Label lblPassword;
	public Label lblPasswordCnf;
	public Label lblEmail;
	public Label lblFirstName;
	public Label lblLastName;
	public Label lblPhone;
	public Label lblCompany;

	public TextField tfUsername;
	public PasswordField tfPassword;
	public PasswordField tfPasswordCnf;
	public TextField tfEmail;
	public TextField tfFirstName;
	public TextField tfLastName;
	public TextField tfPhone;
	public TextField tfCompany;

	public Button btnRegister;
	public Button btnBack;

	public Label lblAddress;
	public Label lblPort;
	public TextField tfAddress;
	public TextField tfPort;

	public RegisterScene(Parent root, double width, double height) {
		super(root, width, height);
		//TITLE BEGIN
		this.top = new FlowPane();
		this.top.setAlignment(Pos.CENTER);
		this.lblTitle = new Label("Client Register");
		this.lblTitle.setFont(new Font(44));

		this.top.getChildren().add(lblTitle);

		//TITLE END

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

		this.serverPicker.getChildren().addAll(lblAddress, tfAddress, lblPort, tfPort);

		//SERVER PICKER END

		// LOGIN FORM START
		this.center = new GridPane();
		// this.center.setMinWidth(400);
		// this.center.setMaxWidth(400);
		this.center.setPadding(new Insets(20, 130, 50, 270));

		this.center.setHgap(30);
		this.center.setVgap(15);

		this.lblUsername = new Label("Username");
		this.lblEmail = new Label("Email");
		this.lblFirstName = new Label("First Name");
		this.lblLastName = new Label("Last Name");
		this.lblPassword = new Label("Password");
		this.lblPasswordCnf = new Label("Confirm");
		this.lblPhone = new Label("Phone");
		this.lblCompany = new Label("Company");
		this.tfUsername = new TextField();
		this.tfEmail = new TextField();
		this.tfFirstName = new TextField();
		this.tfLastName = new TextField();
		this.tfPassword = new PasswordField();
		this.tfPasswordCnf = new PasswordField();
		this.tfPhone = new TextField();
		this.tfCompany = new TextField();
		this.btnRegister = new Button("Register");
		this.btnBack = new Button("Back");

		this.lblUsername.setMaxWidth(200);
		this.lblEmail.setMaxWidth(200);
		this.lblFirstName.setMaxWidth(200);
		this.lblLastName.setMaxWidth(200);
		this.lblPassword.setMaxWidth(200);
		this.lblPasswordCnf.setMaxWidth(200);
		this.lblPhone.setMaxWidth(200);
		this.lblCompany.setMaxWidth(200);

		this.tfUsername.setMaxWidth(200);
		this.tfEmail.setMaxWidth(200);
		this.tfFirstName.setMaxWidth(200);
		this.tfLastName.setMaxWidth(200);
		this.tfPassword.setMaxWidth(200);
		this.tfPasswordCnf.setMaxWidth(200);
		this.tfPhone.setMaxWidth(200);
		this.tfCompany.setMaxWidth(200);

		this.btnRegister.setMinWidth(75);
		this.btnBack.setMinWidth(75);

		this.center.add(this.lblUsername, 0, 0);
		this.center.add(this.lblEmail, 0, 1);
		this.center.add(this.lblFirstName, 0, 2);
		this.center.add(this.lblLastName, 0, 3);
		this.center.add(this.lblPassword, 0, 4);
		this.center.add(this.lblPasswordCnf, 0, 5);
		this.center.add(this.lblPhone, 0, 6);
		this.center.add(this.lblCompany, 0, 7);
		this.center.add(this.tfUsername, 1, 0);
		this.center.add(this.tfEmail, 1, 1);
		this.center.add(this.tfFirstName, 1, 2);
		this.center.add(this.tfLastName, 1, 3);
		this.center.add(this.tfPassword, 1, 4);
		this.center.add(this.tfPasswordCnf, 1, 5);
		this.center.add(this.tfPhone, 1, 6);
		this.center.add(this.tfCompany, 1, 7);
		this.center.add(this.btnRegister, 0, 8, 2, 1);
		this.center.add(this.btnBack, 0, 9, 2, 1);

		// LOGIN FORM END

		((BorderPane) this.getRoot()).setTop(top);
		((BorderPane) this.getRoot()).setCenter(center);
		((BorderPane) this.getRoot()).setBottom(serverPicker);

	}

	public boolean verifyForm() {
		System.out.println(this.tfUsername.getText());
		System.out.println(this.tfEmail.getText());
		System.out.println(this.tfFirstName.getText());
		System.out.println(this.tfLastName.getText());
		System.out.println(this.tfPassword.getText());
		System.out.println(this.tfPasswordCnf.getText());
		System.out.println(this.tfPhone.getText());
		System.out.println(this.tfCompany.getText());
		return !this.tfUsername.getText().equals("") &&
				!this.tfEmail.getText().equals("") &&
				!this.tfFirstName.getText().equals("") &&
				!this.tfLastName.getText().equals("") &&
				!this.tfPassword.getText().equals("") &&
				!this.tfPasswordCnf.getText().equals("") &&
				!this.tfPhone.getText().equals("") &&
				!this.tfCompany.getText().equals("") &&
				this.tfPassword.getText().equals(this.tfPasswordCnf.getText());

	}

	public String getFormData() {
		String formTemplate = "username=%s&" +
				"email=%s&" +
				"firstName=%s&" +
				"lastName=%s&" +
				"password=%s&" +
				"phone=%s&" +
				"company=%s";

		return String.format(formTemplate,
				this.tfUsername.getText(),
				this.tfEmail.getText(),
				this.tfFirstName.getText(),
				this.tfLastName.getText(),
				this.tfPassword.getText(),
				this.tfPhone.getText(),
				this.tfCompany.getText()
		);

	}
}
