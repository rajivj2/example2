package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import com.example.persistence.dao.StatusDAO;
import com.example.persistence.hibernate.dao.StatusHibernateDAO;

/**
 * This class represents the DAOConfiguration.
 * @author Rajiv Jain
 */
@Configuration
public class DAOConfig {
	
	@Bean
	@DependsOn(value = {"liquibase"})
	public StatusDAO statusDAO() throws Exception {
		StatusDAO statusDAO = new StatusHibernateDAO();
		return statusDAO;
	}
}