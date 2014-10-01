package com.example.liquibase;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultLiquidBaseXMLConfig {

	@Autowired
	@Qualifier(value = "defaultDataSource")
	private DataSource defaultDataSource;
	
	public DefaultLiquidBaseXMLConfig() {
		
	}
	
	@Bean
	public SpringLiquibase liquibase() throws Exception {
   		SpringLiquibase springLiquibase = new SpringLiquibase();
 		springLiquibase.setDataSource(defaultDataSource);
		springLiquibase.setChangeLog("db-changelog.xml");
 		return springLiquibase;
	}
}