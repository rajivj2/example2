package com.example.persistence.dao;

import javax.persistence.EntityManager;

/**
 * This interface defines methods common to all DAOs.
 * @author Rajiv Jain
 */
public interface GenericDAO<T> {

	/**
	 * This method saves T.
	 * @param t the t to save.
	 * @throws NullPointerException when T is null.
	 * @return the saved T.
	 */
	public T save(T t);
	/**
	 * This method updates T.
	 * @param t the t to update.
	 * @throws NullPointerException when T is null.
	 * @return the updated T.
	 */
	public T update(T t);
	/**
	 * This method sets the EntityManager.
	 * @param entityManager the entityManager.
	 */
	public void setEntityManager(EntityManager entityManager);
}