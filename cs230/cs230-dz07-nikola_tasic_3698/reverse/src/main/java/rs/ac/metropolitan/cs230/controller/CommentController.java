
package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.entity.Comment;
import rs.ac.metropolitan.cs230.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Comment>> getAll() {
		return ResponseEntity.ok(commentService.findAll());
	}

	@GetMapping("/getAllById/{idComment}")
	public ResponseEntity<Comment> getById(@PathVariable Long idComment) {
		return ResponseEntity.ok(commentService.findById(idComment));
	}


	@PostMapping("/save")
	public ResponseEntity<Comment> save(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.save(comment));
	}

	@PutMapping("/update")
	public ResponseEntity<Comment> update(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.update(comment));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.delete(comment));
	}

	@DeleteMapping("/deleteById/{idComment}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idComment) {
		return ResponseEntity.ok(commentService.deleteById(idComment));
	}
}
