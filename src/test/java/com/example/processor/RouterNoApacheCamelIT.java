package com.example.processor;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.example.config.ResourceConfig;
import com.example.datasource.DefaultJDBCDataSourceConfig;
import com.example.datasource.HibernateConfig;
import com.example.entities.Status;
import com.example.entities.xml.Entity;
import com.example.entitymanager.DefaultEntityManagerFactoryConfig;
import com.example.liquibase.DefaultLiquidBaseXMLConfig;
import com.example.persistence.dao.StatusDAO;
import com.example.transactionmanager.DefaultTransactionManagerConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ResourceConfig.class, DefaultJDBCDataSourceConfig.class, DefaultLiquidBaseXMLConfig.class, HibernateConfig.class, DefaultEntityManagerFactoryConfig.class, DefaultTransactionManagerConfig.class})
public class RouterNoApacheCamelIT {

	@Autowired
	private StatusDAO statusDAO;
	private ContentEnricherProcessor contentEnricherProcessor;
	private Status status;

	@Before
	public void setUp() throws Exception {
		contentEnricherProcessor = new ContentEnricherProcessor();
		status = new Status();
		status.setUserId(10);
		statusDAO.save(status);
	}

//	@Transactional
//	@Test
	public void testProcess() throws Exception {
		Entity entity = new Entity();
		entity.setUserId("10");
		Status status = contentEnricherProcessor.process(entity);
		assertNotNull(status);
	}
}