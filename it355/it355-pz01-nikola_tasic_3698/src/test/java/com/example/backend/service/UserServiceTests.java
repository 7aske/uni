package com.example.backend.service;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.backend.util.TestUtils.getRole;
import static com.example.backend.util.TestUtils.getUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@AfterEach
	void teardown() {
		userRepository.deleteAll();
		roleRepository.deleteAll();
	}

	@Test
	void test_saveUser() {
		User user = getUser();
		User newUser = userService.save(user);

		assertEquals(user, newUser);
	}

	@Test
	void test_userHasRole() {
		User user = getUser();
		Role role = getRole();
		role = roleService.save(role);
		user.setRoles(Collections.singletonList(role));
		user = userService.save(user);

		assertEquals(role, user.getRoles().get(0));
	}

	@Test
	void test_findRolesById() {
		User user = getUser();
		Role role = getRole();
		role = roleRepository.save(role);
		user.setRoles(Collections.singletonList(role));
		user = userService.save(user);
		List<Role> roles = userService.findAllRolesById(user.getId());

		assertEquals(role, roles.get(0));
	}

	@Test
	void test_findByUsername() {
		User user = getUser();
		user = userService.save(user);
		User foundUser = userService.findByUsername(user.getUsername());

		assertEquals(user.getUsername(), foundUser.getUsername());
		assertEquals(user, foundUser);
	}

	@Test
	void test_findByUsername_shouldThrowWhenNoUserFound() {
		assertThrows(NoSuchElementException.class,
				() -> userService.findByUsername("john_malkovich"));
	}

	@Test
	void test_update(){
		User user = getUser();
		user = userService.save(user);
		String expectedUsername = "newUsername";
		user.setUsername(expectedUsername);
		user = userService.update(user);

		assertEquals(expectedUsername, user.getUsername());
	}
}
