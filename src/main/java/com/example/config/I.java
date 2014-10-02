package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@Configuration
//@ManagedResource(objectName = "bean:name=I")
//@EnableMBeanExport(server = "mBeanServer")
public class I {

//	@ManagedOperation
//	@Bean
	public II start() {
		return new II();
	}
}