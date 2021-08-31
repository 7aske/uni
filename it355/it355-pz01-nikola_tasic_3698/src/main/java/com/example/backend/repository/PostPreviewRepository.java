package com.example.backend.repository;

import com.example.backend.entity.PostPreview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostPreviewRepository extends JpaRepository<PostPreview, Integer>, JpaSpecificationExecutor<PostPreview> {
	List<PostPreview> findAllByPublishedTrueAndDeletedFalse(Pageable pageable);
	List<PostPreview> findAllByPublishedTrueAndDeletedFalse();
	List<PostPreview> findAll(Specification<PostPreview> specification);
}