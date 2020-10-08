package rs.ac.metropolitan.cs230.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.cs230.entity.UserRole;
import rs.ac.metropolitan.cs230.repository.UserRoleRepository;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}

	@Override
	public UserRole findById(Long idUserRole) {
		if (userRoleRepository.findById(idUserRole).isPresent()) {
			return userRoleRepository.findById(idUserRole).get();
		} else {
			return null;
		}
	}

	@Override
	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

	@Override
	public UserRole update(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

	@Override
	public boolean delete(UserRole userRole) {
		userRoleRepository.delete(userRole);
		return !userRoleRepository.findById(userRole.getIdUserRole()).isPresent();
	}

	@Override
	public boolean deleteById(Long idUserRole) {
		userRoleRepository.deleteById(idUserRole);
		return !userRoleRepository.findById(idUserRole).isPresent();
	}

}
