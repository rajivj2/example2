package com.example;

import static org.junit.Assert.assertNotNull;
import javax.annotation.Resource;
import org.apache.camel.component.mock.MockEndpoint;
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
import com.example.config.ProcessPersistenceConfig;
import com.example.config.ResourceConfig;
import com.example.datasource.DefaultJDBCDataSourceConfig;
import com.example.datasource.HibernateConfig;
import com.example.entities.Status;
import com.example.entitymanager.DefaultEntityManagerFactoryConfig;
import com.example.liquibase.DefaultLiquidBaseXMLConfig;
import com.example.persistence.dao.jpa.factory.StatusJpaFactory;
import com.example.transactionmanager.DefaultTransactionManagerConfig;

@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {StatusJpaFactory.class, ResourceConfig.class, DefaultJDBCDataSourceConfig.class, DefaultLiquidBaseXMLConfig.class, HibernateConfig.class, DefaultEntityManagerFactoryConfig.class, DefaultTransactionManagerConfig.class, ProcessPersistenceConfig.class, ApacheCamelConfig.class})
public class NotificationRouterIT extends SetupJUnitITProcessor {

	private Logger logger = LoggerFactory.getLogger(NotificationRouterIT.class);

	@Resource(name = "transactionManager")
	private JpaTransactionManager transactionManager;

	public NotificationRouterIT() throws Exception {

	}

//	@Test
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

