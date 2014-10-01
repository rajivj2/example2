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
public class AccountTransactionManagerConfig {

	@Autowired
	@Qualifier(value = "entityManagerFactoryAccount")
	private EntityManagerFactory defaultEntityManagerFactoryConfig;
	private static JpaTransactionManager jpaTransactionManagerAccount;
	
	@Bean 
	public PlatformTransactionManager transactionManagerAccount() throws Exception {
		if(jpaTransactionManagerAccount == null) {
			jpaTransactionManagerAccount = new JpaTransactionManager(); 
	 		jpaTransactionManagerAccount.setEntityManagerFactory(defaultEntityManagerFactoryConfig); 
	 	}
		return jpaTransactionManagerAccount;
	}
}