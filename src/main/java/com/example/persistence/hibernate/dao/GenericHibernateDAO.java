package com.example.persistence.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.persistence.dao.GenericDAO;

/**
 * This class implements common method for all DAOs.
 * @author Rajiv Jain
 */
public abstract class GenericHibernateDAO<T> implements GenericDAO<T> {
	
	protected EntityManager entityManager;
	
	/**
	 * This method sets the EntityManager.
	 * @param entityManager the entityManager.
	 */
	@PersistenceContext(unitName = "defaultEntityManager")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * This method gets the entity manager.
	 * @return the EntityManager.
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * This method saves T.
	 * @param t the t to save.
	 * @throws NullPointerException when T is null.
	 * @return the saved T.
	 */
	public T save(T t) {
		if(t == null) {
			throw new NullPointerException("Entity cannot be null");
		}
		else {
			entityManager.persist(t);
			entityManager.flush();
		}
		return t;
	}
	
	/**
	 * This method updates T.
	 * @param t the t to update.
	 * @throws NullPointerException when T is null.
	 * @return the updated T.
	 */
	public T update(T t) {
		if(t == null) {
			throw new NullPointerException("Entity cannot be null");
		}
		else {
			entityManager.merge(t);
			entityManager.flush();
		}
		return t;
	}
}