package rs.ac.metropolitan.cs230.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.cs230.entity.Comment;
import rs.ac.metropolitan.cs230.repository.CommentRepository;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment findById(Long idComment) {
		if (commentRepository.findById(idComment).isPresent()) {
			return commentRepository.findById(idComment).get();
		} else {
			return null;
		}
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
	public boolean delete(Comment comment) {
		commentRepository.delete(comment);
		return !commentRepository.findById(comment.getIdComment()).isPresent();
	}

	@Override
	public boolean deleteById(Long idComment) {
		commentRepository.deleteById(idComment);
		return !commentRepository.findById(idComment).isPresent();
	}

}
