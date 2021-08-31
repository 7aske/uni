package com.example.backend.controller;

import com.example.backend.entity.Media;
import com.example.backend.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/medias")
@RequiredArgsConstructor
public class MediaController {
	private final MediaService mediaService;

	@GetMapping
	public ResponseEntity<List<Media>> getAll() {
		return ResponseEntity.ok(mediaService.findAll());
	}

	@GetMapping("/{mediaId}")
	public ResponseEntity<Media> getById(@PathVariable Integer mediaId) {
		return ResponseEntity.ok(mediaService.findById(mediaId));
	}

	@PostMapping
	public ResponseEntity<Media> save(@RequestParam MultipartFile file) {
		return ResponseEntity.ok(mediaService.upload(file));
	}

	@DeleteMapping("/{mediaId}")
	public void deleteById(@PathVariable Integer mediaId) {
		mediaService.deleteById(mediaId);
	}

}

