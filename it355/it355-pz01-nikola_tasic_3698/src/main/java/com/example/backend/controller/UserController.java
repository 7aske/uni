package com.example.backend.controller;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping
	public ResponseEntity<Page<User>> getAll(
			@RequestParam(required = false, name = "q") Specification<User> query,
			@RequestParam(required = false, name = "page", defaultValue = "0,10") Pageable pageable
	) {
		return ResponseEntity.ok(userService.findAllPage(query, pageable));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getById(@PathVariable String userId) {
		try {
			return ResponseEntity.ok(userService.findById(Integer.parseInt(userId)));
		} catch (NumberFormatException e) {
			return ResponseEntity.ok(userService.findByUsername(userId));
		}
	}

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}

	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user) {
		return ResponseEntity.ok(userService.update(user));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateById(@RequestBody User user, @PathVariable Integer userId) {
		user.setId(userId);
		return ResponseEntity.ok(userService.update(user));
	}

	@DeleteMapping("/{userId}")
	public void deleteById(@PathVariable Integer userId) {
		userService.deleteById(userId);
	}

	@GetMapping("/{userId}/roles")
	public ResponseEntity<List<Role>> getAllRoles(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.findAllRolesById(userId));
	}

}

