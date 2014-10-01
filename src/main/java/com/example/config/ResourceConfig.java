package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = {"classpath:hibernate.properties", "file:${server.properties}"}, ignoreResourceNotFound = true)
public class ResourceConfig {

	@Value("${hibernate.show_sql}")
	private String properties;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties( ) {
     	return new PropertySourcesPlaceholderConfigurer();
	}
	
	public String getProperties() {
		return properties;
	}
}