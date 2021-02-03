package com.example.cms.database.dao;

import com.example.cms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

abstract public class AbstractDAO<T> {
	private final Class<T> entityClass;

	public AbstractDAO(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Transactional
	public void create(final T entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Transactional
	public T update(final T entity) {
		T t = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			t = (T) session.merge(entity);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return t;
	}

	@Transactional
	public void remove(T entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Transactional
	public void removeById(Object id) {
		remove(find(id));
	}

	@Transactional
	public T find(Object id) {
		T t = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			t = session.find(entityClass, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Transactional
	public List<T> findAll() {
		List<T> tList = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			CriteriaQuery<T> tCriteriaQuery = session.getCriteriaBuilder().createQuery(entityClass);
			tCriteriaQuery.select(tCriteriaQuery.from(entityClass));
			tList = session.createQuery(tCriteriaQuery).getResultList();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tList;
	}
}
