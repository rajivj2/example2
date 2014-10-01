package com.example.liquibase;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountLiquidBaseXMLConfig {

	@Autowired
	@Qualifier(value = "accountDataSource")
	private DataSource accountDataSource;
	
	public AccountLiquidBaseXMLConfig() {
		
	}
	
	@Bean
	public SpringLiquibase liquibaseAccount() throws Exception {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(accountDataSource);
		springLiquibase.setChangeLog("db-changelog-account.xml");
		return springLiquibase;
	}
}