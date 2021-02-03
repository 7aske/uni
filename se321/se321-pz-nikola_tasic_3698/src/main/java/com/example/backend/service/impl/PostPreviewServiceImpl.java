package com.example.backend.service.impl;

import com.example.backend.entity.PostPreview;
import com.example.backend.repository.PostPreviewRepository;
import com.example.backend.service.PostPreviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostPreviewServiceImpl implements PostPreviewService {
	private final PostPreviewRepository postPreviewRepository;

	@Override
	public List<PostPreview> findAll(Pageable pageable) {
		if (pageable == null)
			return findAll();
		return postPreviewRepository.findAllByPublishedTrueAndDeletedFalse(pageable);
	}

	@Override
	public List<PostPreview> findAll(Specification<PostPreview> specification, Pageable pageable) {
		return postPreviewRepository.findAll(specification, pageable).getContent();
	}

	@Override
	public List<PostPreview> findAll() {
		return postPreviewRepository.findAllByPublishedTrueAndDeletedFalse();
	}
}
