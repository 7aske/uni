package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Blog user or author
 */
@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class User extends Auditable implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "user_id")
	private Integer id;
	@Column(name = "username")
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "about")
	private String about;
	@Column(name = "display_name")
	private String displayName;
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_fk"), inverseJoinColumns = @JoinColumn(name = "role_fk"))
	private List<Role> roles;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return getRecordStatus() == 1;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return getRecordStatus() == 1;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return getRecordStatus() == 1;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return getRecordStatus() == 1;
	}
}