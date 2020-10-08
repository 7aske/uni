
package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.entity.PostTag;
import rs.ac.metropolitan.cs230.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postTag")
public class PostTagController {
	@Autowired
	private PostTagService postTagService;

	@GetMapping("/getAll")
	public ResponseEntity<List<PostTag>> getAll() {
		return ResponseEntity.ok(postTagService.findAll());
	}

	@GetMapping("/getAllById/{idPostTag}")
	public ResponseEntity<PostTag> getById(@PathVariable Long idPostTag) {
		return ResponseEntity.ok(postTagService.findById(idPostTag));
	}


	@PostMapping("/save")
	public ResponseEntity<PostTag> save(@RequestBody PostTag postTag) {
		return ResponseEntity.ok(postTagService.save(postTag));
	}

	@PutMapping("/update")
	public ResponseEntity<PostTag> update(@RequestBody PostTag postTag) {
		return ResponseEntity.ok(postTagService.update(postTag));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody PostTag postTag) {
		return ResponseEntity.ok(postTagService.delete(postTag));
	}

	@DeleteMapping("/deleteById/{idPostTag}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idPostTag) {
		return ResponseEntity.ok(postTagService.deleteById(idPostTag));
	}
}
