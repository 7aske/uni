package client.state;

import server.message.Message;
import server.user.User;

import java.util.ArrayList;

public class State {
	private String username;
	private String token;
	private int height = 600;
	private int width = 800;
	private ArrayList<User> contacts;
	private ArrayList<Message> messages;
	private String server;
	private int port;

	public State() {
		this.contacts = new ArrayList<>();
		this.messages = new ArrayList<>();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public ArrayList<User> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<User> contacts) {
		this.contacts = contacts;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "State{" +
				"\nusername='" + username + '\'' +
				", \ntoken='" + token + '\'' +
				", \nheight=" + height +
				", \nwidth=" + width +
				", \nserver='" + server + '\'' +
				", \nport=" + port +
				", \nmessages=" + messages +
				", \nmessages-size='" + messages.size() + '\'' +
				", \ncontacts=" + contacts +
				", \ncontacts-size='" + contacts.size() + '\'' +
				'}';
	}
}
