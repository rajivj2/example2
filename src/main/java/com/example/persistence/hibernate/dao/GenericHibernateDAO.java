package com.example.persistence.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.persistence.dao.GenericDAO;

public abstract class GenericHibernateDAO<T> implements GenericDAO<T> {
	
	protected EntityManager entityManager;
	
	@PersistenceContext(unitName = "defaultEntityManager")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
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