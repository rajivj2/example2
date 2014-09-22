package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class TransactionManagerConfig {

	@Autowired
	private EntityManagerFactoryConfig entityManagerFactoryConfig;
	private static JpaTransactionManager jpaTransactionManager;
	private static JpaTransactionManager jpaTransactionManagerAccount;
	
	@Bean
	@DependsOn(value = {"entityManagerFactory"})
	public PlatformTransactionManager transactionManager() throws Exception {
		if(jpaTransactionManager == null) {
			jpaTransactionManager = new JpaTransactionManager();
			jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryConfig.entityManagerFactory().getObject());
		}
		return jpaTransactionManager;
	}
	
	@Bean 
	@DependsOn(value = {"entityManagerFactoryAccount"}) 
	public PlatformTransactionManager transactionManagerAccount() throws Exception {
		if(jpaTransactionManagerAccount == null) {
			jpaTransactionManagerAccount = new JpaTransactionManager(); 
	 		jpaTransactionManagerAccount.setEntityManagerFactory(entityManagerFactoryConfig.entityManagerFactoryAccount().getObject()); 
	 	}
		return jpaTransactionManagerAccount;
	}
}