package rs.ac.metropolitan.cs230;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
public class User implements Serializable {

	private String username;
	private String password;

	public User() {
	}

	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9._\\-]{3,}");
		Matcher matcher = pattern.matcher(username);
		if (!matcher.matches()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Username not valid", null);
			throw new ValidatorException(facesMessage);
		} else {
			this.username = username;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9._\\-]{3,}");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Password not valid", null);
			throw new ValidatorException(facesMessage);
		} else {
			this.password = password;
		}
	}

	@Override
	public String toString() {
		return "User{" + "username=" + username + ", password=" + password + '}';
	}
}
