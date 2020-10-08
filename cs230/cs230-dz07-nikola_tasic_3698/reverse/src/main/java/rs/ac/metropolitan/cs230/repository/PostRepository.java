package rs.ac.metropolitan.cs230.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.metropolitan.cs230.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
