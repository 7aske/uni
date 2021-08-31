package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * User permissions
 */
@Data
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Role extends Auditable implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "role_id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<User> users;

	@Override
	@JsonIgnore
	public String getAuthority() {
		return String.format("ROLE_%s", name);
	}
}