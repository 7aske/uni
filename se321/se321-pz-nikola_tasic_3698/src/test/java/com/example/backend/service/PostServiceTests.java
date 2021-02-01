package com.example.backend.service;

import com.example.backend.entity.Post;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.TagRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.NoSuchElementException;

import static com.example.backend.util.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class PostServiceTests {
	@Autowired
	PostRepository postRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	PostService postService;

	@AfterEach
	void teardown() {
		postRepository.deleteAll();
		tagRepository.deleteAll();
		categoryRepository.deleteAll();
	}

	@Test
	void test_savePost() {
		Post post = getPost();
		post.setCategory(categoryRepository.save(getCategory()));
		post.setTags(Collections.singletonList(tagRepository.save(getTag())));
		postService.save(post);

		assertEquals(1, postRepository.count());
	}

	@Test
	void test_findBySlug() {
		Post post = getPost();
		postService.save(post);
		Post foundPost = postService.findBySlug("slug");

		assertEquals(foundPost, post);
	}

	@Test
	void test_findBySlug_shouldThrowIfNotFound() {
		assertThrows(NoSuchElementException.class,
				() -> postService.findBySlug("some-other-slug"));
	}
}
