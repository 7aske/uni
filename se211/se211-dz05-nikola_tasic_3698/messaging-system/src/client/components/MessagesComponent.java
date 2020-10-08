package client.components;

import client.Client;
import http.Request;
import http.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import server.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class MessagesComponent extends VBox {
	public MessagesView messageListView;
	public TextArea taMessage;

	public MessagesComponent() {
		this.messageListView = new MessagesView(FXCollections.observableArrayList(Client.state.getMessages()));
		this.taMessage = new TextArea();
		this.taMessage.setEditable(false);
		this.getChildren().addAll(this.messageListView, this.taMessage);
		this.messageListView.setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ESCAPE){
				this.reset();
			}
		});
	}

	public void updateComponent() {
		try {
			this.messageListView.updateComponent(Client.state.getUsername());
		} catch (IOException ex) {
			if (ex.getLocalizedMessage().equals("Connection refused (Connection refused)")) {
				Client.showMessage("Error", "Connection error", "Unable to connect to the server", Alert.AlertType.ERROR);
			} else {
				ex.printStackTrace();
			}
		}
	}
	public void  updateComponentSentFrom(String from){
		this.messageListView.updateComponentSentFrom(from);
	}
	public void reset(){
		this.messageListView.getSelectionModel().clearSelection();
		this.taMessage.setText("");
	}
	public void onClick(EventHandler<MouseEvent> fn) {
		this.messageListView.setOnMouseClicked(fn);
	}

	public Message getSelectedItem() {
		return (Message) this.messageListView.getSelectionModel().getSelectedItem();
	}

	static class MessagesView extends ListView {
		public MessagesView(ObservableList items) {
			super(items);
			this.setCellFactory(param -> new ListCell<Message>() {
				@Override
				protected void updateItem(Message item, boolean empty) {
					super.updateItem(item, empty);

					if (empty || item == null || !item.isValid()) {
						setText(null);
					} else {
						setText(String.format("From: %s - To: %s - %d", item.getSentFrom(), item.getSentTo(), item.getDateSent()));
					}
				}
			});
			try {
				this.updateComponent(Client.state.getUsername());
			} catch (IOException ex) {
				if (ex.getLocalizedMessage().equals("Connection refused (Connection refused)")) {
					Client.showMessage("Error", "Connection error", "Unable to connect to the server", Alert.AlertType.ERROR);
				} else {
					ex.printStackTrace();
				}
			}
		}

		public void updateComponentSentFrom(String from) {
			ArrayList<Message> newMessages = new ArrayList<>();
			for (Message message : Client.state.getMessages()) {
				if ((message.getSentFrom().equals(from) && message.getSentTo().equals(Client.state.getUsername())) ||
						(message.getSentTo().equals(from) && message.getSentFrom().equals(Client.state.getUsername()))) {
					newMessages.add(message);
				}
			}
			newMessages.sort((m0, m1) -> {
				if (m0.getDateSent() > m1.getDateSent()) {
					return 1;
				} else if (m0.getDateSent() < m1.getDateSent()) {
					return -1;
				} else {
					return 0;
				}
			});
			this.setItems(FXCollections.observableArrayList(newMessages));
		}

		public void updateComponent(String query) throws IOException {
			String queryString = String.format("sentTo=%s", query);
			if (query == null) {
				return;
			}
			Request request = Request.generateRequest();
			request.setPath("/api/messages?" + queryString);
			request.setMethod("GET");
			request.setHeader("User", Client.state.getUsername());
			request.setHeader("Token", Client.state.getToken());
			System.out.println(request.toString());
			Response resp = request.send(Client.state.getServer(), Client.state.getPort());
			System.out.println(resp.toString());
			if (resp.getStatusCode() == 200) {
				ArrayList<Message> newMsgs = new ArrayList<>();
				String[] userStrings = resp.getBody().split(Request.CLRF);

				for (String u : userStrings) {
					Message msg = Message.fromForm(u);
					if (msg.isValid())
						newMsgs.add(msg);
				}
				newMsgs.sort((m0, m1) -> {
					if (m0.getDateSent() > m1.getDateSent()) {
						return 1;
					} else if (m0.getDateSent() < m1.getDateSent()) {
						return -1;
					} else {
						return 0;
					}
				});
				Client.state.setMessages(newMsgs);
				this.setItems(FXCollections.observableArrayList(Client.state.getMessages()));
				System.out.println(Client.state.toString());
			} else {
				Client.showMessage("Error", "Error", "Error fetching data from server", Alert.AlertType.ERROR);
			}
		}
	}
}
