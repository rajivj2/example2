package com.example;

import static org.junit.Assert.*;
import javax.annotation.Resource;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.example.config.ApacheCamelConfig;
import com.example.config.DAOConfig;
import com.example.config.DAOFactoryConfig;
import com.example.config.DataSourceConfig;
import com.example.config.EntityManagerFactoryConfig;
import com.example.config.HibernateConfig;
import com.example.config.LiquidBaseXMLConfig;
import com.example.config.ProcessPersistenceConfig;
import com.example.config.ResourceConfig;
import com.example.config.TransactionManagerConfig;
import com.example.entities.Status;

@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DAOConfig.class, DAOFactoryConfig.class, ResourceConfig.class, DataSourceConfig.class, LiquidBaseXMLConfig.class, HibernateConfig.class, EntityManagerFactoryConfig.class, TransactionManagerConfig.class, ProcessPersistenceConfig.class, ApacheCamelConfig.class})
public class NotificationRouterIT extends SetupJUnitITProcessor {
	
	private Logger logger = LoggerFactory.getLogger(NotificationRouterIT.class);
	
	@Resource(name = "transactionManager")
	private JpaTransactionManager transactionManager;
	
	public NotificationRouterIT() throws Exception {
		
	}
	
	@Test
	public void testMessageSendToConsumerQueueRemoteId() throws Exception {
		TransactionTemplate t = new TransactionTemplate();
		t.setTransactionManager(transactionManager);
		t.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status2) {
				Status status = new Status();
				status.setUserId(10);
				statusDAO.save(status);
			}
		});
		MockEndpoint mockEndpoint = (MockEndpoint) this.context.getEndpoint(properties.getProperty("activemq.destination"));
		Status savedStatus = producerTemplate.requestBody(source, "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><example><remoteid>10</remoteid></example>", Status.class);
		assertNotNull(savedStatus);
		mockEndpoint.expectedMessageCount(1);
		mockEndpoint.assertIsSatisfied();
	}
}

