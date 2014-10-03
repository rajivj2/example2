package com.example;

import java.io.BufferedInputStream;
import java.util.Properties;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.config.ApacheCamelConfig;
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
	
	public SetupJUnitITProcessor() throws Exception {
		properties = new Properties();
		properties.load(new BufferedInputStream(ClassLoader.getSystemResourceAsStream("camel.properties")));
		source = properties.getProperty("source");
	}
	
	@Before
	public void setUp() throws Exception {
		producerTemplate = context.createProducerTemplate();
	}
	
	@After
	public void tearDown() throws Exception {
//		config.stop();
	}
}