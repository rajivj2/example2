package com.example.persistence.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.persistence.dao.GenericDAO;

public abstract class GenericJpaDAO<T> implements GenericDAO<T> {
	
	protected EntityManager entityManager;
	
	public GenericJpaDAO() {
		
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
		}
		return t;
	}

	public T update(T t) {
		if(t == null) {
			throw new NullPointerException("Entity cannot be null");
		}
		else {
			entityManager.merge(t);
		}
		return t;
	}
}