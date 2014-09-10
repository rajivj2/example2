package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.example.persistence.dao.AccountDAO;
import com.example.persistence.dao.StatusDAO;
import com.example.persistence.hibernate.dao.AccountHibernateDAO;
import com.example.persistence.hibernate.dao.StatusHibernateDAO;

@Configuration
public class DAOConfig {
	
	@Bean
	@DependsOn(value = {"liquibase"})
	public StatusDAO statusDAO() throws Exception {
		StatusDAO statusDAO = new StatusHibernateDAO();
		return statusDAO;
	}
	
	@Bean
	@DependsOn(value = {"liquibaseAccount"})
	public AccountDAO accountDAO() throws Exception {
		AccountDAO accountDAO = new AccountHibernateDAO();
		return accountDAO;
	}
}