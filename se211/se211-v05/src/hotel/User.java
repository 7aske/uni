package hotel;

public class User extends Person {
	private String username;
	private String password;

	public User(String firstName, String lastName, String jmbg, String username, String password) {
		super(firstName, lastName, jmbg);
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				super.toString() +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
