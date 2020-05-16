package rs.ac.metropolitan.cs230.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.cs230.entity.PostTag;
import rs.ac.metropolitan.cs230.repository.PostTagRepository;
import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {

	@Autowired
	private PostTagRepository postTagRepository;

	@Override
	public List<PostTag> findAll() {
		return postTagRepository.findAll();
	}

	@Override
	public PostTag findById(Long idPostTag) {
		if (postTagRepository.findById(idPostTag).isPresent()) {
			return postTagRepository.findById(idPostTag).get();
		} else {
			return null;
		}
	}

	@Override
	public PostTag save(PostTag postTag) {
		return postTagRepository.save(postTag);
	}

	@Override
	public PostTag update(PostTag postTag) {
		return postTagRepository.save(postTag);
	}

	@Override
	public boolean delete(PostTag postTag) {
		postTagRepository.delete(postTag);
		return !postTagRepository.findById(postTag.getIdPostTag()).isPresent();
	}

	@Override
	public boolean deleteById(Long idPostTag) {
		postTagRepository.deleteById(idPostTag);
		return !postTagRepository.findById(idPostTag).isPresent();
	}

}
