package client.scenes;

import client.Client;
import client.components.ComposeComponent;
import client.components.MessagesComponent;
import client.components.SidebarComponent;
import client.components.TopControls;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import server.user.User;

import java.util.Optional;


public class HomeScene extends Scene {
	public TopControls top;
	public SidebarComponent left;
	public ComposeComponent compose;
	public MessagesComponent messages;

	public HomeScene(Parent root, double width, double height) {
		super(root, width, height);
		//COMPOSE START
		this.compose = new ComposeComponent();
		//COMPOSE END

		//MESSAGES START
		this.messages = new MessagesComponent();

		//MESSAGES END

		// SIDEBAR START
		this.left = new SidebarComponent();
		this.left.onClick(e -> {
			User user = this.left.getSelectedItem();
			if (user != null) {
				this.compose.tfTo.setText(user.getUsername());
				this.compose.tfSubject.setText("Message");
				this.compose.taMessage.setText(String.format("Dear %s,\n", user.getFirstName()));
				this.compose.checkUsername();
				this.messages.updateComponentSentFrom(user.getUsername());
			}
		});

		//SIDEBAR END

		//MESSAGES START
		this.messages = new MessagesComponent();
		this.messages.onClick(e -> {
			if (this.messages.getSelectedItem() != null)
				this.messages.taMessage.setText(this.messages.getSelectedItem().toPrettyString());
		});
		//MESSAGES END

		// TOP START
		this.top = new TopControls();
		this.top.btnCompose.setOnMouseClicked(e -> {
			((BorderPane) this.getRoot()).setCenter(this.compose);
		});
		this.top.btnMessages.setOnMouseClicked(e -> {
			((BorderPane) this.getRoot()).setCenter(this.messages);
		});
		this.top.btnRefresh.setOnMouseClicked(e -> {
			this.left.search.setText("");
			this.left.updateComponent(null);
			this.messages.updateComponent();
		});
		this.top.btnLogout.setOnMouseClicked(e -> {
			Optional<ButtonType> response = Client.showMessage("Warning", "Logout", "Do you really want to logout?", Alert.AlertType.CONFIRMATION);
			if (response.isPresent() && response.get() == ButtonType.OK) {
				Client.state.setToken("");
				Client.state.setUsername("");
				Client.renderLogin(Client.stage);
			}
		});
		// TOP END

		this.left.onKeyPress(e -> {
			this.messages.updateComponent();
			this.compose.reset();
			this.left.clearSelection();
		});
		((BorderPane) this.getRoot()).setLeft(this.left);
		((BorderPane) this.getRoot()).setCenter(this.compose);
		((BorderPane) this.getRoot()).setTop(this.top);
		this.compose.tfTo.requestFocus();
	}
}

