package com.example.backend.controller;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import com.example.backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
	private final RoleService roleService;

	@GetMapping
	public ResponseEntity<List<Role>> getAll() {
		return ResponseEntity.ok(roleService.findAll());
	}

	@GetMapping("/{roleId}")
	public ResponseEntity<Role> getById(@PathVariable Integer roleId) {
		return ResponseEntity.ok(roleService.findById(roleId));
	}

	@PostMapping
	public ResponseEntity<Role> save(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.save(role));
	}

	@PutMapping
	public ResponseEntity<Role> update(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.update(role));
	}

	@PutMapping("/{roleId}")
	public ResponseEntity<Role> updateById(@RequestBody Role role, @PathVariable Integer roleId) {
		role.setId(roleId);
		return ResponseEntity.ok(roleService.update(role));
	}

	@DeleteMapping("/{roleId}")
	public void deleteById(@PathVariable Integer roleId) {
		roleService.deleteById(roleId);
	}

	@GetMapping("/{roleId}/users")
	public ResponseEntity<List<User>> getAllUsers(@PathVariable Integer roleId) {
		return ResponseEntity.ok(roleService.findAllUsersById(roleId));
	}

}

