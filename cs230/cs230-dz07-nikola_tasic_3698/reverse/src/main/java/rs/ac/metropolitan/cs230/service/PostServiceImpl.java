package rs.ac.metropolitan.cs230.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.cs230.entity.Post;
import rs.ac.metropolitan.cs230.repository.PostRepository;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Post findById(Long idPost) {
		if (postRepository.findById(idPost).isPresent()) {
			return postRepository.findById(idPost).get();
		} else {
			return null;
		}
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post update(Post post) {
		return postRepository.save(post);
	}

	@Override
	public boolean delete(Post post) {
		postRepository.delete(post);
		return !postRepository.findById(post.getIdPost()).isPresent();
	}

	@Override
	public boolean deleteById(Long idPost) {
		postRepository.deleteById(idPost);
		return !postRepository.findById(idPost).isPresent();
	}

}
