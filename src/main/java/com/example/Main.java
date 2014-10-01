package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.ResourceConfig;
import com.example.datasource.DefaultJDBCDataSourceConfig;
import com.example.datasource.HibernateConfig;

/**
 * This class represents the Main class.
 * @author Rajiv Jain
 */
public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		ApplicationContext application = application = new AnnotationConfigApplicationContext("com.example.config", 
				"com.example.datasource", "com.example.liquibase", "com.example.entitymanager", "com.example.persistence.dao.jpa.factory", 
				"com.example.transactionmanager");
	}
}