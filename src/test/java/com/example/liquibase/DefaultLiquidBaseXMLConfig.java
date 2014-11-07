package com.example.liquibase;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;

//@Configuration
public class DefaultLiquidBaseXMLConfig {

//	@Autowired
//	@Qualifier(value = "defaultDataSource")
	private DataSource defaultDataSource;

	public DefaultLiquidBaseXMLConfig() {

	}

//	@Bean
	public SpringLiquibase liquibase() throws Exception {
   		SpringLiquibase springLiquibase = new SpringLiquibase();
 		springLiquibase.setDataSource(defaultDataSource);
		springLiquibase.setChangeLog("db-changelog.xml");
 		return springLiquibase;
	}
}