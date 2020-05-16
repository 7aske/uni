package rs.ac.metropolitan.cs230.entity;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	public Long idUser;

	@Column(name = "email")
	public String email;
	@Column(name = "username")
	public String username;
	@Column(name = "display_name")
	public String displayName;
	@Column(name = "password")
	public String password;
	@Column(name = "active")
	public boolean active;

	public User() {}
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}