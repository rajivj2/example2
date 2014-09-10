package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

/**
 * This class represents the ProcessPersistenceConfiguration.
 * @author Rajiv Jain
 */
@Configuration
public class ProcessPersistenceConfig {

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor = new PersistenceAnnotationBeanPostProcessor();
		persistenceAnnotationBeanPostProcessor.setDefaultPersistenceUnitName("entityManagerFactory");
		return persistenceAnnotationBeanPostProcessor;
	}
}