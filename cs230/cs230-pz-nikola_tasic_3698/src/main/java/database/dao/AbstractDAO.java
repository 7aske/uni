package database.dao;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

abstract public class AbstractDAO<T> {
	private Class<T> entityClass;

	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void create(T entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	public T update(T entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		T updated = em.merge(entity);
		em.getTransaction().commit();
		return updated;
	}

	public void remove(T entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	public T find(Object id) {
		EntityManager em = getEntityManager();
		return em.find(entityClass, id);
	}

	public List<T> findAll() {
		EntityManager em = getEntityManager();
		CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}
}
