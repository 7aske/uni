package rs.ac.metropolitan.cs230.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.cs230.entity.Tag;
import rs.ac.metropolitan.cs230.repository.TagRepository;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag findById(Long idTag) {
		if (tagRepository.findById(idTag).isPresent()) {
			return tagRepository.findById(idTag).get();
		} else {
			return null;
		}
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag update(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public boolean delete(Tag tag) {
		tagRepository.delete(tag);
		return !tagRepository.findById(tag.getIdTag()).isPresent();
	}

	@Override
	public boolean deleteById(Long idTag) {
		tagRepository.deleteById(idTag);
		return !tagRepository.findById(idTag).isPresent();
	}

}
