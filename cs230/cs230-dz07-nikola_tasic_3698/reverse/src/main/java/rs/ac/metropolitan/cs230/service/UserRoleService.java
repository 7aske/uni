package rs.ac.metropolitan.cs230.service;

import rs.ac.metropolitan.cs230.entity.UserRole;
import java.util.List;

public interface UserRoleService {

	List<UserRole> findAll();

	UserRole findById(Long idUserRole);

	UserRole save(UserRole userRole);

	UserRole update(UserRole userRole);

	boolean delete(UserRole userRole);

	boolean deleteById(Long idUserRole);
}
