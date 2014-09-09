package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {

	@Autowired
	private EntityManagerFactoryConfig entityManagerFactoryConfig;
	private static JpaTransactionManager jpaTransactionManager;
	
	@Bean
	@DependsOn(value = {"entityManagerFactory"})
	public PlatformTransactionManager transactionManager() throws Exception {
		if(jpaTransactionManager == null) {
			jpaTransactionManager = new JpaTransactionManager();
			jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryConfig.entityManagerFactory().getObject());
		}
		return jpaTransactionManager;
	}
}