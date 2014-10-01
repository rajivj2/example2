package com.example.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class EmployeeDataSourceFactoryConfig {
	
	@Value("${jdbc.url.employee}")
	private String jdbcUrlEmployee;
	@Autowired
	private DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	
	public EmployeeDataSourceFactoryConfig() {
		
	}

	public String getJdbcUrlEmployee() {
		return jdbcUrlEmployee;
	}
	
	@Bean
	public DataSource employeeDataSource() throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(jdbcUrlEmployee);
		dataSource.setUsername(defaultJDBCDataSourceConfig.getJdbcUsername());
		dataSource.setPassword(defaultJDBCDataSourceConfig.getJdbcPassword());
		dataSource.setDriverClassName(defaultJDBCDataSourceConfig.getJdbcDriverClassName());
		return dataSource;
	}
}