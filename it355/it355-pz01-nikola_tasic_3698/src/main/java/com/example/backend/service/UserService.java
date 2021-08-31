package com.example.backend.service;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {

	List<User> findAll();

	List<User> findAll(Specification<User> specification, Pageable pageable);

	Page<User> findAllPage(Specification<User> specification, Pageable pageable);

	User save(User user);

	User update(User user);

	User findById(Integer userId);

	void deleteById(Integer userId);

	List<Role> findAllRolesById(Integer userId);

	User findByUsername(String username);
}