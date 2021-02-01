package com.example.backend.controller;

import com.example.backend.data.search.RequestParamQuery;
import com.example.backend.entity.PostPreview;
import com.example.backend.service.PostPreviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/previews")
@RequiredArgsConstructor
public class PostPreviewController {
	private final PostPreviewService postPreviewService;

	@GetMapping
	public ResponseEntity<List<PostPreview>> getAll(
			@RequestParam(required = false, name="q") Specification<PostPreview> query,
			@RequestParam(required = false, name = "page") Pageable pageable) {
		return ResponseEntity.ok(postPreviewService.findAll(query, pageable));
	}
}

