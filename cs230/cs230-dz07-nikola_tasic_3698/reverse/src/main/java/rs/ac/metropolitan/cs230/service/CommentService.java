package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.Comment;
import java.util.List;

public interface CommentService {

	List<Comment> findAll();

	Comment findById(Long idComment);

	Comment save(Comment comment);

	Comment update(Comment comment);

	boolean delete(Comment comment);

	boolean deleteById(Long idComment);
}
