package com.example.persistence.dao.jpa.factory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.persistence.dao.StatusDAO;
import com.example.persistence.dao.jpa.StatusJpaDAO;

@Configuration
public class StatusJpaFactory {

	@PersistenceContext(unitName = "defaultEntityManager")
	EntityManager entityManager;

	@Bean
	public StatusDAO statusDAO() throws Exception {
		StatusDAO statusDAO = new StatusJpaDAO(entityManager);
		return statusDAO;
	}
}