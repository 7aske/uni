package com.example.backend.service;

import com.example.backend.data.search.RequestParamQuery;
import com.example.backend.entity.Post;
import com.example.backend.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PostService {

	List<Post> findAll();

	List<Post> findAll(RequestParamQuery query, Pageable pageable);

	Long count(Specification<Post> query);

	Post save(Post post);

	Post update(Post post);

	Post findById(Integer postId);

	Post findBySlug(String slug);

	void deleteById(Integer postId);

	List<Tag> findAllTagsById(Integer postId);

}