package com.example.backend.service;

import com.example.backend.entity.Post;
import com.example.backend.entity.PostPreview;
import com.example.backend.entity.Tag;

import java.util.List;

public interface TagService {

	List<Tag> findAll();

	Tag save(Tag tag);

	Tag update(Tag tag);

	Tag findById(Integer tagId);

	void deleteById(Integer tagId);

	List<PostPreview> findAllPostsById(Integer tagId);

}