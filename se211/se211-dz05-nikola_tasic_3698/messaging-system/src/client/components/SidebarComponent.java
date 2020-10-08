package client.components;


import client.Client;
import http.Request;
import http.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import server.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class SidebarComponent extends FlowPane {
	public UsersView usersView;
	public TextField search;
	public Label title;

	public SidebarComponent() {
		this.setAlignment(Pos.CENTER);
		this.usersView = new UsersView(FXCollections.observableArrayList(Client.state.getContacts()));
		this.usersView.setMinHeight(Client.state.getHeight() - 30);

		this.title = new Label("Users");
		title.setFont(new Font(24));
		this.title.setPadding(new Insets(5));
		this.search = new TextField();
		this.search.setPromptText("Search");
		this.search.setMinWidth(250);
		this.search.setMinHeight(30);
		this.search.setOnKeyReleased(e -> {
			try {
				this.usersView.updateComponent(this.search.getText());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		this.setMinWidth(250);
		this.setMaxWidth(250);

		this.getChildren().addAll(this.title, this.search, this.usersView);
	}
	public void onKeyPress(EventHandler<KeyEvent> fn){
		this.usersView.setOnKeyPressed(fn);
	}
	public void clearSelection(){
		this.usersView.getSelectionModel().clearSelection();
	}
	public void updateComponent(String query) {
		try {
			this.usersView.updateComponent(query);
		} catch (IOException ex) {
			if (ex.getLocalizedMessage().equals("Connection refused (Connection refused)")) {
				Client.showMessage("Error", "Connection error", "Unable to connect to the server", Alert.AlertType.ERROR);
			} else {
				ex.printStackTrace();
			}
		}
	}

	public void onClick(EventHandler<MouseEvent> fn) {
		this.usersView.setOnMouseClicked(fn);
	}

	public User getSelectedItem() {
		return (User) this.usersView.getSelectionModel().getSelectedItem();
	}

	static class UsersView extends ListView {
		public UsersView(ObservableList<User> items) {
			super(items);
			this.setCellFactory(param -> new ListCell<User>() {
				@Override
				protected void updateItem(User item, boolean empty) {
					super.updateItem(item, empty);

					if (empty || item == null || item.getUsername() == null) {
						setText(null);
					} else {
					// setText(item.getFirstName() + " " + item.getLastName());
						setText(item.getUsername());
					}
				}
			});
			try {
				this.updateComponent(null);
			} catch (IOException ex) {
				if (ex.getLocalizedMessage().equals("Connection refused (Connection refused)")) {
					Client.showMessage("Error", "Connection error", "Unable to connect to the server", Alert.AlertType.ERROR);
				} else {
					ex.printStackTrace();
				}
			}
		}

		public void updateComponent(String query) throws IOException {
			String queryString = String.format("username=%s&firstName=%s&lastName=%s&company=%s", query, query, query, query);
			if (query == null || query.equals("")) {
				queryString = "";
			}
			Request request = Request.generateRequest();
			System.out.println(queryString);
			request.setPath("/api/users?" + queryString);
			request.setMethod("GET");
			request.setHeader("User", Client.state.getUsername());
			request.setHeader("Token", Client.state.getToken());
			Response resp = request.send(Client.state.getServer(), Client.state.getPort());
			System.out.println(resp.toString());
			if (resp.getStatusCode() == 200) {
				ArrayList<User> newContacts = new ArrayList<>();
				String[] userStrings = resp.getBody().split(Request.CLRF);

				for (String u : userStrings) {
					User newuser = User.fromForm(u);
					if (newuser.getUsername() != null && !newuser.getUsername().equals(Client.state.getUsername()))
						newContacts.add(newuser);
				}

				newContacts.sort(Comparator.comparing(User::getUsername));

				Client.state.setContacts(newContacts);
				this.setItems(FXCollections.observableArrayList(Client.state.getContacts()));
				System.out.println(Client.state.toString());
			} else {
				Client.showMessage("Error", "Error", "Error fetching data from server", Alert.AlertType.ERROR);
			}
		}

	}


}
