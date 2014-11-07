package com.example.transactionmanager;

import javax.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DefaultTransactionManagerConfig {

	@Autowired
	@Qualifier(value = "atomikosTransactionManager")
	TransactionManager atomikosTransactionManager;
	private JtaTransactionManager jtaTransactionManager;

	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		jtaTransactionManager = new JtaTransactionManager();
		jtaTransactionManager.setTransactionManager(atomikosTransactionManager);
		return jtaTransactionManager;
	}
}