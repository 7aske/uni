package database.dao;

import com.sun.istack.NotNull;
import database.entity.BlogPost;

import javax.persistence.*;

public class BlogPostDAO extends AbstractDAO<BlogPost> {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("blogpu");
	private EntityManager em = emf.createEntityManager();

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public BlogPostDAO() {
		super(BlogPost.class);
	}

	public BlogPost findBySlug(@NotNull final String slug) {
		final String query = "select bp from BlogPost bp where bp.slug = :slug";
		try {
			return em.createQuery(query, BlogPost.class)
					.setParameter("slug", slug)
					.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
}
