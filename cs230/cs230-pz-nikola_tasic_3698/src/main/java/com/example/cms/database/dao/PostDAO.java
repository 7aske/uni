package com.example.cms.database.dao;

import com.example.cms.database.entity.Comment;
import com.example.cms.database.entity.Post;
import com.example.cms.database.entity.Tag;
import com.example.cms.util.HibernateUtil;
import com.example.cms.util.SetUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

public class PostDAO extends AbstractDAO<Post> {

	public PostDAO() {
		super(Post.class);
	}

	@Override
	@Transactional
	public List<Post> findAll() {
		List<Post> posts = super.findAll();
		posts.sort(Comparator.comparing(Post::getDatePosted).reversed());
		return posts;
	}

	@Transactional
	public Post findBySlug(final String slug) {
		final String QUERY = "select bp from Post bp where bp.slug = :slug";
		Post post = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			post = session.createQuery(QUERY, Post.class)
					.setParameter("slug", slug)
					.getSingleResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return post;
	}

	@Override
	public void remove(Post entity) {
		CommentDAO commentDAO = new CommentDAO();
		if (entity.getCommentList() != null) {
			for (Comment comment : entity.getCommentList()) {
				commentDAO.remove(comment);
			}
		}
		super.remove(entity);
	}

	@Override
	public void removeById(Object id) {
		remove(find(id));
	}

	public List<Post> findByTagName(final String tagName) {
		Set<Tag> tagSet = new HashSet<>();
		Tag tag = new TagDAO().findByName(tagName);
		if (tag != null) {
			tagSet.add(tag);
		}
		return findByTagList(tagSet);
	}

	public List<Post> findByTagList(final Collection<Tag> tags) {
		// SCREW YOU JPA
		List<Post> posts = findAll();
		return posts.stream().filter(p -> SetUtil.inter(p.getTags(), tags).size() > 0).collect(Collectors.toList());
	}

	public List<Post> findPublishedByTagName(final String tagName) {
		Set<Tag> tagSet = new HashSet<>();
		Tag tag = new TagDAO().findByName(tagName);
		if (tag != null) {
			tagSet.add(tag);
		}
		return findPublishedByTagList(tagSet);
	}

	public List<Post> findPublishedByTagName(final String tagName, int limit) {
		Set<Tag> tagSet = new HashSet<>();
		Tag tag = new TagDAO().findByName(tagName);
		if (tag != null) {
			tagSet.add(tag);
		}
		return findPublishedByTagList(tagSet, limit);
	}

	public List<Post> findPublishedByTagList(final Collection<Tag> tags) {
		// SCREW YOU JPA
		List<Post> posts = findAllPublished();
		return posts.stream().filter(p -> SetUtil.inter(p.getTags(), tags).size() > 0).collect(Collectors.toList());
	}

	public List<Post> findPublishedByTagList(final Collection<Tag> tags, int limit) {
		// SCREW YOU JPA
		List<Post> posts = findAllPublished();
		return posts.stream().limit(limit).filter(p -> SetUtil.inter(p.getTags(), tags).size() > 0).collect(Collectors.toList());
	}

	@Transactional
	public List<Post> findAllPublished() {
		final String QUERY = "select bp from Post bp where bp.published = :published order by bp.datePosted desc";

		List<Post> posts = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			posts = session.createQuery(QUERY, Post.class)
					.setParameter("published", true)
					.getResultList();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posts;
	}

	@Transactional
	public List<Post> findAllPublished(int limit) {
		final String QUERY = "select bp from Post bp where bp.published = :published order by bp.datePosted desc";

		List<Post> posts = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			posts = session.createQuery(QUERY, Post.class)
					.setParameter("published", true)
					.setMaxResults(limit)
					.getResultList();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posts;
	}
}
