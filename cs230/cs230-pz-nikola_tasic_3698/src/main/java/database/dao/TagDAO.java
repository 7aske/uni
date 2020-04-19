package database.dao;

import database.entity.Tag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TagDAO extends AbstractDAO<Tag> {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("blogpu");
	private EntityManager em = emf.createEntityManager();

	public TagDAO() {
		super(Tag.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
