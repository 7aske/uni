package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.PostTag;
import java.util.List;

public interface PostTagService {

	List<PostTag> findAll();

	PostTag findById(Long idPostTag);

	PostTag save(PostTag postTag);

	PostTag update(PostTag postTag);

	boolean delete(PostTag postTag);

	boolean deleteById(Long idPostTag);
}
