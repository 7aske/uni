
package rs.ac.metropolitan.cs230.controller;

import rs.ac.metropolitan.cs230.entity.Tag;
import rs.ac.metropolitan.cs230.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
	@Autowired
	private TagService tagService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Tag>> getAll() {
		return ResponseEntity.ok(tagService.findAll());
	}

	@GetMapping("/getAllById/{idTag}")
	public ResponseEntity<Tag> getById(@PathVariable Long idTag) {
		return ResponseEntity.ok(tagService.findById(idTag));
	}


	@PostMapping("/save")
	public ResponseEntity<Tag> save(@RequestBody Tag tag) {
		return ResponseEntity.ok(tagService.save(tag));
	}

	@PutMapping("/update")
	public ResponseEntity<Tag> update(@RequestBody Tag tag) {
		return ResponseEntity.ok(tagService.update(tag));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Tag tag) {
		return ResponseEntity.ok(tagService.delete(tag));
	}

	@DeleteMapping("/deleteById/{idTag}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idTag) {
		return ResponseEntity.ok(tagService.deleteById(idTag));
	}
}
