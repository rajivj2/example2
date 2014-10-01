package com.example.persistence.dao.jpa;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.entities.Account;
import com.example.entities.Employee;
import com.example.persistence.dao.AccountDAO;
import com.example.persistence.dao.EmployeeDAO;

public class EmployeeJpaDAO extends GenericJpaDAO<Employee> implements EmployeeDAO {

	private Logger logger = LoggerFactory.getLogger(EmployeeJpaDAO.class);
	
	public EmployeeJpaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}