package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.Post;
import java.util.List;

public interface PostService {

	List<Post> findAll();

	Post findById(Long idPost);

	Post save(Post post);

	Post update(Post post);

	boolean delete(Post post);

	boolean deleteById(Long idPost);
}
