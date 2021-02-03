package com.example.backend.controller;

import com.example.backend.entity.Comment;
import com.example.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping
	public ResponseEntity<List<Comment>> getAll() {
		return ResponseEntity.ok(commentService.findAll());
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<Comment> getById(@PathVariable Integer commentId) {
		return ResponseEntity.ok(commentService.findById(commentId));
	}

	@PostMapping
	public ResponseEntity<Comment> save(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.save(comment));
	}

	@PutMapping
	public ResponseEntity<Comment> update(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentService.update(comment));
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<Comment> updateById(@RequestBody Comment comment, @PathVariable Integer commentId) {
		comment.setId(commentId);
		return ResponseEntity.ok(commentService.update(comment));
	}

	@DeleteMapping("/{commentId}")
	public void deleteById(@PathVariable Integer commentId) {
		commentService.deleteById(commentId);
	}

}

