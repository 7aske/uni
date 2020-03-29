package client.components;

import client.Client;
import http.Request;
import http.Response;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import server.message.Message;
import server.user.User;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

public class ComposeComponent extends VBox {
	public Label lblTo;
	public Label lblSubject;
	public Label lblMessage;
	public TextField tfTo;
	public TextField tfSubject;
	public TextArea taMessage;

	public Button btnSend;
	public Button btnReset;

	public ComposeComponent() {

		this.setMinWidth(550);
		this.setPadding(new Insets(5, 20, 20, 20));
		this.setAlignment(Pos.TOP_LEFT);
		this.setSpacing(10);

		this.lblTo = new Label("To:");
		this.lblSubject = new Label("Subject:");
		this.lblMessage = new Label("Message:");

		this.tfTo = new TextField();
		this.tfSubject = new TextField();
		this.taMessage = new TextArea();

		this.tfTo.setOnKeyReleased(e -> checkUsername());


		this.btnSend = new Button("Send");
		this.btnReset = new Button("Reset");
		this.btnSend.setMaxWidth(75);
		this.btnReset.setMaxWidth(75);
		this.btnReset.setOnMouseClicked(e -> this.reset());
		this.btnSend.setOnMouseClicked(e -> {
			Optional<ButtonType> response = Client.showMessage("Warning", "Sending message", "Are you sure you want to send this message to'" + this.tfTo.getText() + "'?", Alert.AlertType.CONFIRMATION);
			if (response.isPresent() && response.get() == ButtonType.OK) {
				Message msg = new Message(this.tfTo.getText(), Client.state.getUsername(), this.taMessage.getText(), Instant.now().getEpochSecond());
				Request request = Request.generateRequest("POST", "/messages");
				request.setHeader("User", Client.state.getUsername());
				request.setHeader("Token", Client.state.getToken());
				request.setHeader("Content-Type", "application/x-www-form-urlencoded");
				request.setBody(msg.asRequestString());
				try {
					Response resp = request.send(Client.state.getServer(), Client.state.getPort());
					System.out.println(resp.toString());
					if (resp.getStatusCode() == 201) {
						Client.showMessage("Success", "Sending success", "Successfully sent message to " + this.tfTo.getText(), Alert.AlertType.INFORMATION);
					} else {
						Client.showMessage("Error", "Message error", "Invalid message or recipient", Alert.AlertType.ERROR);
					}
				} catch (IOException ex) {
					Client.showMessage("Error", "Connection error", "Error connecting to server", Alert.AlertType.ERROR);
				}
			}
		});

		this.getChildren().addAll(this.lblTo, this.tfTo, this.lblSubject, this.tfSubject, this.lblMessage, this.taMessage, this.btnSend, this.btnReset);

	}
	public void reset(){
		this.tfTo.setText("");
		this.tfSubject.setText("");
		this.taMessage.setText("");
	}
	public void checkUsername(){
		String text = this.tfTo.getText();
		ArrayList<User> contacts = Client.state.getContacts();
		boolean contains = false;
		for (User u : contacts) {
			if (u.getUsername().equals(text)) {
				contains = true;
				break;
			}
		}
		if (!contains){
			this.tfTo.setStyle("-fx-text-fill: red");
		} else {
			this.tfTo.setStyle(null);
		}
	}
}