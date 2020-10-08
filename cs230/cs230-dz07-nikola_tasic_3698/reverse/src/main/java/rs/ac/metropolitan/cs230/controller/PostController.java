
package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.entity.Post;
import rs.ac.metropolitan.cs230.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Post>> getAll() {
		return ResponseEntity.ok(postService.findAll());
	}

	@GetMapping("/getAllById/{idPost}")
	public ResponseEntity<Post> getById(@PathVariable Long idPost) {
		return ResponseEntity.ok(postService.findById(idPost));
	}


	@PostMapping("/save")
	public ResponseEntity<Post> save(@RequestBody Post post) {
		return ResponseEntity.ok(postService.save(post));
	}

	@PutMapping("/update")
	public ResponseEntity<Post> update(@RequestBody Post post) {
		return ResponseEntity.ok(postService.update(post));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Post post) {
		return ResponseEntity.ok(postService.delete(post));
	}

	@DeleteMapping("/deleteById/{idPost}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idPost) {
		return ResponseEntity.ok(postService.deleteById(idPost));
	}
}
