package com.example.transactionmanager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
public class AtomikosTransactionManagerConfig {

	public AtomikosTransactionManagerConfig() {
		super();
	}

	@Bean
	public TransactionManager atomikosTransactionManager() throws SystemException {
		UserTransaction transactionManager = new UserTransactionManager();
		((UserTransactionManager)transactionManager).setForceShutdown(false);
		((UserTransactionManager)transactionManager).setTransactionTimeout(10);
		((UserTransactionManager)transactionManager).setStartupTransactionService(false);
		return (UserTransactionManager) transactionManager;
	}
}
