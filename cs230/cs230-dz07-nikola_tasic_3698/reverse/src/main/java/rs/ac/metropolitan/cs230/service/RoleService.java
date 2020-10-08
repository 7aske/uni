package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.Role;
import java.util.List;

public interface RoleService {

	List<Role> findAll();

	Role findById(Long idRole);

	Role save(Role role);

	Role update(Role role);

	boolean delete(Role role);

	boolean deleteById(Long idRole);
}
