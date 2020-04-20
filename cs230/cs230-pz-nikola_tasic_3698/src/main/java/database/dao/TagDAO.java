package database.dao;

import database.entity.BlogPost;
import database.entity.Tag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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

	public Tag findByName(String name){
		final String query = "select t from Tag t where t.name = :name";
		try {
			return em.createQuery(query, Tag.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
}
