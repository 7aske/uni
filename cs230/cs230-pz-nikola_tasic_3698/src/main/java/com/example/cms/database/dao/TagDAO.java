package com.example.cms.database.dao;

import com.example.cms.database.entity.Tag;
import com.example.cms.util.HibernateUtil;
import com.example.cms.util.UrlUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.transaction.Transactional;

public class TagDAO extends AbstractDAO<Tag> {

	public TagDAO() {
		super(Tag.class);
	}

	@Override
	@Transactional
	public void create(Tag entity) {
		entity.setName(UrlUtil.encodeValue(entity.getName()));
		super.create(entity);
	}

	@Transactional
	public Tag findByName(final String name) {
		String urlEncoded = UrlUtil.encodeValue(name);
		final String QUERY = "select t from Tag t where t.name = :name or t.name = :urlEncoded";
		Tag tag = null;
		Transaction transaction;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			tag = session.createQuery(QUERY, Tag.class)
					.setParameter("name", name)
					.setParameter("urlEncoded", urlEncoded)
					.getSingleResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}
}
