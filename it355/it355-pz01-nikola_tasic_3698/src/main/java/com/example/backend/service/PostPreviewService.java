package com.example.backend.service;

import com.example.backend.entity.PostPreview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PostPreviewService {
	List<PostPreview> findAll(Pageable pageable);

	List<PostPreview> findAll(Specification<PostPreview> specification, Pageable pageable);

	List<PostPreview> findAll();
}