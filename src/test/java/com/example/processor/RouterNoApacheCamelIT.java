package com.example.processor;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.DAOConfig;
import com.example.config.DAOFactoryConfig;
import com.example.config.DataSourceConfig;
import com.example.config.EntityManagerFactoryConfig;
import com.example.config.HibernateConfig;
import com.example.config.LiquidBaseXMLConfig;
import com.example.config.ResourceConfig;
import com.example.config.TransactionManagerConfig;
import com.example.entities.Status;
import com.example.entities.xml.Entity;
import com.example.persistence.dao.StatusDAO;
import com.example.processor.ContentEnricherProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DAOConfig.class, DAOFactoryConfig.class, ResourceConfig.class, DataSourceConfig.class, LiquidBaseXMLConfig.class, HibernateConfig.class, EntityManagerFactoryConfig.class, TransactionManagerConfig.class})
public class RouterNoApacheCamelIT {
	
	@Autowired
	private StatusDAO statusDAO;
	private ContentEnricherProcessor contentEnricherProcessor;
	private Status status;
	
	@Before
	public void setUp() throws Exception {
		contentEnricherProcessor = new ContentEnricherProcessor(statusDAO);
		status = new Status();
		status.setUserId(10);
		statusDAO.save(status);
	}
	
	@Transactional
	@Test
	public void testProcess() throws Exception {
		Entity entity = new Entity();
		entity.setUserId("10");
		Status status = contentEnricherProcessor.process(entity);
		assertNotNull(status);
	}
}