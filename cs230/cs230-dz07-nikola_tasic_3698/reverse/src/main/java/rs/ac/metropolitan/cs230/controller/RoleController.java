
package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.entity.Role;
import rs.ac.metropolitan.cs230.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Role>> getAll() {
		return ResponseEntity.ok(roleService.findAll());
	}

	@GetMapping("/getAllById/{idRole}")
	public ResponseEntity<Role> getById(@PathVariable Long idRole) {
		return ResponseEntity.ok(roleService.findById(idRole));
	}


	@PostMapping("/save")
	public ResponseEntity<Role> save(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.save(role));
	}

	@PutMapping("/update")
	public ResponseEntity<Role> update(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.update(role));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.delete(role));
	}

	@DeleteMapping("/deleteById/{idRole}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idRole) {
		return ResponseEntity.ok(roleService.deleteById(idRole));
	}
}
