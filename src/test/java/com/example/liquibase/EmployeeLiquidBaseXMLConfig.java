package com.example.liquibase;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;

//@Configuration
public class EmployeeLiquidBaseXMLConfig {

//	@Autowired
//	@Qualifier(value = "employeeDataSource")
	private DataSource employeeDataSource;

	public EmployeeLiquidBaseXMLConfig() {

	}

//	@Bean
	public SpringLiquibase liquibaseEmployee() throws Exception {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(employeeDataSource);
		springLiquibase.setChangeLog("db-changelog-employee.xml");
		return springLiquibase;
	}
}