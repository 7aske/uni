package com.example.backend.service;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;

import java.util.List;

public interface RoleService {

	List<Role> findAll();

	Role save(Role role);

	Role update(Role role);

	Role findById(Integer roleId);

	void deleteById(Integer roleId);

	List<User> findAllUsersById(Integer roleId);

}