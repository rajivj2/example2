package com.example.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DefaultDataSourceFactoryConfig {
	
	@Autowired
	DefaultJDBCDataSourceConfig defaultJDBCDataSourceConfig;
	
	public DefaultDataSourceFactoryConfig() {
		
	}
	
	@Bean
	public DataSource defaultDataSource() throws Exception {
   		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(defaultJDBCDataSourceConfig.getJdbcUrlDefault());
		dataSource.setUsername(defaultJDBCDataSourceConfig.getJdbcUsername());
		dataSource.setPassword(defaultJDBCDataSourceConfig.getJdbcPassword());
		dataSource.setDriverClassName(defaultJDBCDataSourceConfig.getJdbcDriverClassName());
		return dataSource;
	}
}