package rs.ac.metropolitan.cs230.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.cs230.entity.User;
import rs.ac.metropolitan.cs230.repository.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long idUser) {
		if (userRepository.findById(idUser).isPresent()) {
			return userRepository.findById(idUser).get();
		} else {
			return null;
		}
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean delete(User user) {
		userRepository.delete(user);
		return !userRepository.findById(user.getIdUser()).isPresent();
	}

	@Override
	public boolean deleteById(Long idUser) {
		userRepository.deleteById(idUser);
		return !userRepository.findById(idUser).isPresent();
	}

}
