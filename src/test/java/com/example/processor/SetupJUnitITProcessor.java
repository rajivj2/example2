package com.example.processor;

import java.io.BufferedInputStream;
import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.example.config.ApacheCamelConfig;
import com.example.entities.Status;
import com.example.persistence.dao.StatusDAO;

public class SetupJUnitITProcessor {

	@Autowired
	protected ApacheCamelConfig config;
	@Autowired
	protected CamelContext context;
	protected ProducerTemplate producerTemplate;
	protected Properties properties;
	protected String source;
	@Autowired
	protected StatusDAO statusDAO;
	@Resource(name = "transactionManager")
	private JtaTransactionManager transactionManager;
	@PersistenceContext(unitName = "defaultEntityManager")
	private EntityManager entityManager;
	private static Logger LOGGER = LoggerFactory.getLogger(SetupJUnitITProcessor.class);

	public SetupJUnitITProcessor() throws Exception {
		properties = new Properties();
		properties.load(new BufferedInputStream(ClassLoader.getSystemResourceAsStream("camel.properties")));
		source = properties.getProperty("source");
	}

	@Before
	public void setUp() throws Exception {
		TransactionTemplate t = new TransactionTemplate();
		t.setTransactionManager(transactionManager);
		t.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status2) {
				LOGGER.info("Creating a transaction");
				entityManager.createQuery("DELETE FROM Status").executeUpdate();
				Status status = new Status();
				status.setUserId(10);
				statusDAO.save(status);
				LOGGER.info("Transaction committed");
			}
		});
		producerTemplate = context.createProducerTemplate();
	}

	@After
	public void tearDown() throws Exception {
//		config.stop();
	}
}