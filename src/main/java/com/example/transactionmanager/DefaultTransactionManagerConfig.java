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
public class DefaultTransactionManagerConfig {

	@Autowired
	@Qualifier(value = "entityManagerFactory")
	private EntityManagerFactory defaultEntityManagerFactoryConfig;
	private static JpaTransactionManager jpaTransactionManager;
	
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		if(jpaTransactionManager == null) {
			jpaTransactionManager = new JpaTransactionManager();
			jpaTransactionManager.setEntityManagerFactory(defaultEntityManagerFactoryConfig);
		}
		return jpaTransactionManager;
	}
}