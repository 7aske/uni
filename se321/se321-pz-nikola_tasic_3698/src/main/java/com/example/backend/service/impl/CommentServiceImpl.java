package com.example.backend.service.impl;

import com.example.backend.entity.Comment;
import com.example.backend.repository.CommentRepository;
import com.example.backend.service.CommentService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment findById(Integer commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new NoSuchElementException("CommentService.notFound"));
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Comment update(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteById(Integer commentId) {
		commentRepository.deleteById(commentId);
	}
}