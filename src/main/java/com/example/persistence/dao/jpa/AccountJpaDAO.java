package com.example.persistence.dao.jpa;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.entities.Account;
import com.example.persistence.dao.AccountDAO;

public class AccountJpaDAO extends GenericJpaDAO<Account> implements AccountDAO {

	private Logger logger = LoggerFactory.getLogger(AccountJpaDAO.class);
	
	public AccountJpaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}