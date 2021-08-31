package com.example.backend.controller;

import com.example.backend.entity.Post;
import com.example.backend.entity.PostPreview;
import com.example.backend.entity.Tag;
import com.example.backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
	private final TagService tagService;

	@GetMapping
	public ResponseEntity<List<Tag>> getAll() {
		return ResponseEntity.ok(tagService.findAll());
	}

	@GetMapping("/{tagId}")
	public ResponseEntity<Tag> getById(@PathVariable Integer tagId) {
		return ResponseEntity.ok(tagService.findById(tagId));
	}

	@PostMapping
	public ResponseEntity<Tag> save(@RequestBody Tag tag) {
		return ResponseEntity.ok(tagService.save(tag));
	}

	@PutMapping
	public ResponseEntity<Tag> update(@RequestBody Tag tag) {
		return ResponseEntity.ok(tagService.update(tag));
	}

	@PutMapping("/{tagId}")
	public ResponseEntity<Tag> updateById(@RequestBody Tag tag, @PathVariable Integer tagId) {
		tag.setId(tagId);
		return ResponseEntity.ok(tagService.update(tag));
	}

	@DeleteMapping("/{tagId}")
	public void deleteById(@PathVariable Integer tagId) {
		tagService.deleteById(tagId);
	}

	@GetMapping("/{tagId}/posts")
	public ResponseEntity<List<PostPreview>> getAllPosts(@PathVariable Integer tagId) {
		return ResponseEntity.ok(tagService.findAllPostsById(tagId));
	}

}

