package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = {"classpath:hibernate.properties", "classpath:camel.properties", "file:${server.properties}"}, ignoreResourceNotFound = true)
public class ResourceConfig {

	@Value("${hibernate.show_sql}")
	private String properties;
	@Value("${source}")
	private String source;

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties( ) {
     	return new PropertySourcesPlaceholderConfigurer();
	}

	public String getProperties() {
		return properties;
	}

	public String getSource() {
		return source;
	}
}