package database.dao;

import com.sun.istack.NotNull;
import database.entity.BlogPost;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

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

	@Override
	public List<BlogPost> findAll() {
		List<BlogPost> blogPosts = super.findAll();
		blogPosts.sort(Comparator.comparing(BlogPost::getDatePosted).reversed());
		return blogPosts;
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

	public List<BlogPost> findAllPublished() {
		final String query = "select bp from BlogPost bp where bp.published = :published order by datePosted desc";
		try {
			return em.createQuery(query, BlogPost.class)
					.setParameter("published", true)
					.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
}
