package rs.ac.metropolitan.cs230.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.cs230.entity.Role;
import rs.ac.metropolitan.cs230.repository.RoleRepository;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long idRole) {
		if (roleRepository.findById(idRole).isPresent()) {
			return roleRepository.findById(idRole).get();
		} else {
			return null;
		}
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public boolean delete(Role role) {
		roleRepository.delete(role);
		return !roleRepository.findById(role.getIdRole()).isPresent();
	}

	@Override
	public boolean deleteById(Long idRole) {
		roleRepository.deleteById(idRole);
		return !roleRepository.findById(idRole).isPresent();
	}

}
