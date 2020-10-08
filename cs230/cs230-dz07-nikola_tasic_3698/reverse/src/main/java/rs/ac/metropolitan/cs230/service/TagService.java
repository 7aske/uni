package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.Tag;
import java.util.List;

public interface TagService {

	List<Tag> findAll();

	Tag findById(Long idTag);

	Tag save(Tag tag);

	Tag update(Tag tag);

	boolean delete(Tag tag);

	boolean deleteById(Long idTag);
}
