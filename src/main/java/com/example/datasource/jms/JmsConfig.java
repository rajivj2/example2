package com.example.datasource.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {

	@Value("${source}")
	private String source;
	@Value("${activemq.location}")
	private String location;
	@Value("${activemq.destination}")
	private String destination;

	public JmsConfig() {
		super();
	}

	public final String getSource() {
		return source;
	}

	public final String getLocation() {
		return location;
	}

	public final String getDestination() {
		return destination;
	}
}
