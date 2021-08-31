package com.example.backend.controller;

import com.example.backend.data.ValueContainer;
import com.example.backend.data.converter.RequestPageableConverter;
import com.example.backend.data.search.RequestParamQuery;
import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import com.example.backend.entity.Tag;
import com.example.backend.repository.specification.GenericSpecification;
import com.example.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping
	public ResponseEntity<List<Post>> getAll(
			@RequestParam(required = false, name="q") Specification<Post> query,
			@RequestParam(required = false, name = "page") Pageable pageable
	) {
		return ResponseEntity.ok(postService.findAll());
	}

	@GetMapping("/{postId}")
	public ResponseEntity<Post> getById(@PathVariable String postId) {
		try {
			return ResponseEntity.ok(postService.findById(Integer.parseInt(postId)));
		} catch (NumberFormatException ignored) {
			return ResponseEntity.ok(postService.findBySlug(postId));
		}
	}

	@GetMapping("/pages")
	public ResponseEntity<ValueContainer<Long>> getPageCount(
		@RequestParam(required = false, name="q") Specification<Post> query,
		@RequestParam(required = false, name = "page") Pageable pageable
	){
		long rowCount = postService.count(query);
		int pageSize = pageable == null ? RequestPageableConverter.DEFAULT_PAGE_SIZE : pageable.getPageSize();
		long pages = rowCount / pageSize;
		if (pages == 0) pages = 1;
		return ResponseEntity.ok(ValueContainer.of(pages));
	}

	@PostMapping
	public ResponseEntity<Post> save(@RequestBody Post post) {
		return ResponseEntity.ok(postService.save(post));
	}

	@PutMapping
	public ResponseEntity<Post> update(@RequestBody Post post) {
		return ResponseEntity.ok(postService.update(post));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<Post> updateById(@RequestBody Post post, @PathVariable Integer postId) {
		post.setId(postId);
		return ResponseEntity.ok(postService.update(post));
	}

	@DeleteMapping("/{postId}")
	public void deleteById(@PathVariable Integer postId) {
		postService.deleteById(postId);
	}

	@GetMapping("/{postId}/tags")
	public ResponseEntity<List<Tag>> getAllTags(@PathVariable Integer postId) {
		return ResponseEntity.ok(postService.findAllTagsById(postId));
	}

}

