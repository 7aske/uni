package com.example.backend.service;

import com.example.backend.entity.Category;

import java.util.List;

public interface CategoryService {

	List<Category> findAll();

	Category save(Category category);

	Category update(Category category);

	Category findById(Integer categoryId);

	void deleteById(Integer categoryId);

}