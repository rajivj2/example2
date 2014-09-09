package com.example.persistence.hibernate.dao;

import com.example.persistence.dao.DAOFactory;
import com.example.persistence.dao.StatusDAO;

public class HibernateDAOFactory extends DAOFactory {

	private StatusDAO statusDAO;
	
	public HibernateDAOFactory(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
	
	public StatusDAO getStatusDAO() {
		return statusDAO;
	}
}