package com.example.backend.repository;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.example.backend.util.TestUtils.getRole;
import static com.example.backend.util.TestUtils.getUser;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTests {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@AfterEach
	void teardown() {
		userRepository.deleteAll();
		roleRepository.deleteAll();
	}

	@Test
	void test_saveUser() {
		User user = getUser();
		userRepository.save(user);

		assertEquals(1, userRepository.count());
	}

	@Test
	void test_saveRole() {
		Role role = getRole();
		roleRepository.save(role);

		assertEquals(1, roleRepository.count());
	}

	@Test
	void test_userHasRole() {
		Role role = getRole();
		User user = getUser();
		roleRepository.save(role);
		userRepository.save(user);

		user.setRoles(Collections.singletonList(role));
		user = userRepository.save(user);

		assertEquals(role, user.getRoles().get(0));
	}

	@Test
	void test_userAuditable(){
		User user = getUser();
		user = userRepository.save(user);

		LocalDateTime savedTimestamp = user.getLastModifiedDate();
		user.setFirstName("Some other first name");
		user = userRepository.save(user);
		LocalDateTime updatedTimestamp = user.getLastModifiedDate();

		assertTrue(savedTimestamp.isBefore(updatedTimestamp));
	}
}
