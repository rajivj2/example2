package com.example.persistence.dao.jpa;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.example.config.ProcessPersistenceConfig;
import com.example.config.ResourceConfig;
import com.example.datasource.DefaultJDBCDataSourceConfig;
import com.example.datasource.EmployeeDataSourceFactoryConfig;
import com.example.datasource.HibernateConfig;
import com.example.entitymanager.DefaultEntityManagerFactoryConfig;
import com.example.entitymanager.EmployeeEntityManagerFactoryConfig;
import com.example.persistence.dao.jpa.factory.EmployeeJpaFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {EmployeeEntityManagerFactoryConfig.class, HibernateConfig.class, 
	DefaultJDBCDataSourceConfig.class, ProcessPersistenceConfig.class, EmployeeDataSourceFactoryConfig.class, DefaultEntityManagerFactoryConfig.class, ResourceConfig.class, 
	EmployeeJpaFactory.class})
public class EmployeeJpaDAOIT {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		System.out.println("I");
	}
}