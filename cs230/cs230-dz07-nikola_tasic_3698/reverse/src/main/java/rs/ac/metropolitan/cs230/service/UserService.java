package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.User;
import java.util.List;

public interface UserService {

	List<User> findAll();

	User findById(Long idUser);

	User save(User user);

	User update(User user);

	boolean delete(User user);

	boolean deleteById(Long idUser);
}
