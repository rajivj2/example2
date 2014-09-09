package com.example.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * This class represents the ResourceConfig.
 * @author Rajiv Jain
 */
@Configuration
public class ResourceConfig {

	@Bean
	public PropertyPlaceholderConfigurer properties( ) {
     	PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setIgnoreResourceNotFound(true);
		ppc.setLocations(new ClassPathResource[] {new ClassPathResource("hibernate.properties")});
		return ppc;
	}
}