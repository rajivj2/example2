package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.persistence.dao.DAOFactory;
import com.example.persistence.hibernate.dao.HibernateDAOFactory;

/**
 * This class represents the DAOFactoryConfig.
 * @author Rajiv Jain
 */
@Configuration
public class DAOFactoryConfig {

	@Autowired
	private DAOConfig daoConfig;
	
	@Bean
	public DAOFactory daoFactory() throws Exception {
		DAOFactory daoFactory = new HibernateDAOFactory(daoConfig.statusDAO());
		return daoFactory;
	}
}