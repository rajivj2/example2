package com.example.persistence.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.entities.Account;
import com.example.persistence.dao.AccountDAO;

public class AccountHibernateDAO extends GenericHibernateDAO<Account> implements AccountDAO {

	private Logger logger = LoggerFactory.getLogger(AccountHibernateDAO.class);
	
	public AccountHibernateDAO() {
		
	}

	@PersistenceContext(unitName = "accountEntityManager")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}