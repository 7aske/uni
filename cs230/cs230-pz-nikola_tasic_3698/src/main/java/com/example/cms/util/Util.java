package com.example.cms.util;

import com.example.cms.database.entity.Role;

public class Util {
	public static boolean hasRole(Iterable<Role> roles, String roleName) {
		for (Role role : roles) {
			if (role.getRoleName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasRole(Iterable<Role> roles, Long idRole) {
		for (Role role : roles) {
			if (role.getIdRole().equals(idRole)) {
				return true;
			}
		}
		return false;
	}
}
