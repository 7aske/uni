
package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.entity.UserRole;
import rs.ac.metropolitan.cs230.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/getAll")
	public ResponseEntity<List<UserRole>> getAll() {
		return ResponseEntity.ok(userRoleService.findAll());
	}

	@GetMapping("/getAllById/{idUserRole}")
	public ResponseEntity<UserRole> getById(@PathVariable Long idUserRole) {
		return ResponseEntity.ok(userRoleService.findById(idUserRole));
	}


	@PostMapping("/save")
	public ResponseEntity<UserRole> save(@RequestBody UserRole userRole) {
		return ResponseEntity.ok(userRoleService.save(userRole));
	}

	@PutMapping("/update")
	public ResponseEntity<UserRole> update(@RequestBody UserRole userRole) {
		return ResponseEntity.ok(userRoleService.update(userRole));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody UserRole userRole) {
		return ResponseEntity.ok(userRoleService.delete(userRole));
	}

	@DeleteMapping("/deleteById/{idUserRole}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idUserRole) {
		return ResponseEntity.ok(userRoleService.deleteById(idUserRole));
	}
}
