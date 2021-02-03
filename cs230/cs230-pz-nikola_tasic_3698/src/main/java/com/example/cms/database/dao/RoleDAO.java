package com.example.cms.database.dao;

import com.example.cms.database.entity.Role;
import com.example.cms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.transaction.Transactional;

public class RoleDAO extends AbstractDAO<Role> {


	public RoleDAO() {
		super(Role.class);
	}

	@Transactional
	public Role findByName(final String roleName) {
		final String QUERY = "select r from Role r where r.roleName = :roleName";
		Role role = null;
		Transaction transaction;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			role = session.createQuery(QUERY, Role.class)
					.setParameter("roleName", roleName)
					.getSingleResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;

	}
}
