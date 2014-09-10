package com.example.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquidBaseXMLConfig {

	@Autowired
	private DataSourceConfig dataSourceConfig;
	
	public LiquidBaseXMLConfig() {
		
	}
	
	@Bean
	public SpringLiquibase liquibase() throws Exception {
   		SpringLiquibase springLiquibase = new SpringLiquibase();
 		springLiquibase.setDataSource(dataSourceConfig.dataSource());
		springLiquibase.setChangeLog("db-changelog.xml");
 		return springLiquibase;
	}
	
	@Bean
	public SpringLiquibase liquibaseAccount() throws Exception {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSourceConfig.dataSourceAccount());
		springLiquibase.setChangeLog("db-changelog-account.xml");
		return springLiquibase;
	}
}