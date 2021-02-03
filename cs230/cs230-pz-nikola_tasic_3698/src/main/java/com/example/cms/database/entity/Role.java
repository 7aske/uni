package com.example.cms.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private Long idRole;

	@Column(name = "role_name")
	private String roleName;

	public Role() {
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Role role = (Role) o;
		return roleName.equals(role.roleName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRole);
	}

	@Override
	public String toString() {
		return "Role{" +
				"idRole=" + idRole +
				", roleName='" + roleName + '\'' +
				'}';
	}
}
