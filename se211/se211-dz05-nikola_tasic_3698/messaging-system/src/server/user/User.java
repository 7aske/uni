package server.user;

import http.Response;
import server.message.Message;
import utils.Encryption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String company;
	private List<String> classes;
	public static final String[] fieldList = {"username", "password", "firstName", "lastName", "email", "phone", "company"};

	private List<Message> pendingMessages = new ArrayList<>();

	private User() {
	}

	public User(int id, String username, String password, String email, String firstName, String lastName, String phone, String company) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.company = company;
	}

	public User(String username, String password, String email, String firstName, String lastName, String phone, String company) {
		this.id = 0;
		this.username = username;
		this.password = Encryption.getSHA1Hash(password);
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.company = company;
	}

	public static User fromForm(HashMap<String, String> form) {
		User user = new User();
		for (Map.Entry<String, String> kv : form.entrySet()) {
			switch (kv.getKey()) {
				case "username":
					user.username = kv.getValue();
					break;
				case "firstName":
					user.firstName = kv.getValue();
					break;
				case "lastName":
					user.lastName = kv.getValue();
					break;
				case "email":
					user.email = kv.getValue().replaceAll("%40", "@");
					break;
				case "phone":
					user.phone = kv.getValue();
					break;
				case "company":
					user.company = kv.getValue();
					break;
				case "password":
					user.password = Encryption.getSHA1Hash(kv.getValue());
					break;
			}
		}
		return user;
	}
	public static User fromForm(String response) {
		HashMap<String, String> form = new HashMap<>();
		String[] pairs = response.split("&");
		for (String pair : pairs) {
			String[] kv = pair.split("=");
			if (kv.length == 2) {
				form.put(kv[0], kv[1]);
			}
		}
		return User.fromForm(form);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}

	public List<Message> getPendingMessages() {
		return pendingMessages;
	}

	public void setPendingMessages(List<Message> pendingMessages) {
		this.pendingMessages = pendingMessages;
	}

	public boolean checkPassword(String passToCheck) {
		String hash = Encryption.getSHA1Hash(passToCheck);
		return hash.equals(this.password);
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				(password == null ? "" : ", password='" + password + '\'') +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", phone='" + phone + '\'' +
				", company='" + company + '\'' +
				'}';
	}

	public String asResponseString() {
		return "username=" + username + "&" +
				"email=" + email + "&" +
				"firstName=" + firstName + "&" +
				"lastName=" + lastName + "&" +
				"phone=" + phone + "&" +
				"company=" + company + Response.CLRF;
	}


}
