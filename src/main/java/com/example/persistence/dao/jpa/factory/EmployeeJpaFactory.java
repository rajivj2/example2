package com.example.persistence.dao.jpa.factory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.persistence.dao.AccountDAO;
import com.example.persistence.dao.EmployeeDAO;
import com.example.persistence.dao.jpa.AccountJpaDAO;
import com.example.persistence.dao.jpa.EmployeeJpaDAO;

/**
 * This class represents the AccountJpaFactory.
 * @author Rajiv Jain
 */
@Configuration
public class EmployeeJpaFactory {
	
	@PersistenceContext(unitName = "employeeEntityManager")
	EntityManager entityManager;
	
	@Bean
	public EmployeeDAO employeeDAO() throws Exception {
		EmployeeDAO employeeDAO = new EmployeeJpaDAO(entityManager);
		return employeeDAO;
	}
}