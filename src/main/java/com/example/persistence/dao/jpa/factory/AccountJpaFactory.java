package com.example.persistence.dao.jpa.factory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.persistence.dao.AccountDAO;
import com.example.persistence.dao.jpa.AccountJpaDAO;

@Configuration
public class AccountJpaFactory {

	@PersistenceContext(unitName = "accountEntityManager")
	EntityManager entityManager;

	@Bean
	public AccountDAO accountDAO() throws Exception {
		AccountDAO accountDAO = new AccountJpaDAO(entityManager);
		return accountDAO;
	}
}