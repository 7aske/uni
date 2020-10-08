package rs.ac.metropolitan.cs230.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.metropolitan.cs230.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
