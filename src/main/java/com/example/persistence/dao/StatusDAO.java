package com.example.persistence.dao;

import com.example.entities.Status;

public interface StatusDAO extends GenericDAO<Status> {

	public Status findByUserId(int userId);
}