package com.example.backend.repository;

import com.example.backend.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
	Optional<Media> findByUri(String uri);
}