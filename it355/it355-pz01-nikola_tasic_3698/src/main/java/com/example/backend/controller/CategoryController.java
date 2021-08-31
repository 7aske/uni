package com.example.backend.controller;

import com.example.backend.entity.Category;
import com.example.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.findAll());
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getById(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(categoryService.findById(categoryId));
	}

	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.save(category));
	}

	@PutMapping
	public ResponseEntity<Category> update(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.update(category));
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateById(@RequestBody Category category, @PathVariable Integer categoryId) {
		category.setId(categoryId);
		return ResponseEntity.ok(categoryService.update(category));
	}

	@DeleteMapping("/{categoryId}")
	public void deleteById(@PathVariable Integer categoryId) {
		categoryService.deleteById(categoryId);
	}

}

