package com.example.transactionmanager.hibernate4only;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AtomikosJtaPlatform extends AbstractJtaPlatform {

	@Autowired
	@Qualifier(value = "atomikosTransactionManager")
	TransactionManager atomikosTransactionManager;

	public AtomikosJtaPlatform() {
		super();
	}

	@Override
	protected TransactionManager locateTransactionManager() {
		return atomikosTransactionManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return null;
	}
}
