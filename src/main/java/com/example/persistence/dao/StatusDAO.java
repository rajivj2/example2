package com.example.persistence.dao;

import com.example.entities.Status;

/**
 * This interface defines specific methods for the StatusDAO.
 * @author Rajiv Jain
 */
public interface StatusDAO extends GenericDAO<Status> {

	public Status findByUserId(int userId);
}