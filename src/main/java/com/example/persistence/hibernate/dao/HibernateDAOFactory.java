package com.example.persistence.hibernate.dao;

import com.example.persistence.dao.AccountDAO;
import com.example.persistence.dao.DAOFactory;
import com.example.persistence.dao.StatusDAO;

public class HibernateDAOFactory extends DAOFactory {

	private StatusDAO statusDAO;
	private AccountDAO accountDAO;
	
	public HibernateDAOFactory(StatusDAO statusDAO, AccountDAO accountDAO) {
		this.statusDAO = statusDAO;
		this.accountDAO = accountDAO;
	}
	
	public StatusDAO getStatusDAO() {
		return statusDAO;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}
}