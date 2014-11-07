package com.example.processor;

import static org.junit.Assert.assertNotNull;
import javax.annotation.Resource;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.example.config.ApacheCamelConfig;
import com.example.config.ProcessPersistenceConfig;
import com.example.config.ResourceConfig;
import com.example.datasource.DefaultDataSourceFactoryConfig;
import com.example.datasource.DefaultJDBCDataSourceConfig;
import com.example.datasource.EmployeeDataSourceFactoryConfig;
import com.example.datasource.HibernateConfig;
import com.example.datasource.jms.JmsConfig;
import com.example.datasource.jms.JmsConnectionFactoryConfig;
import com.example.entities.Employee;
import com.example.entities.Status;
import com.example.entities.xml.Entity;
import com.example.entitymanager.DefaultEntityManagerFactoryConfig;
import com.example.entitymanager.EmployeeEntityManagerFactoryConfig;
import com.example.persistence.dao.EmployeeDAO;
import com.example.persistence.dao.jpa.factory.EmployeeJpaFactory;
import com.example.persistence.dao.jpa.factory.StatusJpaFactory;
import com.example.transactionmanager.AtomikosTransactionManagerConfig;
import com.example.transactionmanager.DefaultTransactionManagerConfig;

@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {StatusJpaFactory.class, EmployeeJpaFactory.class,
	ResourceConfig.class, DefaultJDBCDataSourceConfig.class, EmployeeDataSourceFactoryConfig.class, HibernateConfig.class,
	EmployeeEntityManagerFactoryConfig.class, DefaultEntityManagerFactoryConfig.class, DefaultTransactionManagerConfig.class, ProcessPersistenceConfig.class,
	DefaultDataSourceFactoryConfig.class, AtomikosTransactionManagerConfig.class, JmsConfig.class, JmsConnectionFactoryConfig.class,
	ApacheCamelConfig.class})
public class NotificationRouterIT extends SetupJUnitITProcessor {

	private Logger LOGGER = LoggerFactory.getLogger(NotificationRouterIT.class);

	@Autowired
	private JmsConfig jmsConfig;
	@Resource(name = "contentEnricherProcessor")
	private ContentEnricherProcessor contentEnricherProcessor;
	@Autowired
	private EmployeeDAO employeeDAO;

	public NotificationRouterIT() throws Exception {

	}

//	@Test
	public void testMessageSendToConsumerQueueRemoteId() throws Exception {
		LOGGER.info("RemoteId test has started");
		MockEndpoint mockEndpoint = (MockEndpoint) this.context.getEndpoint(jmsConfig.getDestination());
		mockEndpoint.reset();
		Status savedStatus = producerTemplate.requestBody(jmsConfig.getSource(), "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><example><remoteid>10</remoteid></example>", Status.class);
		assertNotNull(savedStatus);
		mockEndpoint.expectedMessageCount(1);
		mockEndpoint.assertIsSatisfied();
		LOGGER.info("RemoteId test has completed");
	}

	@Transactional
	@Test
	public void testProcess() throws Exception {
		LOGGER.info("Process test has started");
		Entity entity = new Entity();
		entity.setUserId("10");
		Status status = contentEnricherProcessor.process(entity);
		assertNotNull(status);
		LOGGER.info("Process test has finished");
	}

//	@Test
	public void testRoute() {
		LOGGER.info("Route test has started");
		MockEndpoint mockEndpoint = (MockEndpoint) this.context.getEndpoint(jmsConfig.getDestination());
		mockEndpoint.reset();
		mockEndpoint.expectedMessageCount(1);
		Object body = producerTemplate.requestBody(jmsConfig.getSource(), "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><example><remoteid>10</remoteid></example>", Object.class);
		assertNotNull(body);
		LOGGER.info("Route test has finished");
	}

	@Transactional
//	@Test
	public void testSave() {
		Employee employee = new Employee();
		employee.setEmployeeName("employeeName");
		employeeDAO.save(employee);
		assertNotNull(employee);
	}
}

