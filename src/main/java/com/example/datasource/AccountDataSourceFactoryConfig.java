package com.example.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AccountDataSourceFactoryConfig {
	
	@Value("${jdbc.url.account}")
	private String jdbcUrlAccount;
	@Autowired
	private DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	
	public AccountDataSourceFactoryConfig() {
		
	}

	public String getJdbcUrlAccount() {
		return jdbcUrlAccount;
	}
	
	@Bean
	public DataSource accountDataSource() throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(jdbcUrlAccount);
		dataSource.setUsername(defaultJDBCDataSourceConfig.getJdbcUsername());
		dataSource.setPassword(defaultJDBCDataSourceConfig.getJdbcPassword());
		dataSource.setDriverClassName(defaultJDBCDataSourceConfig.getJdbcDriverClassName());
		return dataSource;
	}
}