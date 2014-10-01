package com.example.transactionmanager;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class EmployeeTransactionManagerConfig {

	@Autowired
	@Qualifier(value = "entityManagerFactoryEmployee")
	private EntityManagerFactory defaultEntityManagerFactoryConfig;
	private static JpaTransactionManager jpaTransactionManagerEmployee;
	
	@Bean 
	public PlatformTransactionManager transactionManagerEmployee() throws Exception {
		if(jpaTransactionManagerEmployee == null) {
			jpaTransactionManagerEmployee = new JpaTransactionManager(); 
	 		jpaTransactionManagerEmployee.setEntityManagerFactory(defaultEntityManagerFactoryConfig); 
	 	}
		return jpaTransactionManagerEmployee;
	}
}