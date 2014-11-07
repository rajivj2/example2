package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = {"classpath:hibernate.properties", "classpath:camel.properties",
		"classpath:myapp4.properties", "file:${server.properties}"}, ignoreResourceNotFound = true)
public class ResourceConfig2 {

	@Value("${hibernate.show_sql}")
	private String properties;
	@Value("${source}")
	private String source;
	@Value("${property}")
	private String property;

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

	public String getProperty() {
		return property;
	}
}