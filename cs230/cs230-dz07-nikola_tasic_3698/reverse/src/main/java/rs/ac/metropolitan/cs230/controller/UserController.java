
package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.entity.User;
import rs.ac.metropolitan.cs230.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/getAllById/{idUser}")
	public ResponseEntity<User> getById(@PathVariable Long idUser) {
		return ResponseEntity.ok(userService.findById(idUser));
	}


	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}

	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody User user) {
		return ResponseEntity.ok(userService.update(user));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody User user) {
		return ResponseEntity.ok(userService.delete(user));
	}

	@DeleteMapping("/deleteById/{idUser}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idUser) {
		return ResponseEntity.ok(userService.deleteById(idUser));
	}
}
