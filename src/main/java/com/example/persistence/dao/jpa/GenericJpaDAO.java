package com.example.persistence.dao.jpa;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.persistence.dao.GenericDAO;

public abstract class GenericJpaDAO<T> implements GenericDAO<T> {

	protected EntityManager entityManager;
	private static Logger LOGGER = LoggerFactory.getLogger(GenericJpaDAO.class);
	public GenericJpaDAO() {

	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public T save(T t) {
		LOGGER.info("In save");
		if(t == null) {
			throw new NullPointerException("Entity cannot be null");
		}
		else {
			LOGGER.info("About to persist");
			entityManager.persist(t);
			LOGGER.info("Persisted");
		}
		return t;
	}

	@Override
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