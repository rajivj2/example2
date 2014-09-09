package com.example.persistence.dao;

import javax.persistence.EntityManager;

public interface GenericDAO<T> {

	public T save(T t);

	public T update(T t);

	public void setEntityManager(EntityManager entityManager);
}